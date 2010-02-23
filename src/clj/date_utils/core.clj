(ns date-utils.core
  (:import date_utils.ISO8601DateFormatter))

(defn parse [string]
  (ISO8601DateFormatter/parse string))
