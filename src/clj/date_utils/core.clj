(ns date-utils.core
  (:import java.util.GregorianCalendar
           date_utils.ISO8601DateParser))

(defn parse [string]
  (ISO8601DateParser/parse string))

(defn date
  ([]
     (let [calendar (GregorianCalendar.)]
       (.setTime calendar (java.util.Date.))
       calendar))
  ([year]
     (date year 1 1))
  ([year month]
     (date year month 1 0 0 0))
  ([year month day]
     (date year month day 0 0 0))
  ([year month day hour]
     (date year month day hour 0 0))
  ([year month day hour min]
     (date year month day hour min 0))
  ([year month day hour min sec]
     (GregorianCalendar. year (- month 1) day hour min sec)))

(defn ordinal-date [year day-of-year]
  (let [cal (GregorianCalendar. 1970 0 1 0 0 0)]
    (.set cal GregorianCalendar/YEAR year)
    (.set cal GregorianCalendar/DAY_OF_YEAR day-of-year)
    cal))

(defn year [cal]
  (.get cal GregorianCalendar/YEAR))

(defn month [cal]
  (+ 1 (.get cal GregorianCalendar/MONTH)) )

(defn day [cal]
  (.get cal GregorianCalendar/DAY_OF_MONTH))

(defn hour [cal]
  (.get cal GregorianCalendar/HOUR))

(defn minutes [cal]
  (.get cal GregorianCalendar/MINUTE))

(defn seconds [cal]
  (.get cal GregorianCalendar/SECOND))

(defn add [cal a]
  "Add `a` seconds ot the date `cal`. For example
   `(add (date 2009 1 1) 3600)`."
   (let [c (.clone cal)]
     (.add c GregorianCalendar/SECOND a)
     c))

(defn difference [a b]
  "The number of seconds that date `a` differs from date `b`. For
   example: `(difference (date 2009 1 1) (parse 2009 1 2))` results
   in ``."
   (/ (- (.getTimeInMillis b) (.getTimeInMillis a))
      1000))

(defn after? [a b]
  (> 0 (difference a b)))

(defn before? [a b]
  (< 0 (difference a b)))
