package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import Model.adt.*;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.TypeCheckException;
import Model.exp.Expression;

import java.io.BufferedReader;

public class PrintStmt implements IStmt{
    Expression v;
    public PrintStmt(Expression value){
        this.v = value;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, ExpressionException {
        IList<Value> output = state.getOut();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IStack<IDict<String, Value>> symTable = state.getSymTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        output.addOut(this.v.evaluate(symTable, fileTable, heapTable));
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        v.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print " + v;
    }
}
