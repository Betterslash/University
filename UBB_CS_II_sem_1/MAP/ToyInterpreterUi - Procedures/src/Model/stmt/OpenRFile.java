package Model.stmt;

import Model.PrgState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.except.MyException;
import Model.exp.Expression;

import java.io.*;

public class OpenRFile implements IStmt{
    final Expression expression;

    public OpenRFile(Expression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        IDict<String, Value> symTable = state.getTopOfSymStack();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        Value value = this.expression.evaluate(symTable, fileTable, heapTable);
        if(value.getType().equals(new StringType())){
            StringValue stringValue = (StringValue) value;
            if(fileTable.lookup(stringValue.getValue()) == null){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(stringValue.getValue()));
                fileTable.add(stringValue.getValue(), bufferedReader);
            }else {
                throw new MyException("File already opened!");
            }
        }
        else{
            throw new MyException("You can't open a file with a path different than string!");
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "open("+expression+")";
    }
}
