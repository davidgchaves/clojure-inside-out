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

