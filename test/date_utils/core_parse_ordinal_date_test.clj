(ns date-utils.core-parse-ordinal-date-test
  (:use clojure.test
        date-utils.core)
  (:import java.util.GregorianCalendar
           java.text.ParseException))

(deftest parse-normal-year-day
  (is (= (ordinal-date 2001 365)
         (parse "2001-365")))
  (is (= (date 1700 1 1)
         (parse "1700-001"))))

(deftest parse-leap-year-day
  (is (= (date 2000 2 29) 
         (parse "2000-060")))
  (is (= (date 2004 2 1) 
         (parse "2004-032"))))

(deftest parse-valid-ordinal-dates-test
  (is (= (date 2001 12 31)
         (parse "2001-365")))
  (is (= (date 2001 12 31)
         (parse "2001365"))))

(deftest parse-invalid-ordinal-dates-test
  (is (thrown? ParseException (parse "2001000")))
  (is (thrown? ParseException (parse "2001366")))
  (is (thrown? ParseException (parse "2001400"))))
