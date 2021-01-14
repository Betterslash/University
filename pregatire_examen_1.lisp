;(defun F (L)
 ;   (COND
  ;      ((NULL L) 0)
   ;     ((> (F (CAR L)) 2) (+ (CAR L) (F (CDR L))))
    ;    (T (F (CAR L)))
    ;)
;)

(defun F (L)
    ((lambda (ff) 
        (COND
            ((NULL L) 0)
            ((> ff 2) (+ (CAR L) (F (CDR L))))
            (T ff)
        ))
    (F (car L)))
)
(print (F '((4 5))))