package num11;

import java.util.Date;

public class FormatThread extends Thread{

    private Formatter formatter;
    private Date date;
    private String dateFormatted;

    public FormatThread(Date date, Formatter formatter){
        this.date = date;
        this.formatter = formatter;
    }

    @Override
    public void run() {
        dateFormatted = formatter.format(date);
    }

    public String getDateFormatted() {
        return dateFormatted;
    }
}
