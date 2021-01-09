(defun Fct (F L)
    (cond
        ((null L) nil)
        ;((FUNCALL F (car L)) (CONS (FUNCALL F (CAR L)) (Fct F (CDR L))))
        ((APPLY F L))
        (T nil)
    )
)
(print (Fct #'- '(1 2 3 4 5)))
(defun G (L) (LIST (CAR L) (CAR L)))
(setq Q 'G)
(setq P Q)
(print (FUNCALL P '(A B C)))


;search_r(e, (a1, ..., an)) = { (), if |a1, ..., an| = 0
;                            { true, if a1 = e
;                            { search_r(e, (a1, ..., an)), otherwise
(defun search_r (e L)
    (cond 
        ((null L) nil)
        ((equal e (car L)) T)
        ((search_r e (cdr L)))
    )
)

;liniarize((a1, ..., an)) = { (a1), if a1 is Atom
;                         = { U[i->n](a1, ..., an), if ai E (a1,...,an) 
(defun liniarize (L)
    (cond
        ((atom L) (list l))
        (T (MAPCAN #'liniarize L))
    )
)
(print (search_r 'Z (liniarize '(a (b (c)) (d) (E (f))))))