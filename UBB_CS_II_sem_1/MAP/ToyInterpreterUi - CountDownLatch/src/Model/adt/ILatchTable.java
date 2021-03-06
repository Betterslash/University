package Model.adt;

import java.util.Map;

public interface ILatchTable<T1, T2> {
    void update(T1 id, T2 value);
    T2 lookup(T1 id);
    void add(T1 id, T2 value);

    void setTable(Map<T1, T2> representation);
    Integer getAddress();
    Map<T1, T2> getRepresentation();
}
