(defun gcd (A B)
    (cond
        ((> A B) (gcd (- A B) B))
        ((< A B) (gcd A (- B A)))
        ((equal A B) A)
    )
)

(defun get_atoms (lista)
    (cond
        ((null lista) nil)
        ((listp (car lista)) (append (get_atoms (car lista)) (get_atoms (cdr lista))))
        (T (cons (car lista) (get_atoms (cdr lista))))
    )
)

(defun lcm (A B)
    (/ (* A B) (gcd A B))
)

(defun len (lista) 
    (cond
        ((null lista) 0)
        (T (+ 1 (len (cdr lista))))
    )
)

(defun create_list (lista)
    (cond
        ((null lista) nil)
        ((and (and (and (numberp (cadr lista)) (not (numberp (car lista)))) (not (numberp (caddr lista)))) (> (len lista) 2)) (cons (cadr lista) (create_list (cdr lista))))
        (T (create_list (cdr lista)))
    )
)


(defun resolv (lista res)
    (cond
        ((null lista) res)
        (T (resolv (cdr lista) (lcm (car lista) res)))
    )
)

(defun t_resolv (lista)
    (resolv (create_list (get_atoms lista)) 1)
)
(print (t_resolv '(A B 12 (5 D (A F (15 B) D (5 F) 4)) C 9)))