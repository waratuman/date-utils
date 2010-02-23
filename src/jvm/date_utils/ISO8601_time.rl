%%{
  machine ISO8601_time;
  
  # TODO: add support for fraction on the lowest time element. For example
  # "14.5", "14:30,5", "1430,5", "1430.5" or "14:30:30.4"

  action set_hour { hour = Integer.parseInt(new String(data, ts, 2)); }
  action set_min { min = Integer.parseInt(new String(data, ts, 2)); }
  action set_sec { sec = Integer.parseInt(new String(data, ts, 2)); }
  
  hour = ([0-1] . [0-9]) | ('2' . [0-4]) >tag @set_hour;
  min = ([0-5] . [0-9]) >tag @set_min;
  sec = (([0-5] . [0-9]) | ('6' . '0')) >tag @set_sec;
  
  hour_min = hour . '-'{0,1} . min;
  hour_min_sec = (hour . min . sec) | (hour . '-' . min . '-' . sec);
  
  time = hour | hour_min | hour_min_sec;
}%%
