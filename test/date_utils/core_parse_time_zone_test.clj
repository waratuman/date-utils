(ns date-utils.core-parse-time-zone-test
  (:use clojure.test
        date-utils.core)
  (:import java.util.GregorianCalendar
           java.text.ParseException
           java.util.TimeZone))

(def utc-calendar
     (let [c (GregorianCalendar. 0 0 0 0 0 0)]
       (.setTimeZone c (TimeZone/getTimeZone "UTC"))
       c))

(def other-tz-calendar
     (let [c (GregorianCalendar. 0 0 0 0 0 0)]
       (.setTimeZone c (TimeZone/getTimeZone "GMT+0500"))
       c))

(def local-calendar
     (let [c (GregorianCalendar. 0 0 0 0 0 0)]
       (.setTimeZone c (TimeZone/getDefault))
       c))

(defn calendar-with-time-zone [zone]
  (let [c (GregorianCalendar. 0 0 0 0 0 0)]
       (.setTimeZone c (TimeZone/getTimeZone zone))
       c))

(defn date-from-cal [cal year month day hour min sec]
  (.set cal year month day hour min sec)
  cal)

(deftest parse-date-time
  (is (= (date-from-cal utc-calendar 2004 0 31 23 0 0)
         (parse "20040131T23Z")))
  (is (= (date-from-cal local-calendar 2004 0 31 23 0 0)
         (parse "20040131T23")))
  (is (= (date-from-cal other-tz-calendar 2004 0 31 23 0 0)
         (parse "20040131T23+05:00")))
  (is (= (date-from-cal other-tz-calendar 2004 0 31 23 0 0)
         (parse "20040131T23+0500")))
  (is (= (date-from-cal (calendar-with-time-zone "GMT-0500") 2004 0 31 23 0 0)
         (parse "20040131T23-0500")))
  (is (= (date-from-cal (calendar-with-time-zone "GMT-0500") 2004 0 31 23 0 0)
         (parse "20040131T23-05:00")))
  (is (= (date-from-cal utc-calendar 2004 0 31 23 21 0)
         (parse "20040131T2321Z")))
  (is (= (date-from-cal utc-calendar 2004 0 31 23 21 0)
         (parse "20040131T23:21Z")))
  (is (= (date-from-cal local-calendar 2004 0 31 23 21 0)
         (parse "20040131T2321")))
  (is (= (date-from-cal local-calendar 2004 0 31 23 21 0)
         (parse "20040131T23:21")))
  (is (= (date-from-cal other-tz-calendar 2004 0 31 23 21 0)
         (parse "20040131T2321+05:00")))
  (is (= (date-from-cal other-tz-calendar 2004 0 31 23 21 0)
         (parse "20040131T23:21+05:00")))
  (is (= (date-from-cal other-tz-calendar 2004 0 31 23 21 0)
         (parse "20040131T2321+0500")))
  (is (= (date-from-cal other-tz-calendar 2004 0 31 23 21 0)
         (parse "20040131T23:21+0500")))
  (is (= (date-from-cal (calendar-with-time-zone "GMT-0500") 2004 0 31 23 21 0)
         (parse "20040131T2321-0500")))
  (is (= (date-from-cal (calendar-with-time-zone "GMT-0500") 2004 0 31 23 21 0)
         (parse "20040131T23:21-0500")))
  (is (= (date-from-cal (calendar-with-time-zone "GMT-0500") 2004 0 31 23 21 0)
         (parse "20040131T2321-05:00")))
  (is (= (date-from-cal (calendar-with-time-zone "GMT-0500") 2004 0 31 23 21 0)
         (parse "20040131T23:21-05:00")))
  (is (= (date-from-cal utc-calendar 2004 0 31 23 21 54)
         (parse "20040131T232154Z")))
  (is (= (date-from-cal utc-calendar 2004 0 31 23 21 54)
         (parse "20040131T23:21:54Z")))
  (is (= (date-from-cal local-calendar 2004 0 31 23 21 54)
         (parse "20040131T232154")))
  (is (= (date-from-cal local-calendar 2004 0 31 23 21 54)
         (parse "20040131T23:21:54")))
  (is (= (date-from-cal other-tz-calendar 2004 0 31 23 21 54)
         (parse "20040131T232154+05:00")))
  (is (= (date-from-cal other-tz-calendar 2004 0 31 23 21 54)
         (parse "20040131T23:21:54+05:00")))
  (is (= (date-from-cal other-tz-calendar 2004 0 31 23 21 54)
         (parse "20040131T232154+0500")))
  (is (= (date-from-cal other-tz-calendar 2004 0 31 23 21 54)
         (parse "20040131T23:21:54+0500")))
  (is (= (date-from-cal (calendar-with-time-zone "GMT-0500") 2004 0 31 23 21 54)
         (parse "20040131T232154-0500")))
  (is (= (date-from-cal (calendar-with-time-zone "GMT-0500") 2004 0 31 23 21 54)
         (parse "20040131T23:21:54-0500")))
  (is (= (date-from-cal (calendar-with-time-zone "GMT-0500") 2004 0 31 23 21 54)
         (parse "20040131T232154-05:00")))
  (is (= (date-from-cal (calendar-with-time-zone "GMT-0500") 2004 0 31 23 21 54)
         (parse "20040131T23:21:54-05:00"))))

