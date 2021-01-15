(defun rez (k L lvl)
    (COND
        ((and (atom L) (equal lvl k)) 0)
        ((and (atom L) (not (equal lvl k))) (print L) L)
        (T (MAPCAR (lambda (L) (rez k L (+ 1 lvl))) L))
    )
)
(print (rez 2 '(a (b (g) z) r q) 0))