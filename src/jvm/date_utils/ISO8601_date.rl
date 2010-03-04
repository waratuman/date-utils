%%{
  machine ISO8601_date;
  
  action set_year { year = Integer.parseInt(new String(data, ts, 4)); }
  action set_two_digit_year { year = Integer.parseInt((new String(data, ts, 2)) + "00"); }
  action set_month { month = Integer.parseInt(new String(data, ts, 2)); }
  action set_day { day = Integer.parseInt(new String(data, ts, 2)); }
  
  days_31 = (('0' . [1-9]) | 
             (('1' | '2') . [0-9]) | 
             ('3' . [0-1])) >tag @set_day;
  days_30 = (('0' . [1-9]) |
             (('1' | '2') . [0-9]) | '30') >tag @set_day;
  days_29 = (('0' . [1-9]) | (('1' | '2') . [0-9])) >tag @set_day;
  days_28 = (('0' . [1-9]) | (('1' | '2') . [0-8])) >tag @set_day;
  
  month = (('0' . [1-9]) | ('1' . [0-2])) >tag @set_month;

  leap_year_400 = 
    (('0' | '2' | '4' | '6' | '8' ) . ('0' | '4' | '8') |
     ('1' | '3' | '5' | '7' | '9' ) . ('2' | '6')) . '0' . '0';
  leap_year_100 =
    digit{2} . '00';
  leap_year_4 =
    digit{2} . 
    ((('0' | '2' | '4' | '6' | '8' ) . ('0' | '4' | '8')) |
     (('1' | '3' | '5' | '7' | '9' ) . ('2' | '6')));
  leap_year_exp = (leap_year_400 | (leap_year_4 - leap_year_100)) >tag @set_year;
  leap_year = leap_year_exp;
  
  leap_year_month = leap_year . '-' . month;
  leap_year_month_day1 = leap_year . 
    (((('01' | '03' | '05' | '07' | '08' | '10' | '12')  >tag @set_month) . days_31) |
     ((('04' | '06' | '09' | '11')  >tag @set_month) . days_30) |
     (('02'  >tag @set_month) . days_29));
  leap_year_month_day2 = leap_year . '-' .
    (((('01' | '03' | '05' | '07' | '08' | '10' | '12')  >tag @set_month) . '-' . days_31) |
     ((('04' | '06' | '09' | '11')  >tag @set_month) . '-' . days_30) |
     (('02'  >tag @set_month) . '-' . days_29));
  leap_year_month_day = leap_year_month_day1 | leap_year_month_day2;
  

  normal_year = (digit{4} - leap_year_exp) >tag @set_year;
  normal_year_month = normal_year '-' month;
  normal_year_month_day1 = normal_year .
    (((('01' | '03' | '05' | '07' | '08' | '10' | '12') >tag @set_month) . days_31) |
     ((('04' | '06' | '09' | '11') >tag @set_month) . days_30) |
     (('02'  >tag @set_month) . days_28));
  normal_year_month_day2 = normal_year . '-' .
    (((('01' | '03' | '05' | '07' | '08' | '10' | '12') >tag @set_month) . '-' . days_31) |
     ((('04' | '06' | '09' | '11') >tag @set_month) . '-' . days_30) |
     (('02'  >tag @set_month) . '-' . days_28));
  normal_year_month_day = normal_year_month_day1 | normal_year_month_day2;
  
  two_digit_year = digit{2} >tag @set_two_digit_year;
  
  leap_date = leap_year | leap_year_month | leap_year_month_day;
  normal_date = normal_year | normal_year_month | normal_year_month_day;
  calendar_date = (two_digit_year | leap_date | normal_date) %/set_calendar_date;
  complete_calendar_date = (leap_year_month_day | normal_year_month_day) %set_calendar_date;
  
  # TODO: Implement the week date.
  week_date = '';
  
  # TODO: Implement ordinal dates.
  ordinal_date = '';
  
  date = calendar_date; # | week_date | ordinal_date;
}%%
