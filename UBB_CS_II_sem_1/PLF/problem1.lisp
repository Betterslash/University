;a)
(defun get_len (lista)
    (cond
        ((null lista) 0)
        (T (+ 1 (get_len (cdr lista))))
    )
)

(defun get_n_elem (lista pos)
    (cond
        ((> pos (get_len lista)) nil)
        ((equal 1 pos) (car lista))
        (T (get_n_elem (cdr lista) (- pos 1)))
    )
)
(print (get_n_elem '(6 2 1 5 3 11 22) 6))

;b)
(defun is_elem (lista elem)
    (cond 
        ((null lista) nil)
        ((equal (car lista) elem) 1)
        (T (is_elem (cdr lista) elem))
    )
)
(print (is_elem '((1 2 3) 2 1 5 B 11 22) '(1 2 3 )))

;c)
(defun res_lists (lista)
    (cond
        ((null lista) nil)
        ((listp (car lista)) (append (cons (car lista) (res_lists (car lista))) (res_lists (cdr lista)))) 
        (T (res_lists (cdr lista)))
    )
)
(print (res_lists (list '(1 2 (3 (4 5) (6 7)) 8 (9 10)))))

;d)
(defun make_set (lista)
    (cond
    ((null lista) nil)
    ((equal (is_elem (cdr lista) (car lista)) nil) (cons (car lista) (make_set (cdr lista))))
    (T (make_set (cdr lista)))
    )
)
(print (make_set '(1 2 3 3 2 4 1 5 6 1)))