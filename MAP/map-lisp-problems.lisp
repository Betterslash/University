((lambda (l) (cons (car l) (cdr l))) '(1 2 3))
(defun f (l)
    (cond
        ((null l) t)
        (((lambda (v)
            (cond
                ((numberp v) t)
                (t nil)
            )
        )
        (car l)
        ) nil)
        (t (f (cdr l)))
    )   
 )

(defun f (lista)
    (cond 
        ((null lista) nil)
        ((listp (car lista)) (f (car lista)))
        (T (cons (car lista) (f (cdr lista))))
    )
)
 (print (MAPCAN #'F '((A B C) (X Y (T M (N)) Z) (O V (G (H (I)))))))


(DEFUN atomi (L)
    (COND
        ((ATOM L) (LIST L))
        (T (MAPCAN #'atomi L))
    )   
)
(print (atomi '(a (b (c)) (d) (E (f))))) 

(defun rez (lista nod)
    (cond 
        ((null lista) nil)
        ((equal (car lista) nod) T)
        (T (rez (cdr lista) nod))
    )
)
(print (rez (atomi '(a (b (c)) (d) (E (f)))) 'p))