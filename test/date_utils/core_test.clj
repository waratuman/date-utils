(ns date-utils.core-test
  (:use clojure.test
        date-utils.core)
  (:import java.util.GregorianCalendar))
  
(deftest date-test
  (is date)
  (is (= (GregorianCalendar. 2004 0 0)
         (date 2004)))
  (is (= (GregorianCalendar. 2004 1 0)
        (date 2004 2)))
  (is (= (GregorianCalendar. 2004 1 2)
        (date 2004 2 2)))
  (is (= (GregorianCalendar. 2004 0 0 0 0 0)
         (date 2004)))
  (is (= (GregorianCalendar. 2004 1 0 0 0 0)
        (date 2004 2)))
  (is (= (GregorianCalendar. 2004 1 2 0 0 0)
        (date 2004 2 2)))
  (is (= (GregorianCalendar. 2004 1 2 3 0 0)
         (date 2004 2 2 3)))
  (is (= (GregorianCalendar. 2004 1 2 3 4 0)
        (date 2004 2 2 3 4)))
  (is (= (GregorianCalendar. 2004 1 2 3 4 5)
        (date 2004 2 2 3 4 5))))

(deftest difference-test
  (is (= 86400
         (difference (date 2009 1 1) (date 2009 1 2))))
  (is (= -86400
        (difference (date 2009 1 2) (date 2009 1 1)))))
