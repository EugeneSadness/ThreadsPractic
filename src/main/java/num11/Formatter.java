package num11;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {

    public String format(Date date){
        String pattern = "dd-M-yyyy hh:mm:ss";
        SimpleDateFormat sdf  = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
