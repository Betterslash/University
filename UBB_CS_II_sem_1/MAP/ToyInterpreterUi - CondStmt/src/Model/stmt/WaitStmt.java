package Model.stmt;

import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.StatementException;
import Model.except.TypeCheckException;
import Model.exp.Expression;
import Model.exp.ValueExp;

import java.io.BufferedReader;
import java.io.IOException;

public class WaitStmt implements IStmt{
    final Expression expression;

    public WaitStmt(Expression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException, ExpressionException {
        IDict<String, Value> symTable = state.getSymTable();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        Value value = this.expression.evaluate(symTable, fileTable, heapTable);
        if(value.getType().equals(new IntType())){
            IntValue intValue = (IntValue) value;
            int updatedValue = intValue.getValue();
            if(updatedValue > 0){
                System.out.println(updatedValue);
                updatedValue -= 1;
            }else{
                return null;
            }
            state.getExeStack().push(new WaitStmt(new ValueExp(new IntValue(updatedValue))));
        }else{
            throw new StatementException("The value of wait must be an integer!");
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        Type type = this.expression.typeCheck(typeEnv);
        if(type.equals(new IntType())) {
            return typeEnv;
        }else{
            throw new TypeCheckException("The value of wait must be an int type!");
        }
    }

    @Override
    public String toString() {
        return "wait(" + this.expression + ")";
    }
}
