package Model.adt;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IList<T>{
    ArrayList<T> representation;
    public MyList(){
        this.representation = new ArrayList<T>();
    }
    @Override
    public void addOut(T item) {
        this.representation.add(item);
    }

    @Override
    public T getOut() {
        return this.representation.get(this.representation.size() - 1);
    }

    @Override
    public List<T> getList() {
        return this.representation;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(T elem : this.representation){
            stringBuilder.append(elem).append(" ");
        }
        return stringBuilder.toString();
    }
}
