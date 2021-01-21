package Model.adt;

import java.util.HashMap;
import java.util.Map;

public class MyIFDict<String, BufferedReader> implements IFDict<String, BufferedReader>{
    final HashMap<String, BufferedReader> representation;

    public MyIFDict() {
        this.representation = new HashMap<>();
    }

    @Override
    public BufferedReader lookup(String id) {
        return this.representation.get(id);
    }

    @Override
    public void add(String id, BufferedReader value) {
        this.representation.put(id, value);
    }

    @Override
    public BufferedReader delete(String val) {
        return this.representation.remove(val);
    }

    @Override
    public Map<String, BufferedReader> getRepresentation() {
        return this.representation;
    }

    @Override
    public java.lang.String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(String key : representation.keySet()){
            stringBuilder.append(key).append("-->").append(this.representation.get(key)).append(" ");
        }
        return stringBuilder.toString();
    }
}
