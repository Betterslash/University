; (defun lin (L)
;     (COND
;         ((NULL L) NIL)
;         ((LISTP (CAR L)) (append (lin (CAR L)) (lin (CDR L))))
;         (T (cons (car l)(lin (CDR L))))
;     )
; )
(defun lin (L)
    (cond
        ((NULL L) NIL)
        (t(lambda (ff)
            (COND     
                ((LISTP (CAR L))(append (lin (CAR L)) ff))
                (T (cons (car l) ff))
            )
        ) (lin (CDR L)))
    )
)

(PRINT (lin '(1 2 (3 (4 (5))))))

(defun F (L lvl K)
    (cond
        ((ATOM L)
            (cond
                ((equal lvl k) 0)
                (T L)
            )
        )
        (T (MAPCAR (LAMBDA (L)
                        (F L (+ 1 lvl) K)
                    )
            L
            )
        )
    )
)
(print (F '(a (1 (2 b)) (c (d))) 0 2))
(defun nonA (L)
    (cond
    ((null L) 0)
    ((and (atom (car L)) (not (numberp (car L)))) (+ 1 (nonA (cdr L))))
    (T (nonA (cdr L)))
    )
)
(defun elvl (L lvl)
    (cond
        ((atom L) 
            (cond
                ((equal (mod lvl 2) 0) (list L))
                (T nil)                
            )
        )
        (T (mapcan (lambda (L)
                    (elvl L (+ 1 lvl))
                    ) L
        ))
    )
)
(defun check (L)
    (cond 
        (T (equal (mod (nonA (elvl L 0)) 2) 1))
    )
)
(defun rez (L)
    (cond
        ((atom l) nil)
        (t(cons L (mapcan #'rez L)))
    )
)
(defun t_rez (L)
    (cond
        ((null l) 0)
        ((listp (car L)) 
            (cond
                ((equal (check (car L)) T)(PRINT (CAR L)) (+ 1 (t_rez (cdr L))))
                (T (t_rez (cdr L)))
            )
        )
        (T (t_rez (cdr L)))
    )
)

(print (t_rez (rez '(A (B 2) (1 C 4) (1 (6 F)) (((G) 4) 6)))))