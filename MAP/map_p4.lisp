;Write a function that returns the product of numeric atoms in a list, at any level.
(DEFUN product (L)
    (COND
        ((NULL L) 0)
        ((atom L) L)
        (T (APPLY #'* (MAPCAR #'product L)))
    )   
)

(DEFUN remove_elem (L e)
    (COND
        ((NULL L) nil)
        ((and (atom L) (not (equal L e))) (LIST L))
        ((atom L) nil)
        (T (APPLY #'APPEND (MAPCAR #'(lambda (L)
        (remove_elem L e)) L)))
    )   
)

(DEFUN SUM_EVEN_ODD (L)
    (COND
        ((NULL L) 0)
        ((atom L) 
            (cond 
                ((equal (mod L 2) 0) L)
                ((equal (mod L 2) 1) (* -1 L))
            )
        )
        (T (APPLY #'+ (MAPCAR #'SUM_EVEN_ODD L)))
    )   
)

(DEFUN det_max (L)
    (COND
        ((NULL L) 0)
        ((atom L) L)
        (T (APPLY #'MAX (MAPCAR #'det_max L)))
    )   
)

(print (remove_elem '(1 4 (2 (3 5 6 (2)))) 2))
