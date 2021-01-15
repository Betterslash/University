revres_list([], X, X).
revres_list([H|T], X, R):-
    revres_list(T, [H|X], R)
.

t_reverse(L, R):- revres_list(L, [], R).

resol([], X, X).
resol([H|T], X, E):-
    is_list(H),!,
    t_reverse(H, R),
    resol(T, [R|X], E).
resol([H|T], X, E):-
    resol(T, [H|X], E)
.

t_resol(L, R):- resol(L, [], R).

%----------------------------------------------------------------%
%                       BACKTRACKING                             %
%----------------------------------------------------------------%



% subsets(l1...ln) =
% 	[], n = 0
% 	l1 + subsets(l2...ln), n > 0
% 	subsets(l2...ln), n > 0

% subsets(L:list, R:list)
% subsets(i, o)
subsets([], []).
subsets([H|T], [H|R]):-
    subsets(T, R)
.
subsets([_|T], R):-
    subsets(T, R)
.

% inserare(l1...ln, e) =
% 	[e], n = 0
% 	e + l1...ln, n >= 1
% 	l1 + inserare(l2...ln, e), otherwise

% inserare(L:list, E:number, R:list)
% inserare(i, i, o)

inserare([], E, [E]).
inserare([H|T], E, [E,H|T]).
inserare([H|T], E, [H|R]) :- 
         inserare(T, E, R).

% permutari(l1...ln) =
% 	[], n = 0
% 	inserare(permutari(l2...ln), l1), otherwise

% permutari(L:list, R:list)
% permutari(i, o)

permutari([], []).
permutari([H|T], R) :-
    permutari(T, RP),
    inserare(RP, H, R).

% combinari(l1...ln, k) =
% 	[], k = 0
% 	l1 + combinari(l2...ln, k - 1), k > 0
% 	combinari(l2...ln, k), k > 0

% combinari(L:list, K:number, R:list)
% combinari(i, o)

combinari(_, 0, []).
combinari([H|T], K, [H|R]) :-
    K > 0,
    NK is K - 1,
    combinari(T, NK, R).
combinari([_|T], K, R) :-
    K > 0,
    combinari(T, K, R).



% allsolutions(L:list, N:number, R:list)
% allsolutions(i, i, o)

allsolutions(L, N, R) :-
    findall(RPartial, combinari(L, N, RPartial), R).

%---------------------------------------------------%
%                     Problmes                      %
%---------------------------------------------------%

aranjamente(L, K, V, R):-
    combinari(L, K, Rez),
    product_list(Rez, X),
    X >= V,
    permutari(Rez, Rez1).

product_list([], 1).
product_list([H|T], R):-
    product_list(T, R1),
    R is R1 * H
.

aranjamente(L, K, R):-
    combinari(L, K, Rez),
    permutari(Rez, R)
   .
rez(L, K, V, R):-
    aranjamente(L,K,R), 
    product_list(R, X), X < V. 

product_list([], 1).
product_list([H|T], R):-
    product_list(T, R1),
    R is R1 * H
.