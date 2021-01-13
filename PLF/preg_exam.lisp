(defun Fct (F L)
    (cond
        ((null L) nil)
        ;((FUNCALL F (car L)) (CONS (FUNCALL F (CAR L)) (Fct F (CDR L))))
        ((APPLY F L))
        (T nil)
    )
)
;(print (Fct #'- '(1 2 3 4 5)))
;(defun G (L) (LIST (CAR L) (CAR L)))
;(setq Q 'G)
;(setq P Q)
;(print (FUNCALL P '(A B C)))


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
;(print (search_r 'Z (liniarize '(a (b (c)) (d) (E (f))))))

;replace every even atom with it + 1 using MAP
(defun next_value (L)
    (cond
        ((null L) nil)
        ((and (atom L) (and (numberp L) (equal (mod L 2) 0)))(print L) (+ 1 L))
        ((and (atom L) (or (not (numberp L)) (not (equal (mod L 2) 0)))) L)
        (T (MAPCAR #'next_value L))
    )
)
(print (next_value '(1 s 4 (2 f (7)))))

(defun G (F L)
    (FUNCALL F L)
)
(print (G #'(lambda (L) (G #'CDR L)) '(1 2 3)))