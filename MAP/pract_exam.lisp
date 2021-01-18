(defun G (L)
    (cond
        ((null L) 0)
        ((> (G (car L)) 2) (+ (car L) ( G (cdr L))))
        (T (G (car L)))
    )
)

(defun F (X &REST Y)
    (cond 
        ((NULL Y) X)
        (T (print Y)(APPEND X (MAPCAR #'CAR Y)))
    )
)
(print (F '(3 4) '(5 6) '(7 8)))