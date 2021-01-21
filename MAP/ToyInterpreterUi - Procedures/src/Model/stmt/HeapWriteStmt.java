package Model.stmt;


import Model.PrgState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.except.MyException;
import Model.except.StatementException;
import Model.exp.Expression;

import java.io.BufferedReader;

public class HeapWriteStmt implements IStmt{
    String varName;
    Expression exp;

    public HeapWriteStmt(String varName, Expression exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, MyException {
        IDict<String, Value> symTable = state.getTopOfSymStack();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, Value> heapTable = state.getHeap();
        Value value = this.exp.evaluate(symTable,fileTable, heapTable);
        Value value1 = symTable.lookup(varName);
        if(value1 != null){
            if(value1.getType() instanceof RefType){
                if(value1.getType().equals(value.getType())){
                    int location = ((RefValue) value1).getAddress();
                    heapTable.update(location, value);
                }else{
                    throw new StatementException("Inner types missmatch!");
                }
            }else {
                throw new StatementException("Ref type missmatch!");
            }
        }
        else {
            throw new StatementException("Invalid value write!");
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(varName);
        Type typexp = exp.typeCheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return "wH("+ varName+", "+ exp+")";
    }
}
