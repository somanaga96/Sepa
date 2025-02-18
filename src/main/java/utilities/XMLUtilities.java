package utilities;

import com.codoid.products.exception.FilloException;
import io.cucumber.messages.internal.com.google.common.io.FileBackedOutputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.lang.model.element.Element;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Paths;
import java.util.Map;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static utilities.BaseUtils.*;
import static utilities.Constants.SCREEN_SHOT_FOLDER_PATH;
import static utilities.Constants.TEMPLATE_DIRECTORY;

public class XMLUtilities {
    public static String updateXMLTemplate(String testCaseId, String sheetName) throws FilloException {
        Map<String, String> excelTestData = ExcelReader.readExcel(getPropertyValue(configPropertyFilePath, "ExcelDataFilePath"), sheetName, testCaseId);
        File messageFile = new File(TEMPLATE_DIRECTORY + "/" + "pacs008_template.xml");
        String filePath = copyXMLFromTemplate(messageFile, SCREEN_SHOT_FOLDER_PATH + scenarioID + "/" + testCaseId + runID + "pacs008OutBound");
        System.out.println("filepath :" + filePath);
        updateXMLDataFromExcel(filePath, "GrpHdr", "MsgId", 0, "check1");
        return filePath;
    }

    public static String copyXMLFromTemplate(File sourceFile, String targetFile) {
        if (sourceFile == null || targetFile == null || targetFile.isEmpty()) {
            return null;
        }

        // Extract file extension
        String[] parts = sourceFile.getName().split("\\.");
        String extension = parts.length > 1 ? parts[parts.length - 1] : "";

        // Append extension if present
        if (!extension.isEmpty()) {
            targetFile += "." + extension;
        }

        File destinationFile = new File(targetFile);

        try {
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return destinationFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String updateXMLDataFromExcel(String filePath, String tagName, String tagName1, int index, String updatedValue) {
        System.out.println("Tag Name :" + tagName);
        System.out.println("Tag Name1 :" + tagName1);
        System.out.println("Update value :" + updatedValue);
        DocumentBuilderFactory ddf = DocumentBuilderFactory.newInstance();
        try {
            ddf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = ddf.newDocumentBuilder();
            Document doc = db.parse(new File(filePath));
            doc.getDocumentElement().normalize();

            NodeList docNodes = doc.getElementsByTagName(tagName);
            if (docNodes.getLength() == 0) {
                return "Tag not found: " + tagName;
            }

            Node docNode = docNodes.item(0);
            if (docNode.getNodeType() == Node.ELEMENT_NODE) {
                if (((org.w3c.dom.Element) docNode).getElementsByTagName(tagName1).getLength() > 0) {
                    if (!"DEFAULT".equals(updatedValue)) {
                        ((org.w3c.dom.Element) docNode).getElementsByTagName(tagName1).item(index).setTextContent(updatedValue);
                        writeXML(doc, filePath);
                    }
                }
            }
            return String.valueOf(((org.w3c.dom.Element) docNode).getElementsByTagName(tagName1).getLength());

        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeXML(Document doc, String filePath) throws TransformerException {
        doc.getDocumentElement().normalize();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Enable proper indentation
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // Indent with 4 spaces
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

        // Write XML to a String first to clean up formatting
        Writer out = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(out));

        // Remove unnecessary blank lines
        String formattedXml = out.toString().replaceAll("(?m)^\\s*$[\n\r]+", "");

        // Write the cleaned XML to file
        try {
            Files.write(Paths.get(filePath), formattedXml.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error writing formatted XML to file: " + filePath, e);
        }
    }

}
