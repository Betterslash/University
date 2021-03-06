; (defun F (L)
;    (COND
;        ((ATOM L) -1)
;        ((> (F (CAR L)) 0) (+ (CAR L)(F(CAR L))) (F (CDR L)))
;        (T (F (CDR L)))
;     )
; )
; (defun f (l)
;     (cond
;         ((null l) 0)
;         ((> (f (car l)) 2) (+ (car l) (f (cdr l))))
;         (T (f (car l)))
;     )
; )

; (defun f (l)
;     (cond
;         ((null l) 0)
;         ((LAMBDA (ff) 
;             (> ff 2) (+ (car l) (f (cdr l)))
;             (T ff)) 
;             (F (car L))
;         )
;     )
; )

; (DEFUN F (L)
;     (COND 
;         ((ATOM L) -1)
;         ((lambda (ff) 
;             (> ff 0) (+ (car L) ff) (F (cdr L))
;             (T (F (cdr L)))
;             )(F (car L))
;         )
;     )
; )
; (print (F '(4 5)))

; (DEFUN G (Fc L)
;     (FUNCALL Fc L)
; )
; (PRINT (G #'(lambda (L) (G #'CDR l)) '(1 2 3)))

; (defun F (L)
;     (cond
;         ((null L) nil)
;         ((and (atom L) (numberp L))
;             (cond
;                 ((equal (mod L 2) 0) (+ 1 L))
;                 ((equal (mod L 2) 1)  L)
;             )
;         )
;         ((and (atom L) (not (numberp L)))
;             L
;         )
;         (T (MAPCAR #'F L))
;     )
; )
; (defun F (e L)
;     (cond
;         ((atom L)
;             (cond
;                 ((not(equal e L))  L)
;             )
;         )
;         (T (MAPCAR (lambda (L) (F e L)) L))
;     )
; )
; (DEFUN atomi (L)
;     (COND
;         ((ATOM L) (LIST L))
;         (T (MAPCAN #'atomi L))
;     )   
; )
; (DEFUN LGT (L)
;     (COND 
;         ((NULL L) 0)
;         (T (+ 1 (LGT (CDR L))))
;     )
; )
; (DEFUN IS_NUMBER (L)
;     (COND
;         ((equal (lgt L) 1) 
;             (cond
;                 ((numberp (car L)) T)
;                 ((not (numberp (car L))) nil)
;             )
;         )
;         (T (IS_NUMBER (cdr L)))
;     )
; )
; (defun F (L R)
;     (cond
;         ((ATOM L) R)
;         ((LISTP L) (PRINT L)
;             (COND 
;                 ((EQUAL(IS_NUMBER (ATOMI L)) NIL)  (SETQ R (+ 1 R)))
;             )
;                 (MAPCAR (lambda (L) 
;                     (F L R))
;             L))
;     )
; )
; (defun rez (L)
;     (cond
;         ((NULL L) 0)
;         ((not (equal (car L) 1)) (+ 1 (rez (cdr L))))
;         (T (rez (cdr L)))
;     )
; )
; (print (IS_NUMBER '(1 2 3 4 A)))
; (print (IS_NUMBER '(1 2 3 4)))
; (print (F '(A 2 (B 2) (1 C 4) (D 1 (6 (F))) ((G 4 Z (Q (P))) 6) F) 0))

; (SET 'L '(NIL T NIL))
; (PRINT(OR T NIL T))
; (PRINT (FUNCALL (LAMBDA (L)
;      (OR (CAR L) (CADR L) (CADDR L))) L))
; (DEFUN Fr (L E N)
;     (COND
;         ((NULL L) NIL)
;         ((EQUAL E N) (Fr (CDR L) 1 N))
;         (T (CONS (CAR L) (Fr (CDR L) (+ 1 E) N)))
;     )
; )
;(print (Fr '(1 2 3 4 5) 1 2))
(defun nratm (L)
    (cond
        ((null L) 0)
        ((atom (car L)) (+ 1 (nratm (cdr L))))
        (T (nratm (cdr L)))
    )
)
(defun nrimpar (L)
    (cond
        ((equal (mod (nratm L) 2) 1) T)
        (T nil)
    )
)
(defun check (L lvl)
    (cond
        ((null L) nil)
        ((ATOM L) 
            (cond 
                ((equal (mod lvl 2) 0) L)
            )
        )
        (T (MAPCAR (lambda (L)
            (check L (+ 1 lvl))) 
            L))
    )
)
(defun checkLvl (L lvl)
    (cond
        ((null L) nil)
        ((ATOM L) 
            (cond 
                ((equal (mod lvl 2) 1) L)
            )
        )
        (T (MAPCAR (lambda (L)
            (checkLvl L (+ 1 lvl))) 
            L))
    )
)
; (defun fil (L)
;     (COND
;         ((NULL L) NIL)

;     )
; )
(DEFUN Fe (L lvl)
    (COND
        ((ATOM L) L)
        ((LISTP L) (PRINT L) (MAPCAR (lambda (L)
            (Fe L (+ 1 lvl))
        ) L))
    )
)
(print (Fe (checkLvl '(A (B 2) (1 C 4) (1 (6 F)) (((G) 4) 6) B C D) 0 ) 0))

(defun A (L)
    (COND
        ((ATOM L) 
            (COND 
                ((numberp L) 
                    (COND
                        ((EQUAL (MOD L 2) 0) (+ 1 L))
                        (T L)
                    )
                )
                (T L)
            )
        )
        (T (MAPCAR #'A L))
    )
)
(PRINT (A '(1 S 5 (3 F (7)))))

(DEFUN B (L)
    (COND
        ((NULL L) NIL)
        ((ATOM (CAR L))
            (COND 
                ((numberp (CAR L)) 
                    (COND
                        ((EQUAL (MOD (CAR L) 2) 0) (+ 1 (CAR L)))
                        (T (CONS (CAR L) (B (CDR L))))
                    )
                )
                (T (CONS (CAR L) (B (CDR L))))
            )
        )
        ((LISTP (CAR L)) (CONS (B (CAR L)) (B (CDR L))))
    )
)
(PRINT (B '(1 S 5 (3 F (7)))))

(defun O (L)
    (COND    
        ((ATOM L) 
            (COND
                ((NUMBERP L)
                    (COND
                        ((EQUAL (MOD L 3) 0) NIL)
                        (T L)
                    )
                )
                (T L)
            )
        )
        (T (MAPCAR #'O L))
    )
)

(PRINT (O '(1 (2 A (3 A)) (6))))
(PRINT (O '(1 (2 C))))

(defun G (L lvl)
    (COND
        ((ATOM L) 
            (COND
                ((EQUAL (MOD LVL 2) 0)
                    (COND
                        ((NUMBERP L) 
                            (COND
                                ((EQUAL (MOD L 2) 1) L)
                                (T -1)
                            )
                        )
                        (T -1)
                    )
                )
                (T -1)
            )
        )
        (T (MAPCAR (LAMBDA (L) (G L (+ 1 lvl))) L))
    )
)
(DEFUN liniarize (L)
    (COND
        ((ATOM L) 
            (COND
                ((EQUAL L -1) nil)
                (T (list L))
            )
        )
        (T (MAPCAN #'liniarize L))
    )
)
(defun gcR (A B)
    (COND
        ((= A B) A)
        ((= A 0) B)
        ((= B 0) A)
        ((> A B) (gcR (- A B) B))
        ((< A B) (gcR A (- B A)))
    )
)
(DEFUN GCL (L X)
    (COND
        ((EQUAL (LENGTH L) 1) (PRINT L)(GCR (CAR L) X))
        (T (GCL (CDR L) (gcR (CAR L) X)))
    )
)
(DEFUN T_GCL (L)
    (GCL L (CAR L))
)
(PRINT (GCL '(12 6 18 12) 12))
(print (T_GCL (liniarize(G '(A B 12 (9 D (A F (75 B) D (45 F) 1) 15) C 9) 0))))

