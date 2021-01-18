(defun sum_of_one_vector (vect)
    (cond
        ((null vect) 0)
        (T  ( + (sum_of_one_vector (cdr vect)) (car vect)))
    )
)

(defun sum_of_two_vectors (v_one v_two)
    (cond
        ((and (null v_one) (null v_two)) 0)
        ((null v_one) (+ (sum_of_one_vector v_two) (sum_of_two_vectors () ())))
        (T (+ (sum_of_one_vector v_one) (sum_of_two_vectors () v_two)))
    )
)
(print (sum_of_two_vectors '(1 2 3 4) '(5 6 7 8)))

(defun get_atoms (lista)
    (cond 
        ((null lista) nil)
        ((listp (car lista)) (append (get_atoms (car lista)) (get_atoms (cdr lista))))
        (T (cons (car lista) (get_atoms (cdr lista))))
    )
)
(print (get_atoms '((1 2 3 (4 (1) (1 2 34) 10) 5) 6 7 (8 9))))

(defun revs (lista ret_lista) 
    (cond 
        ((null lista) ret_lista)
        (T (revs (cdr lista) (cons (car lista) ret_lista)))
    )
)
(defun t_revs (lista)
    (revs lista ())
)

(defun resolv (lista ret_lista)
    (cond 
        ((null lista )  ret_lista)
        ((listp (car lista)) (resolv (cdr lista) (cons (resolv (car lista) ()) ret_lista)))
        (T (resolv (cdr lista) (cons (car lista) ret_lista)))
    )
)

(print (resolv '(A B (C) D E F (H I J (I O U (C D F) Z))) nil))
(defun rezolvare (l aux)
    (cond
        ((null l) (t_revs aux))
        ((listp (car l)) (append (t_revs aux) (cons (rezolvare (car l) nil) (rezolvare (cdr l) nil))))
        (T (rezolvare (cdr l) (append aux (list (car l)))))
    )
)

(print (rezolvare '(a b c (d (e f) g h i)) nil))