(ns date-utils.core-test
  (:use clojure.test
        date-utils.core)
  (:import java.util.GregorianCalendar))

(defn gregorian-calendar
  ([year] (gregorian-calendar year 1 1 0 0 0))
  ([year month] (gregorian-calendar year month 1 0 0 0))
  ([year month day] (gregorian-calendar year month day 0 0 0))
  ([year month day hour min sec]
     (let [cal (GregorianCalendar. year (- month 1) day hour min sec)]
       cal)))

(deftest date-test
  (is date)
  (is (= (gregorian-calendar 2004 1 1)
         (date 2004)))
  (is (= (gregorian-calendar 2004 2 1)
        (date 2004 2)))
  (is (= (gregorian-calendar 2004 2 2)
        (date 2004 2 2)))
  (is (= (gregorian-calendar 2004 1 1 0 0 0)
         (date 2004)))
  (is (= (gregorian-calendar 2004 2 1 0 0 0)
        (date 2004 2)))
  (is (= (gregorian-calendar 2004 2 2 0 0 0)
        (date 2004 2 2)))
  (is (= (gregorian-calendar 2004 2 2 3 0 0)
         (date 2004 2 2 3)))
  (is (= (gregorian-calendar 2004 2 2 3 4 0)
        (date 2004 2 2 3 4)))
  (is (= (gregorian-calendar 2004 2 2 3 4 5)
        (date 2004 2 2 3 4 5))))

(deftest ordinal-date-test
  (is (= (gregorian-calendar 2001)
         (ordinal-date 2001 1))))

(deftest year-test
  (is (= 2004
         (year (date 2004))))
  (is (= 1913
         (year (date 1913)))))

(deftest month-test
  (is (= 1
         (month (date 2004))))
  (is (= 2
         (month (date 1913 2)))))

(deftest day-test
  (is (= 1
         (day (date 2004))))
  (is (= 2
         (day (date 1913 1 2)))))

(deftest hour-test
  (is (= 0
         (hour (date 2004))))
  (is (= 1
         (hour (date 1913 2 1 1)))))

(deftest minutes-test
  (is (= 0
         (minutes (date 2004))))
  (is (= 20
         (minutes (date 1913 2 1 1 20)))))

(deftest seconds-test
  (is (= 0
         (seconds (date 2004))))
  (is (= 49
         (seconds (date 1913 2 1 1 20 49)))))

(deftest add-test
  (is (= (date 2009 1 2)
         (add (date 2009 1 1) 86400)))
  (is (= (date 2009 1 1)
         (add (date 2009 1 2) -86400))))

(deftest difference-test
  (is (= 86400
         (difference (date 2009 1 1) (date 2009 1 2))))
  (is (= -86400
        (difference (date 2009 1 2) (date 2009 1 1)))))

(deftest after?-test
  (is (after? (date 2010) (date 2009)))
  (is (= false
         (after? (date 2009) (date 2010)))))

(deftest before?-test
  (is (before? (date 2009) (date 2010)))
  (is (= false
         (before? (date 2010) (date 2009)))))
