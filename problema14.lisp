;14. Determine the list of nodes accesed in postorder in a tree of type (1).
;parcurg_st ->
;parcurg_st((l1, ..., ln) nv nm) = { (), arb = ()
;                        { (), nv = nm + 1
;                        { l1 U l2 U parcurg_st((l3, l4, ...,ln) (nv + 1) (nm + l2))
(defun parcurg_st (arb nv nm)
 (cond
    ((null arb) nil)
    ((= nv (+ 1 nm)) nil)
    (t (cons (car arb) (cons (cadr arb) (parcurg_st (cddr arb) (+ 1 nv) (+ (cadr arb) nm)))))
 )
)
(defun stang (arb)
 (parcurg_st (cddr arb) 0 0)
)


;parcurg_dreapta ->
;parcurg_dreapta((l1, ..., ln) nv nm) = { (), arb = ()
;                        { l1 U l2 U parcurg_dreapta((l3, l4, ...,ln) 1 0) ,if nm + 1 = nv 
;                        { l1 U l2 U parcurg_dreapta((l3, l4, ...,ln) (nv + 1) (nm + l2))
(defun parcurg_dreapta (arb nv nm)
 (cond
    ((null arb) nil)
    ((= nv (+ 1 nm)) (cons (car arb) (cons (cadr arb) (parcurg_dreapta (cddr arb) 1 0))))
    (T (parcurg_dreapta (cddr arb) (+ 1 nv) (+ (cadr arb) nm)))
 )
)
(defun drept (arb)
 (parcurg_dreapta (cddr arb) 0 0)
)

;post_order(arbore) = { (), if arbore = ()
;                     { (post_order(stang(l1,...,ln)) U post_order(drept(l1,...,ln))) U l1
(defun post_order (arbore)
    (cond 
        ((null arbore) nil)
        (T (append (cons (post_order (stang arbore)) (post_order (drept arbore))) (list(car arbore))))
    )
)

;get_atoms((l1,...,ln)) = { (), |(l1, ..., ln)| = 0
;                         { get_atoms(l1) U get_atoms((l2, ..., ln)), l1 isList = True
;                         { l1 U get_atoms((l2, ..., ln)), else
(defun get_atoms (lista)
    (cond 
        ((null lista) nil)
        ((listp (car lista)) (append (get_atoms (car lista)) (get_atoms (cdr lista))))
        (T (cons (car lista) (get_atoms (cdr lista))))
    )
)
(defun t_post_order (arb)
    (get_atoms (post_order arb))
)

(print (t_post_order'(a 2 b 2 c 1 i 0 f 1 j 2 m 0 n 0 d 2 e 0 h 0)))
;;      a
;;   /    \
;;  b      d
;; / \    / \
;;c   f  e  h
;;/  /
;;i  j  