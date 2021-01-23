package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.StatementException;
import Model.except.TypeCheckException;
import Model.exp.Expression;

import java.io.BufferedReader;

public class AssignStmt implements IStmt{
    String id;
    Expression exp;
    public AssignStmt(String id, Expression exp){
        this.exp = exp;
        this.id = id;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, ExpressionException, StatementException {
        IDict<String , Value> symTable = state.getSymTable();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
            Value val = this.exp.evaluate(symTable, fileTable, heapTable);
            try{
            Type type = symTable.lookup(id).getType();
            if (val.getType().equals(type)) {
                symTable.update(id, val);
            }
        }catch (Exception e){
            throw new StatementException("There's no variable like this stored!");
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typeCheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new TypeCheckException("Assignment: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return id + " = " + exp;
    }
}
