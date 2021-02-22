package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.adt.IList;
import Model.except.MyException;
import Model.exp.Expression;

import java.io.BufferedReader;

public class PrintStmt implements IStmt{
    Expression v;
    public PrintStmt(Expression value){
        this.v = value;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IList<Value> output = state.getOut();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IDict<String, Value> symTable = state.getTopOfSymStack();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        output.addOut(this.v.evaluate(symTable, fileTable, heapTable));
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        v.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print " + v;
    }
}
