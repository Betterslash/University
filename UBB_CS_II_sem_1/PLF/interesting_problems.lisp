; (defun F (L)
;     (COND 
;         ((ATOM L) L)
;         (T (APPLY #'* (MAPCAN #'F L)))
;     )
; )
; (PRINT (F '(1 2 (3 (4)))))

(DEFUN NRAP (E L)
    (COND
        ((ATOM L)
            (COND
                ((EQUAL L E) 1)
                (T 0)
            )
        )
        (T (APPLY #'+ (MAPCAR 
                        (LAMBDA (L)
                            (NRAP E L)
                        ) 
                        L
        )))
    )
)
(PRINT (NRAP 'a '(1 (a (3 (4 a) a )) (7 (a 9)))))
(DEFUN G (L) 
    (COND
        ((ATOM L) 
            (COND 
                ((NUMBERP L)
                    (COND
                        ((< L 0) NIL)
                        (T (LIST L))
                    )
                )
                (T (LIST L))
            )
        )
        (T (LIST  (MAPCAN #'G L)))
    )
)
(PRINT (G '(a 2 (b -4 (c -6)) -1)))
(DEFUN H (L)
    (COND
        ((ATOM L) 1)
        (T (APPLY #'+ (MAPCAR #'H L)))
    )
)
(PRINT (H '(a (b (c) (d (e))) (f (g)))))
(DEFUN DPT (A lvl)
    (COND
        ((ATOM A) lvl)
        (T (APPLY #'MAX (MAPCAR
            (LAMBDA (A)
                (DPT A (+ 1 lvl))
            ) A
        )))
    )
)
(PRINT (DPT '(a (b (c) (d (e))) (f (g))) -1))

(defun I (L clvl ilvl)
    (COND 
        ((ATOM L) 
            (COND
                ((EQUAL clvl ilvl) (list L))
                (T NIL)
            )
        )
        (T (MAPCAN 
                    (LAMBDA (L)
                        (I L (+ 1 clvl) ilvl)
                    )
                    L
        ))
    )
)
(PRINT (I '((a (b (c d))) e (f (g h (i)))) 0 3))
(print (I '((a (b (c d))) e (f (g h (i)))) 0 4))
(defun subm (L)
    (cond
        ((null L) (list nil))
        (t ((lambda (s)(append s (mapcar #'(lambda (sb)(cons (car L) sb))s)))(subm(cdr L))))
    )
)
(PRINT (subm '(1 2)))

(defun permutari (L) 
    (cond
        ((null (cdr L))(list L)) 
        (t (mapcan #'(lambda (e)(mapcar #'(lambda (p) (cons e p))(permutari(remove e L))))L))
    )
)
(PRINT (permutari '(1 2 3)))