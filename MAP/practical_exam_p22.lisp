(defun get_numbers (lista level)
    (cond
        ((null lista) nil)
        ((listp (car lista))(append (get_numbers (car lista) (+ 1 level)) (get_numbers (cdr lista) level)))
        ((equal (MOD level 2) 0)(cons (car lista) (get_numbers (cdr lista) level)))
        (T (get_numbers (cdr lista) level))
    )
)
(print (get_numbers '(A B (9 D (A F (75 B (Z)) D (45 F) 1) 15) C 1 9) 1))

(defun filter_numbers (lista)
    (cond
        ((null lista) nil)
        ((numberp (car lista)) (cons (car lista )(filter_numbers (cdr lista))))
        (T (filter_numbers (cdr lista)))
    )    
)
(print (filter_numbers (get_numbers '(A B (9 D (A F (75 B (Z)) D (45 F) 1) 15) C 1 9) 1)))