package date_utils;

import java.util.Date;
import java.util.TimeZone;
import java.text.ParseException;
import java.util.GregorianCalendar;

%%{
  # TODO: Implement durations, time intervals and repeating intervals
  machine ISO8601;
  
  action tag { ts = p; }
  action set_date { 
	  month--; // The month is 0 based for the GregorianCalendar
	  calendar = new GregorianCalendar(year, month, day, hour, min, sec);
	  calendar.setTimeZone(zone);
  }
  
  include ISO8601_date "ISO8601_date.rl";
  include ISO8601_time "ISO8601_time.rl";
  include ISO8601_time_zone "ISO8601_time_zone.rl";
  
  main := (date | (complete_calendar_date . ('T' | ' ') . time . zone_designator)) %/ set_date;
  
}%%

public class ISO8601DateFormatter {
    
    %% write data;
    
    public static GregorianCalendar parse(String string) throws ParseException {
        char[] data = string.toCharArray();
        int cs;
        int ts = 0;
        int eof = data.length;
        int p = 0;
        int pe = eof;
        int year = 0, month = 1, day = 0, hour = 0, min = 0, sec = 0;
        TimeZone zone = TimeZone.getDefault();
		    GregorianCalendar calendar = null;

        %% write init;
        %% write exec;
        
        if (cs == ISO8601_error || calendar == null) {
            throw new ParseException("Unparseable Date.", p);
        }
        
        return calendar;
    }
    
}
