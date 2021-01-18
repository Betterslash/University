%f([ ],0).
%f([H|T],S):- f(T,S1), avoid(S, S1, H).
%avoid(S, S1, H):- H<S1, !, S is S1+H.
%avoid(S, S1, _):- S is S1 + 2.

f(1, 1):- !.
f(K, X):- 
    K1 is K - 1, 
    f(K1, Y),
    Y > 1, !,
    K2 is K1 - 1,
    X is K2
    .
f(K, X):-
    K1 is K - 1, 
    f(K1, Y),
    Y > 0.5,
    !,
    X is Y
.    
f(K, X):-
    K1 is K - 1,
    f(K1, Y),
    X is Y - 1
.
