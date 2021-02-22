package Model.adt;

public class MyPair<T, T1> {
    final T key;
    final T1 value;

    public MyPair(T key, T1 value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public T1 getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "("+this.key+", "+this.value+")";
    }
}
