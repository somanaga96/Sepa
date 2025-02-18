package utilities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class GeneralUtilities {
    public static String getUKCurrentDate(String format, String timeZoneId) {
        String dateTime;
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
            Instant instant = Instant.now();
            if (timeZoneId.equalsIgnoreCase("BST")) {
                timeZoneId = "Europe/London";
            }
            ZoneId id = ZoneId.of(timeZoneId);
            ZonedDateTime zonedDateTime = instant.atZone(id);
            dateTime = zonedDateTime.format(dateTimeFormatter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dateTime;
    }

}
