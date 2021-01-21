package Model.adt;

import java.util.Map;

public interface IDict<T1,T2> {
    void update(T1 id, T2 value);
    T2 lookup(T1 id);
    void add(T1 id, T2 value);
    Map<T1, T2> getRepresentation();
    IDict<T1, T2> clone();
}
