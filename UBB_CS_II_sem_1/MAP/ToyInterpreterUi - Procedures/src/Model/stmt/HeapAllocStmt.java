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

public class HeapAllocStmt implements IStmt{
    final String id;
    final Expression expression;

    public HeapAllocStmt(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, StatementException {
        IDict<String, Value> symTable = state.getTopOfSymStack();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        Value value = expression.evaluate(symTable, fileTable, heapTable);
        if(symTable.lookup(this.id).getType() instanceof RefType){
            RefType refType = (RefType) symTable.lookup(this.id).getType();
            if(value.getType().equals(refType.getInner())) {
                try {
                    int address = heapTable.getAddress();
                    symTable.update(id, new RefValue(refType.getInner(), address));
                    heapTable.add(address, value);
                }catch (Exception e){
                    System.out.println("here");
                }
            }
            else {
                throw new StatementException("Inner types missmatch!1");
            }
        }else {
            throw new StatementException("Inner types missmatch!");
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(id);
        Type typexp = expression.typeCheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }
    @Override
    public String toString() {
        return "new(" + this.id +", " + this.expression+ ")";
    }
}
