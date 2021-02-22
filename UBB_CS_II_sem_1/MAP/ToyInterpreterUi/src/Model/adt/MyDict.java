package Model.adt;

import java.util.HashMap;
import java.util.Map;

public class MyDict<T1,T2> implements IDict<T1, T2>{
    HashMap<T1, T2> representation;

    public void setRepresentation(HashMap<T1, T2> representation) {
        this.representation = representation;
    }

    public MyDict(){
        this.representation = new HashMap<>();
    }
    @Override
    public void update(T1 id, T2 value) {
        this.representation.put(id,value);
    }

    @Override
    public T2 lookup(T1 id) {
        return this.representation.get(id);
    }

    @Override
    public void add(T1 id, T2 value) {
        this.representation.put(id,value);
    }

    @Override
    public Map<T1, T2> getRepresentation() {
        return this.representation;
    }

    @Override
    public IDict<T1, T2> clone() {
        HashMap<T1, T2> hashMap = new HashMap<>();
        for(T1 key: this.representation.keySet()){
            hashMap.put(key, this.representation.get(key));
        }
        MyDict<T1, T2> cloneTable = new MyDict<>();
        cloneTable.setRepresentation(hashMap);
        return cloneTable;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(T1 key : representation.keySet()){
           stringBuilder.append(key).append("-->").append(this.representation.get(key)).append(" ");
        }
        return stringBuilder.toString();
    }
}
