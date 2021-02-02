package Model.stmt;

import Model.PrgState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
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
import java.io.IOException;

public class CloseRFile implements IStmt{
    final Expression expression;

    public CloseRFile(Expression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, ExpressionException, StatementException {
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IStack<IDict<String, Value>> symTable = state.getSymTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        Value value = this.expression.evaluate(symTable, fileTable, heapTable);
        if(value.getType().equals(new StringType())){
            StringValue stringValue = (StringValue)value;
            if(fileTable.lookup(stringValue.getValue()) != null){
                BufferedReader bufferedReader = fileTable.lookup(stringValue.getValue());
                bufferedReader.close();
                fileTable.delete(stringValue.getValue());
            }else{
                throw new StatementException("The file already closed or not in file table!");
            }
        }else{
            throw new StatementException("The path is not valid!");
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        this.expression.typeCheck(typeEnv);
        if(expression.typeCheck(typeEnv) instanceof StringValue){
            return typeEnv;
        }
        return null;
    }

    @Override
    public String toString() {
        return "close(" + expression + ")";
    }
}
