package Model.exp;

import Model.Types.Type;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;

import java.io.BufferedReader;

public class ValueExp extends Expression{
    Value value;
    public ValueExp(Value value){
        this.value = value;
    }
    @Override
    public Value evaluate(IDict<String, Value> symTable, IFDict<String, BufferedReader> fileTable, IHeap<Integer, Value> heapTable) {
        return this.value;
    }

    @Override
    public Type typeCheck(IDict<String, Type> typeEnv) {
        return value.getType();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
