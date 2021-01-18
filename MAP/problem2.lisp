;a)
(defun dot_product (v_one v_two)
    (cond 
        ((or (null v_one) (null v_two)) 0)
        (T (+(* (car v_one) (car v_two)) (dot_product (cdr v_one) (cdr v_two))) )
    )
)
(print (dot_product '(1 3 -5 3) '(4 -2 -1 3)))

;b)
(defun depth (lista m_depth dpt)
    (cond
        ((null lista) dpt)
        ((listp (car lista)) (depth (car lista) (+ 1 m_depth) dpt))
        ((< dpt m_depth)(depth (cdr lista) m_depth m_depth))
        (T (depth (cdr lista) 0 dpt))
    )
)
(print (depth '(6 (2 (1 (4 (5))) 3) (3(4(5(6(7 (11 4 ( 22))) 8) 9) 99) 100) 4 5) 0 0))