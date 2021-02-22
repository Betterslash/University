(defun res (lista, elem, spos)
    (cond
    ((null lista) nil)
    ((equal(mod spos 2) 0)(list(lista, elem))(res lista elem + 1 spos))
    ())
)

(defun resolv (lista ret_lista)
    (cond 
        ((null lista) ret_lista)
        ((listp (car lista)) (print (resolv (cdr lista) ())) (list ret_lista (resolv (car lista) ())))
        (T (resolv (cdr lista) (cons (car lista) ret_lista)))
    )
)