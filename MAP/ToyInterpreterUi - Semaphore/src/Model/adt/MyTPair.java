package Model.adt;

public class MyTPair<T, T1, T2> {
    final T first;
    final T1 list;
    final T2 second;
    public MyTPair(T first, T1 list, T2 second) {
        this.first = first;
        this.list = list;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public T1 getList() {
        return list;
    }
}