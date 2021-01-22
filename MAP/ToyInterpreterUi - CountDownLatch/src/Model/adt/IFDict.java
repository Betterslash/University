package Model.adt;

import java.util.Map;

public interface IFDict<T1, T2>{
    T2 lookup(T1 id);
    void add(T1 id, T2 value);
    T2 delete(T1 val);
    Map<T1, T2> getRepresentation();
}
