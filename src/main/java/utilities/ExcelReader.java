package utilities;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.util.Map;
import java.util.TreeMap;

public class ExcelReader {
    public static Map<String, String> readExcel(String excelFilePath, String sheetName, String testCaseId) throws FilloException {
        Map<String, String> data = new TreeMap<>();
        String readQuery = String.format("SELECT *FROM %s WHERE RUN='YES' and TestCaseId='%s'", sheetName, testCaseId);
        Fillo fillo = new Fillo();
        Connection connection;
        Recordset recordset;
        try {
            connection = fillo.getConnection(excelFilePath);
            recordset = connection.executeQuery(readQuery);
            while (recordset.next()) {
                for (String field : recordset.getFieldNames()) {
                    data.put(field, recordset.getField(field));
                }
            }
        } catch (FilloException e) {
            e.printStackTrace();
            throw new FilloException("Test data cannot be find.....");
        }
        connection.close();
        return data;
    }
}
