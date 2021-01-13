(defun G (L)
    (MAPCON #'LIST L)
)

(PRINT (APPLY #'APPEND (MAPCON #'G '(1 2))))

(defun liniarize (L)
    (cond
        ((and (and (atom L) (numberp L)) (> (mod L 3) 0))  L)
        ((and (atom L) (not(numberp L))) L)
        ((and (atom L) (numberp L)) NIL)
        (T (MAPCAR #'liniarize L))
    )
)
(PRINT (liniarize '(1 (2 A (3 A)) 6)))

(defun list_sum (L)
    (cond
        ((null l) 0)
        (T (+ (car l) (list_sum (cdr l))))
    )
)


(DEFUN G (L) (LIST (CAR L) (CAR L)))
(SETQ Q 'G)
(SETQ P Q)
(PRINT (FUNCALL P '(A B C)))