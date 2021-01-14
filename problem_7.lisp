(defun subs (e l1 l2)
    (cond
        ((null l1) nil) 
        ((equal l1 e) l2)
        ((atom l1) l1)
        (T  (mapcar #'(lambda (l1)
                (subs e l1 l2)) l1
            )
        )
    )
)

(print (subs 4 '(1 2 3 4 5 (6 (4) 4) 5) '(z z z)))