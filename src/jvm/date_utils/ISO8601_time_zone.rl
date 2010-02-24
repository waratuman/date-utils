%%{
  machine ISO8601_time_zone;
  
  action set_timezone { zone = TimeZone.getTimeZone("GMT" + (new String(data, ts, p - ts + 1))); }
  action set_utc_timezone { zone = TimeZone.getTimeZone("UTC"); }
  
  zulu = 'Z' @set_utc_timezone;
  offset = (('+' | '-') . digit{2} . ':'{0,1} . digit{2}) >tag @set_timezone;
  
  zone_designator = '' | zulu | offset;

}%%
