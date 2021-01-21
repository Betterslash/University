package Model.stmt;

import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.adt.IStack;
import Model.except.MyException;
import Model.exp.Expression;

import java.io.BufferedReader;

public class IfStmt implements IStmt{
    Expression e1;
    IStmt thenStmt;
    IStmt elseStmt;
    public IfStmt(Expression e1, IStmt thenStmt, IStmt elseStmt){
        this.e1 = e1;
        this.elseStmt = elseStmt;
        this.thenStmt = thenStmt;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, Value> symTable = state.getTopOfSymStack();
        IStack<IStmt> exeStack = state.getExeStack();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        Value boolVal = e1.evaluate(symTable, fileTable, heapTable);
        if(boolVal.getType() instanceof BoolType){
            if(((BoolValue) boolVal).isValue()){
                exeStack.push(thenStmt);
            }
            else{
                exeStack.push(elseStmt);
            }
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        Type type = e1.typeCheck(typeEnv);
        if(type instanceof BoolType){
            thenStmt.typecheck(typeEnv);
            elseStmt.typecheck(typeEnv);
            return typeEnv;
        }
        else {
            throw new MyException("The expression is not a boolean one!");
        }
    }

    @Override
    public String toString() {
        return "If (" + e1 +
                ") then (" + thenStmt +
                ") else (" + elseStmt + ")";
    }
}
