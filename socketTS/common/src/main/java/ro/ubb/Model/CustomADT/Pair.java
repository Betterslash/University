package ro.ubb.Model.CustomADT;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Pair<T, T1>{
    private final T first;
    private final T1 last;
    public Pair(T first, T1 last){
        this.first = first;
        this.last = last;
    }
    public static Pair<Integer, Integer> parsePair(String body){
        List<String> attrs = Arrays.asList(body.split(", "));
        return new Pair<>(Integer.parseInt(attrs.get(0)), Integer.parseInt(attrs.get(1)));
    }
    public String asCsv(){ return first + ", " + last;}
    public String toString()
    {
        return "(TrainID: "+ first.toString() +", StationID: " + last.toString() +")";
    }
}
