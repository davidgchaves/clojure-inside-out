;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; from MAPS to RECORDS (using DEFRECORD) with the same API ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;
;; maps (data oriented) ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;
(def stu1 {:fname "Stu" :lname "Halloway"
           :address {:street "200 N Mangum"
                     :city "Durham"
                     :state "NC"
                     :zip 27701}})

;; keyword lookup
(:lname stu1)               ;; => "Halloway"

;; nested access
(-> stu1 :address :city)    ;; => "Durham"

;; update
(assoc stu1 :fname "Stuart")
;; => {:address {:city "Durham", :state "NC", :street "200 N Mangum", :zip 27701},
;;     :fname "Stuart",
;;     :lname "Halloway"}

;; nested update
(update-in stu1 [:address :zip] inc)
;; => {:address {:city "Durham", :state "NC", :street "200 N Mangum", :zip 27702},
;;     :fname "Stu",
;;     :lname "Halloway"}


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; records (object-oriented like) ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; defrecord - the form for creating named types that contain domain information ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defrecord Person [fname lname address])
(defrecord Address [street city state zip])

;; using Java constructors (Person.) (Address.)
(def stu2 (Person. "Stu" "Halloway"
                   (Address. "200 N Mangum"
                             "Durham"
                             "NC"
                             27701)))

;; still data oriented: everything works as before

;; keyword lookup
(:lname stu2)               ;; => "Halloway"

;; nested access
(-> stu2 :address :city)    ;; => "Durham"

;; update
(assoc stu2 :fname "Stuart")
;; => #user.Person{:fname "Stuart",
;;                 :lname "Halloway",
;;                 :address #user.Address{:street "200 N Mangum", :city "Durham", :state "NC", :zip 27701}}

;; nested update
(update-in stu2 [:address :zip] inc)
;; => #user.Person{:fname "Stu",
;;                 :lname "Halloway",
;;                 :address #user.Address{:street "200 N Mangum", :city "Durham", :state "NC", :zip 27702}}

