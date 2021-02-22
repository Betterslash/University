(defun rez (L lvl)
    (COND
        ((ATOM L)
            (COND
                ((equal (MOD lvl 2) 0) 
                    (cond 
                        ((numberp L)(list L))
                        (T nil)
                    )
                )
                (T(list L))
            )
        )
        (T (MAPCAN (LAMBDA (l)
                (rez L (+ 1 lvl))
        ) L))
    )
)
(print (rez '(a (1 (2 b)) (c (d))) 0))