package date_utils;

import java.util.Date;
import java.text.ParseException;
import java.util.GregorianCalendar;

%%{
  # TODO: Implement durations, time intervals and repeating intervals
  machine ISO8601;
  
  action tag { ts = p; }
  
  include ISO8601_date "ISO8601_date.rl";
  include ISO8601_time "ISO8601_time.rl";
  
    main := date; # | time | (date . 'T' . time);
}%%

public class ISO8601DateFormatter {
    
    %% write data;
    
    public static Date parse(String string) throws ParseException {
        char[] data = string.toCharArray();
        int cs;
        int ts = 0;
        int eof = data.length;
        int p = 0;
        int pe = eof;
        int year = 0, month = 1, day = 0, hour = 0, min = 0, sec = 0;
        
        %% write init;
        %% write exec;
        
        if (cs == ISO8601_error) {
            throw new ParseException("Unparseable Date.", p);
        }
        
        month--; // The month is 0 based for the GregorianCalendar
        
        GregorianCalendar c = new GregorianCalendar(year, month, day, hour, min, sec);
        return c.getTime();
    }
    
}
