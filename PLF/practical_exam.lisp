(defun check_liniarity (lista)
    (cond
        ((null lista) 1)
        ((listp (car lista)) 0)
        (T (check_liniarity (cdr lista)))
    )
)

(defun num_of_elems (lista)
    (cond
        ((null lista) 0)
        (T (+ 1 (num_of_elems (cdr lista))))
    )
)
(defun parity (lista)
    (cond 
        ((equal (mod (num_of_elems lista) 2) 0) 1)
        (T 0)
    )
)

(defun cand_list (lista)
    (cond
        ((and (listp lista) (and (equal (check_liniarity lista) 1) (equal (parity lista) 1))) 2)
        ((and (listp lista) (equal (check_liniarity lista) 0)) 1)
        (T 0)
    )
)
(print (cand_list '(6 (7 8) ((7 9) 8) (6 8) 9)))
(defun resolv (lista)
    (cond
        ((null lista) nil)
        ((equal (cand_list (car lista)) 0) (cons (car lista) (resolv (cdr lista))))
        ((equal (cand_list (car lista)) 1) (print (car lista))(cons (resolv (car lista)) (resolv (cdr lista))))
        (T  (print (car lista)) (resolv (cdr lista)))
    )
)
(print (resolv '((2 3 4) (6 (7 8) ((7 9) 8) (6 8) 9 (10 11)))))
