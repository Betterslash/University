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

import java.io.*;

public class OpenRFile implements IStmt{
    final Expression expression;

    public OpenRFile(Expression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, ExpressionException, StatementException {
        IStack<IDict<String, Value>> symTable = state.getSymTable();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        Value value = this.expression.evaluate(symTable, fileTable, heapTable);
        if(value.getType().equals(new StringType())){
            StringValue stringValue = (StringValue) value;
            if(fileTable.lookup(stringValue.getValue()) == null){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(stringValue.getValue()));
                fileTable.add(stringValue.getValue(), bufferedReader);
            }else {
                throw new StatementException("File already opened!");
            }
        }
        else{
            throw new StatementException("You can't open a file with a path different than string!");
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "open("+expression+")";
    }
}
