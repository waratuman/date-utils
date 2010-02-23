(ns date-utils.core-test
  (:use clojure.test
        date-utils.core)
  (:import java.util.GregorianCalendar
           java.text.ParseException))

(deftest parse-normal-year
  (is (= (.getTime (GregorianCalendar. 2010 0 0)) 
         (parse "2010")))
  (is (= (.getTime (GregorianCalendar. 1700 0 0)) 
         (parse "1700")))
  (is (= (.getTime (GregorianCalendar. 3000 0 0)) 
         (parse "3000"))))

(deftest parse-leap-year
  (is (= (.getTime (GregorianCalendar. 2000 0 0)) 
         (parse "2000")))
  (is (= (.getTime (GregorianCalendar. 2004 0 0)) 
         (parse "2004"))))

(deftest parse-normal-year-month
  (is (= (.getTime (GregorianCalendar. 2001 1 0))
         (parse "2001-02")))
  (is (= (.getTime (GregorianCalendar. 1700 1 0)) 
         (parse "1700-02"))))

(deftest parse-leap-year-month
  (is (= (.getTime (GregorianCalendar. 2000 1 0))
         (parse "2000-02")))
  (is (= (.getTime (GregorianCalendar. 2004 1 0)) 
         (parse "2004-02"))))

(deftest parse-normal-year-month-day
  (is (= (.getTime (GregorianCalendar. 2001 1 28))
         (parse "20010228")))
  (is (= (.getTime (GregorianCalendar. 1700 1 28))
         (parse "17000228")))
  (is (thrown? ParseException (parse "20010229")))
  (is (thrown? ParseException (parse "17000229"))))

(deftest parse-leap-year-month-day
  (is (= (.getTime (GregorianCalendar. 2000 1 29))
         (parse "20000229"))))

(deftest parse-normal-year-month-day2
  (is (= (.getTime (GregorianCalendar. 2001 1 28))
         (parse "2001-02-28")))
  (is (= (.getTime (GregorianCalendar. 1700 1 28))
         (parse "1700-02-28")))
  (is (thrown? ParseException (parse "2001-02-29")))
  (is (thrown? ParseException (parse "1700-02-29"))))

(deftest parse-leap-year-month-day2
  (is (= (.getTime (GregorianCalendar. 2004 1 29))
         (parse "2004-02-29")))
  (is (= (.getTime (GregorianCalendar. 2000 1 29))
         (parse "2000-02-29"))))

(deftest parse-valid-month-dates-test
  (is (= (.getTime (GregorianCalendar. 2004 0 31))
         (parse "2004-01-31")))
  (is (= (.getTime (GregorianCalendar. 2004 0 31))
         (parse "20040131")))
  (is (= (.getTime (GregorianCalendar. 2004 2 31))
         (parse "2004-03-31")))
  (is (= (.getTime (GregorianCalendar. 2004 2 31))
         (parse "20040331")))
  (is (= (.getTime (GregorianCalendar. 2004 3 30))
         (parse "2004-04-30")))
  (is (= (.getTime (GregorianCalendar. 2004 3 30))
         (parse "20040430")))
  (is (= (.getTime (GregorianCalendar. 2004 4 31))
         (parse "2004-05-31")))
  (is (= (.getTime (GregorianCalendar. 2004 4 31))
         (parse "20040531")))
  (is (= (.getTime (GregorianCalendar. 2004 5 30))
         (parse "2004-06-30")))
  (is (= (.getTime (GregorianCalendar. 2004 5 30))
         (parse "20040630")))
  (is (= (.getTime (GregorianCalendar. 2004 6 31))
         (parse "2004-07-31")))
  (is (= (.getTime (GregorianCalendar. 2004 6 31))
         (parse "20040731")))
  (is (= (.getTime (GregorianCalendar. 2004 7 31))
         (parse "2004-08-31")))
  (is (= (.getTime (GregorianCalendar. 2004 7 31))
         (parse "20040831")))
  (is (= (.getTime (GregorianCalendar. 2004 8 30))
         (parse "2004-09-30")))
  (is (= (.getTime (GregorianCalendar. 2004 8 30))
         (parse "20040930")))
  (is (= (.getTime (GregorianCalendar. 2004 9 31))
         (parse "2004-10-31")))
  (is (= (.getTime (GregorianCalendar. 2004 9 31))
         (parse "20041031")))
  (is (= (.getTime (GregorianCalendar. 2004 10 30))
         (parse "2004-11-30")))
  (is (= (.getTime (GregorianCalendar. 2004 10 30))
         (parse "20041130")))
  (is (= (.getTime (GregorianCalendar. 2004 11 31))
         (parse "2004-12-31")))
  (is (= (.getTime (GregorianCalendar. 2004 11 31))
         (parse "20041231"))))

(deftest parse-invalid-month-dates-test
  (is (thrown? ParseException (parse "2004-01-00")))
  (is (thrown? ParseException (parse "2004-01-32")))
  (is (thrown? ParseException (parse "20040132")))
  (is (thrown? ParseException (parse "2004-03-32")))
  (is (thrown? ParseException (parse "20040332")))
  (is (thrown? ParseException (parse "2004-04-31")))
  (is (thrown? ParseException (parse "20040431")))
  (is (thrown? ParseException (parse "2004-05-32")))
  (is (thrown? ParseException (parse "20040532")))
  (is (thrown? ParseException (parse "2004-06-31")))
  (is (thrown? ParseException (parse "20040631")))
  (is (thrown? ParseException (parse "2004-07-32")))
  (is (thrown? ParseException (parse "20040732")))
  (is (thrown? ParseException (parse "2004-08-32")))
  (is (thrown? ParseException (parse "20040832")))
  (is (thrown? ParseException (parse "2004-09-31")))
  (is (thrown? ParseException (parse "20040931")))
  (is (thrown? ParseException (parse "2004-10-32")))
  (is (thrown? ParseException (parse "20041032")))
  (is (thrown? ParseException (parse "2004-11-31")))
  (is (thrown? ParseException (parse "20041131")))
  (is (thrown? ParseException (parse "2004-12-32")))
  (is (thrown? ParseException (parse "20041232"))))

(deftest parse-invalid-formats
  (is (thrown? ParseException (parse "2004-0101")))
  (is (thrown? ParseException (parse "200401-01")))
  (is (thrown? ParseException (parse "04-01-02"))))

