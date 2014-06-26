;; Playing alone with the video

(fn [x] (* x x))                      ;; forget to name the function
(def cool *1)                         ;; *1 is the last thing I did (evaluate the function)
(cool 10)                             ;; --> 100
(map cool (range 10))                 ;; --> (0 1 4 9 16 25 36 49 64 81)
(filter even? (map cool (range 10)))  ;; --> (0 4 16 36 64)
(reduce + (filter even? (map cool (range 10))))  ;; --> 120
