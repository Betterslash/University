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
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.StatementException;
import Model.except.TypeCheckException;
import Model.exp.Expression;

import java.io.BufferedReader;

public class WhileStmt implements IStmt{
    final Expression expression;
    final IStmt iStmt;

    public WhileStmt(Expression expression, IStmt iStmt) {
        this.expression = expression;
        this.iStmt = iStmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, ExpressionException, StatementException {
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IStack<IDict<String, Value>> symTable = state.getSymTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        IStack<IStmt> exeStack = state.getExeStack();
        Value value = this.expression.evaluate(symTable, fileTable, heapTable);
        if(value.getType() instanceof BoolType){
            BoolValue boolValue = (BoolValue)value;
            if(boolValue.isValue()){
                exeStack.push(this);
                exeStack.push(iStmt);
            }
        }else{
            throw new StatementException("Cheking while value must be of boolean type!");
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        Type type = expression.typeCheck(typeEnv);
        if(type instanceof BoolType){
            iStmt.typecheck(typeEnv);
            return typeEnv;
        }else {
            throw new TypeCheckException("Expression is not a boolean type!");
        }
    }

    @Override
    public String toString() {
        return "while(" + this.expression + ")"+"{"+this.iStmt+"}";
    }
}
