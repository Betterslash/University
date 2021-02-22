package Model.exp;

import Model.Types.Type;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.adt.IStack;
import Model.except.MyException;

import java.io.BufferedReader;

public class VarExp extends Expression{
    String id;
    public VarExp(String id){
        this.id = id;
    }
    @Override
    public Value evaluate(IStack<IDict<String, Value>> symTable, IFDict<String, BufferedReader> fileTable, IHeap<Integer, Value> heapTable) {
        return symTable.clone().pop().lookup(id);
    }

    @Override
    public Type typeCheck(IDict<String, Type> typeEnv) {
        return typeEnv.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
