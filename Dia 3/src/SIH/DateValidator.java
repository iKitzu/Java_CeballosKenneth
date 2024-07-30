package SIH;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public static boolean isValidDate(String dateStr) {
        dateFormat.setLenient(false);
        try {
            Date date = dateFormat.parse(dateStr);
            return dateStr.equals(dateFormat.format(date));
        } catch (ParseException e) {
            return false;
        }
    }
}
