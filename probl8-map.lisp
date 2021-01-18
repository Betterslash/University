;Write a function to determine the number of nodes on the level k from a n-tree represented as follows:
;(root list_nodes_subtree1 ... list_nodes_subtreen)
;Eg: tree is (a (b (c)) (d) (e (f))) and k=1 => 3 nodes

(defun lista (L n)
    (cond
        ((and (= n 0) (atom L)) (list L))
        ((= n 0) nil)
        ((atom L) nil)
        (t (mapcan #'(lambda(L)
                        (lista L (- n 1))
                     )
                     L
           )
        )
    )
)


(print (lista '(a (b (c)) (d) (e (f))) 2))