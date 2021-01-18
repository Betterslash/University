;3.Define a function to tests the membership of a node in a n-tree represented as (root
;  list_of_nodes_subtree1 ... list_of_nodes_subtreen)
;  Eg. tree is (a (b (c)) (d) (E (f))) and the node is "b" => true

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

(defun rezolv (e L)
    (search_r e (liniarize L))
)
    
(print (rezolv 'E '(a (b (c)) (d) (E (f)))))