(ns date-utils.core-parse-date-time-test
  (:use clojure.test
        date-utils.core)
  (:import java.util.GregorianCalendar
           java.text.ParseException))


(deftest parse-invalid-date-representation-with-time
  (is (thrown? ParseException (parse "19T01")))
  (is (thrown? ParseException (parse "19T0122")))
  (is (thrown? ParseException (parse "19T012203")))
  (is (thrown? ParseException (parse "1900T01")))
  (is (thrown? ParseException (parse "1900T0122")))
  (is (thrown? ParseException (parse "1900T012203")))
  (is (thrown? ParseException (parse "190001T01")))
  (is (thrown? ParseException (parse "190001T0122")))
  (is (thrown? ParseException (parse "190001T012203")))
  (is (thrown? ParseException (parse "1900-01T01")))
  (is (thrown? ParseException (parse "1900-01T0122")))
  (is (thrown? ParseException (parse "1900-01T012203"))))


(deftest parse-date-time
  (is (= (.getTime (GregorianCalendar. 2004 0 31 23 0 0))
         (parse "20040131T23")))
  (is (= (.getTime (GregorianCalendar. 2004 0 31 23 12 0))
         (parse "20040131T2312")))
  (is (= (.getTime (GregorianCalendar. 2004 0 31 23 12 0))
         (parse "20040131T23:12")))
  (is (= (.getTime (GregorianCalendar. 2004 0 31 23 12 13))
         (parse "20040131T231213")))
  (is (= (.getTime (GregorianCalendar. 2004 0 31 23 12 13))
         (parse "20040131T23:12:13"))))


(deftest parse-date-invalid-time
  (is (thrown? ParseException (parse "20040131T2")))
  (is (thrown? ParseException (parse "20040131T23-12")))
  (is (thrown? ParseException (parse "20040131T232")))
  (is (thrown? ParseException (parse "20040131T23121")))
  (is (thrown? ParseException (parse "20040131T23121:23")))
  (is (thrown? ParseException (parse "20040131T23-12-13"))))

;; (deftest parse-decimal-time
;;   (is (= (.getTime (GregorianCalendar. 2004 0 31))
;;          (parse "20040131T23.5")))
;;   (is (= (.getTime (GregorianCalendar. 2004 0 31))
;;          (parse "20040131T23,5")))
;;   (is (= (.getTime (GregorianCalendar. 2004 0 31))
;;          (parse "20040131T2312.5")))
;;   (is (= (.getTime (GregorianCalendar. 2004 0 31))
;;          (parse "20040131T2312,5")))
;;   (is (= (.getTime (GregorianCalendar. 2004 0 31))
;;          (parse "20040131T231213.5")))
;;   (is (= (.getTime (GregorianCalendar. 2004 0 31))
;;          (parse "20040131T231213,5"))))



