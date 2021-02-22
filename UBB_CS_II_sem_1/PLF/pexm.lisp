(defun liniarize3 (L)
    (cond
        ((AND (atom L) (not (numberp L))) nil)
        ((AND (atom L) (numberp L)) (list l))
        (T  (PRINT L) (MAPCAN #'liniarize3 L))
    )
)

(defun liniarize2 (lvl L)
    (cond
        ((and (atom L) (equal (mod lvl 2) 1)) (list l))
        ((and (atom L) (equal (mod lvl 2) 0)) nil)
        (T (MAPCAN #'(lambda (L)(liniarize2 (+ 1 lvl) L)) L))
    )
)
(print (liniarize3 '(a (b (1 (4 (4)))) (d) (E (f)))))