package num11;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {
    static String pattern = "dd-M-yyyy hh:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);

    public String format(Date date) {
        return sdf.format(date);
    }
}
