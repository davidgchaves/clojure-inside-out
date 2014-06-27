;; 01 - using LOOP and RECUR
(defn zipm01 [keys values]
  (loop [m {} ks (seq keys) vs (seq values)]    ;; loop uses let underneath
    (if (and ks vs)
      (recur (assoc m (first ks) (first vs)) (next ks) (next vs))
      m)))

(zipm01 [:a :b :c] [1 2 3])

;; recur rebinds and jumps to nearest:
;;  - loop, or
;;  - function frame


;; 02 - using LOOP and RECUR with DESTRUCTURING
(defn zipm02 [keys values]
  (loop [m {} [k & ks :as keys] (seq keys) [v & vs :as vals] (seq values)]
    (if (and keys vals)
      (recur (assoc m k v) ks vs)
      m)))

(zipm02 [:a :b :c] [1 2 3])

;; :as directive --> Bind the entire vector to a local


;; 03 - using REDUCE and MAP
(defn zipm03 [keys values]
  (reduce (fn [m [k v]] (assoc m k v))
          {} (map vector keys values)))

(zipm03 [:a :b :c] [1 2 3])


;; 04 - using DATA CONSTRUCTOR function
(defn zipm04 [keys values]
  (apply hash-map (interleave keys values)))

(zipm04 [:a :b :c] [1 2 3])


;; 05 - using INTO
(defn zipm05 [keys values]
  (into {} (map vector keys values)))

(zipm05 [:a :b :c] [1 2 3])


;; 06 - cheating
(defn zipm06 [keys values]
  (zipmap keys values))

(zipm06 [:a :b :c] [1 2 3])

