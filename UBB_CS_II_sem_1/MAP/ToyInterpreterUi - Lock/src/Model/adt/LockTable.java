package Model.adt;

import java.util.HashMap;
import java.util.Map;

public class LockTable<T1, T2> implements ILockTable<T1, T2>{
    Map<T1, T2> representation;
    int currentAddress;

    public void setTable(Map<T1, T2> representation) {
        this.representation = representation;
    }

    @Override
    public Integer getAddress() {
        this.currentAddress++;
        return this.currentAddress;
    }

    public LockTable() {
        this.currentAddress = 1;
        this.representation = new HashMap<>();
    }

    @Override
    public synchronized void update(T1 id, T2 value) {
        this.representation.put(id, value);
    }

    @Override
    public synchronized  T2 lookup(T1 id) {
        return this.representation.get(id);
    }

    @Override
    public void add(T1 id, T2 value) {
        this.representation.put(id, value);
        this.currentAddress += 1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(T1 address : this.representation.keySet()){
            stringBuilder.append(address).append("-->").append(this.representation.get(address)).append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    public Map<T1, T2> getRepresentation() {
        return representation;
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


}
