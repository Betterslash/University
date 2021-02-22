package Model.adt;

import Model.Procedure;
import Model.stmt.IStmt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcTable<T1, T2> implements IProcTable<T1, T2> {
    Map<T1, T2> representation;
    int currentAddress;

    public void setTable(Map<T1, T2> representation) {
        this.representation = representation;
    }

    @Override
    public Integer getAddress() {
        return this.currentAddress;
    }

    public ProcTable() {
        this.currentAddress = 1;
        this.representation = new HashMap<>();
    }

    @Override
    public void update(T1 id, T2 value) {
        this.representation.put(id, value);
    }

    @Override
    public T2 lookup(T1 id) {
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
            MyPair<List<String>, IStmt> myPair = (MyPair<List<String>, IStmt>) this.representation.get(address);
            stringBuilder.append("procedure ").append(address).append(" (").append(myPair.getKey()).append(") ").append(myPair.getValue()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public Map<T1, T2> getRepresentation() {
        return representation;
    }



}
