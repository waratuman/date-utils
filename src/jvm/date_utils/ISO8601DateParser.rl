package date_utils;

import java.util.Date;
import java.util.TimeZone;
import java.text.ParseException;
import java.util.GregorianCalendar;

%%{
  # TODO: Implement durations, time intervals and repeating intervals
  machine ISO8601;
  
  action tag { ts = p; }
  action set_calendar_date { 
    month--; // The month is 0 based for the GregorianCalendar
    calendar = new GregorianCalendar(year, month, day, hour, min, sec);
  }
  action set_time {
    calendar.set(GregorianCalendar.HOUR, hour);
    calendar.set(GregorianCalendar.MINUTE, min);
    calendar.set(GregorianCalendar.SECOND, sec);
  }
  action finish {
    parseFinished = true;
  }
  
  include ISO8601_date "ISO8601_date.rl";
  include ISO8601_time "ISO8601_time.rl";
  include ISO8601_time_zone "ISO8601_time_zone.rl";
  
  main := (date | (complete_calendar_date . ('T' | ' ') . time . zone_designator)) %/finish;
  
}%%

public class ISO8601DateParser {
    
    %% write data;
    
    public static GregorianCalendar parse(String string) throws ParseException {
        char[] data = string.toCharArray();
        int cs;
        int ts = 0;
        int eof = data.length;
        int p = 0;
        int pe = eof;
        int year = 1970, month = 1, day = 1, hour = 0, min = 0, sec = 0;
        boolean parseFinished = false;
        TimeZone zone = TimeZone.getDefault();
		    GregorianCalendar calendar = null;

        %% write init;
        %% write exec;
        
        if (cs == ISO8601_error || parseFinished == false) {
            throw new ParseException("Unparseable Date.", p);
        }
        
        calendar.setTimeZone(zone);
        
        return calendar;
    }
    
}
