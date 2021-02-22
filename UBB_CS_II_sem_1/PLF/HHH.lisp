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

(defun rez (e L lvl)
    (COND
        ((and (atom L) (equal (mod lvl  2) 1)) e)
        ((and (atom L) (not (equal (mod lvl 2) 1))) L)
        (T (MAPCAR (lambda (L) (rez e L (+ 1 lvl))) L))
    )
)
(print (rez 'H '(a (b (g) z) r q) 0))

(defun F (X &REST Y)
    (COND 
        ((NULL Y) X)
        (T (PRINT Y)(APPEND X (MAPCAR #'CAR Y)))
    )
)
(PRINT (F '(3 4) '(5 6) '(7 8)))
(PRINT (APPEND (F '(1 2)) (F '(3 4) '(5 6) '(7 8))))