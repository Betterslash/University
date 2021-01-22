package Model.adt;

import java.util.List;

public interface IList<T> {
    void addOut(T item);
    T getOut();

    List<T> getList();
}
