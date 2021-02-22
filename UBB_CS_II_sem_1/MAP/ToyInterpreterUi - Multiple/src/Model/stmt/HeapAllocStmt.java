package Model.stmt;

import Model.PrgState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.adt.IStack;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.StatementException;
import Model.except.TypeCheckException;
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
    public PrgState execute(PrgState state) throws MyException, StatementException, ExpressionException {
        IStack<IDict<String, Value>> symTable = state.getSymTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        Value value = expression.evaluate(symTable, fileTable, heapTable);
        if(symTable.clone().pop().lookup(this.id).getType() instanceof RefType){
            RefType refType = (RefType) symTable.clone().pop().lookup(this.id).getType();
            if(value.getType().equals(refType.getInner())) {
                try {
                    int address = heapTable.getAddress();
                    symTable.clone().pop().update(id, new RefValue(refType.getInner(), address));
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
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        Type typevar = typeEnv.lookup(id);
        Type typexp = expression.typeCheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new TypeCheckException("NEW stmt: right hand side and left hand side have different types ");
    }
    @Override
    public String toString() {
        return "new(" + this.id +", " + this.expression+ ")";
    }
}
