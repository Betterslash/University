;a) Write a function that inserts in a linear list a given atom A after the 2nd, 4th, 6th, ... element.
;insert((l1, ..., ln), elem, pos) = { (), |(l1, ..., ln)| = 0
;                                   { elem U l1 U isnert((l2, ..., ln), elem, pos + 1), pos % 2 = 0
;                                   {  l1 U isnert((l2, ..., ln), elem, pos + 1), pos % 2 = 0, else
(defun insert (lista elem pos)
    (cond 
        ((null lista) nil)
        ((equal(mod pos 2) 0) 
            (cons elem (cons (car lista) (insert (cdr lista) elem (+ 1 pos))))
        )
        (T (cons (car lista) (insert (cdr lista) elem (+ pos 1))))
    )
)
(defun rez (lista elem)
    (insert lista elem 1)
)
;b) Write a function to get from a given list the list of all atoms, on any

; add_list((l1, ..., ln), (a1, ..., an)) = { (), |(l1, ..., ln)| = 0 and |(a1, ..., an)| = 0
;                                          { l1 U add_list((l2, ..., ln), (a1, ..., an)), |(l1, ..., ln)| != 0
;                                          { a1 U add_list((l1, ..., ln), (a2, ..., an)), else
(defun add_list (lista1 lista2)
    (cond
        ((and (null lista1) (null lista2)) nil)
        ((not (null lista1))(cons (car lista1) (add_list (cdr lista1) lista2)))
        (T (cons (car lista2) (add_list lista1 (cdr lista2))))
    )
)
;per_lista((l1,...,ln)) = { (), |(l1, ..., ln)| = 0
;                         { add_list(per_lista(l1), per_lista((l2, ..., ln))), l1 isList = True
;                         { l1 U per_lista((l2, ..., ln)), else
(defun per_lista (lista)
    (cond 
        ((null lista) nil)
        ((listp (car lista)) (add_list (per_lista (car lista)) (per_lista (cdr lista))))
        (T (cons (car lista)(per_lista (cdr lista))))
    )
)

;reve((l1, ..., ln), (a1, ..., an)) = { (a1, ..., an), if |(l1, ..., ln)| = 0
;                                     { reve((l2, ..., ln), l1 U (a1, ..., an)), else
(defun reve (lista lista1)
    (cond
        ((null lista) lista1)
        (T  (reve (cdr lista) (cons (car lista) lista1)))
    )
)
 ;c) Write a function that returns the greatest common divisor of all numbers in a nonlinear list
;gcdP(a, b) = { (), a, b not number
;             { a, b not number
;             { b, a not number
;             { a, b = 0
;             { b, a = 0
;             { gcdP((a - b), b), if a > b
;             { gcdP(a, (b - a)), if b > a
;             { a, a = b
(defun gcdP (A B)
    (cond 
        ((and (not (numberp A)) (not (numberp B))) nil)
        ((not (numberp A)) B)
        ((not (numberp B)) A)
        ((equal A 0) B)
        ((equal B 0) A)
        ((> A B) (gcdP (- A B) B))
        ((< B A) (gcdP A (- B A)))
        (T (equal A B) A)
    )
)

; len_lista((l1, ..., ln)) = { 0, |(l1, ..., ln)| = 0
;                            { 1 + len_lista((l2, ..., ln)), else
(defun lenlista (lista)
    (cond 
        ((null lista) 0)
        (T (+ 1 (lenlista (cdr lista))))
    )
)

;gcd_lista((l1, ..., ln) enter) = { 0, len_lista((l1, ..., ln)) = 0
;                                 { gcd_lista((l2, ..., ln) gcdP(enter l1)), else
(defun gcd_lista (lista enter)
    (cond 
        ((equal (lenlista lista) 0) enter)
        (T(gcd_lista (cdr lista) (gcdP enter (car lista))))
    )
)

;d) Write a function that determines the number of occurrences of a given atom in a nonlinear list.
;occur((l1, ..., ln) at) = { 0, |(l1, ..., ln)| = 0
;                          { 1 + occur((l2, ..., ln), at), if at is_atom and at = l1
;                          { occur((l2, ..., ln) at) , else
(defun occur (lista at)
    (cond
        ((null lista) 0)
        ((and (atom (car lista)) (equal (car lista) at)) (+ 1 (occur (cdr lista) at)))
        (T (occur (cdr lista) at))
    )
)

(print( rez '(1 2 3 4 5 6) 22))
(print(reve ( per_lista '(A (44 (11 2)) 22 33 C)) ()))
(print (gcd_lista '(C 33 22 242 121 44 A) 0))
(print (occur (per_lista'(1 (3 (5 4 3) (5 3)) 3 3 (3 1 3))) 3))

(defun fct (lista)
    (cond
        ((null lista) 1)
        ((listp (car lista)) (fct (car lista)) (fct (cdr lista)))
        (T (* (car lista) (fct (cdr lista))))
    )
)

(print (fct '(1 2 (3 4))))