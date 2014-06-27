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


;; SEQUENCE COMPREHENSIONS - FOR MACRO (You can live without it, it's a personal preference)
(for [x (range 2) y (range 3)] [x y]) ;; => ([0 0] [0 1] [0 2] [1 0] [1 1] [1 2])

(take 10 (for [x (range 1000000) y (range 1000000) :while (< y x)]
           [x y]))
;; => ([1 0] [2 0] [2 1] [3 0] [3 1] [3 2] [4 0] [4 1] [4 2] [4 3])


;; VECTORS
(def v [42 :rabbit [1 2 3]])

;; vectors are actually functions of themselves, so
(v 0)     ;; => 42
(v 1)     ;; => :rabbit
(v 2)     ;; => [1 2 3]

(peek v)  ;; => [1 2 3]
(pop v)   ;; => [42 :rabbit]

;; subvector starting in element 1 (= :rabbit)
(subvec v 1)  ;; => [:rabbit [1 2 3]]

;; GOTCHA: contains? is a function of an associative collection asking "is the key present?"
;;         vectors are associative collections
(contains? v 0)       ;; => true
(contains? v 1)       ;; => true
(contains? v 2)       ;; => true
(contains? v 3)       ;; => false

(contains? v 42)      ;; => false (GOTCHA)
(contains? v :rabbit) ;; => false (GOTCHA)
(contains? v [1 2 3]) ;; => false (GOTCHA)


;; MAPS
(def m {:a 1 :b 2 :c 7})

;; maps are actually functions of themselves, so
(m :a)    ;; => 1
(m :b)    ;; => 2
(m :c)    ;; => 7

;; ...but a keyword could become a function on a map, so
(:a m)    ;; => 1
(:b m)    ;; => 2
(:c m)    ;; => 7

(keys m)              ;; => (:c :b :a)
(assoc m :d 9 :c 42)  ;; => {:c 42, :b 2, :d 9, :a 1}
(dissoc m :d)         ;; => {:c 7, :b 2, :a 1}

(merge-with + m {:a 2 :b 3}) ;; => {:c 7, :b 5, :a 3}
;; a was 1, but 1+2 is 3, so a now is 3
;; b was 2, but 2+3 is 5, so b now is 5

(merge-with + m {:d 5})     ;; => {:c 7, :b 2, :d 5, :a 1}


;; NESTED STRUCTURES
(def jdoe {:name "John Doe"
           :address {:zip 27705 :street "Main Street" :number 24}})

(get-in jdoe [:address :zip])     ;; => 27705
(get-in jdoe [:address :number])  ;; => 24
(get-in jdoe [:name])             ;; => "John Doe"

(assoc-in jdoe [:address :zip] 27514)
;; => {:address {:number 24, :street "Main Street", :zip 27514}, :name "John Doe"}

(update-in jdoe [:address :zip] inc)
;; => {:address {:number 24, :street "Main Street", :zip 27706}, :name "John Doe"}


;; SETS
(def colors #{"red" "green" "blue"})
(def moods  #{"happy" "blue"})

(disj colors "red")     ;; => #{"blue" "green"}

(clojure.set/difference   colors moods)   ;; => #{"green" "red"}
(clojure.set/intersection colors moods)   ;; => #{"blue"}
(clojure.set/union        colors moods)   ;; => #{"blue" "green" "red" "happy"}

