package Model.stmt;

import Model.PrgState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.except.MyException;
import Model.exp.Expression;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt {
    final String varName;
    final Expression expression;

    public ReadFileStmt(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IDict<String, Value> symTable = state.getTopOfSymStack();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        Value value = expression.evaluate(symTable, fileTable, heapTable);
        if(symTable.lookup(varName) != null){
            if(symTable.lookup(varName).getType().equals(new IntType())){
                if(value.getType().equals(new StringType())){
                    StringValue stringValue = (StringValue)value;
                    if(fileTable.lookup(stringValue.getValue()) != null){
                        BufferedReader bufferedReader = fileTable.lookup(stringValue.getValue());
                        int val = Integer.parseInt(bufferedReader.readLine());
                        symTable.update(varName, new IntValue(val));
                    }else{
                        throw new MyException("No file opened!");
                    }
                }else{
                    throw new MyException("Path is not a string!");
                }
            }else{
                throw new MyException("The var doesn't have the coresponding type!");
            }
        }else{
            throw new MyException("No variable of this name is stored!");
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        this.expression.typeCheck(typeEnv);
        if(expression.typeCheck(typeEnv) instanceof StringType){
            if(typeEnv.lookup(varName) instanceof IntType)
            return typeEnv;
            else throw new MyException("Wrong type!");
        }
        else{
            throw new MyException("Wrong variables!");
        }
    }

    @Override
    public String toString() {
        return "read(" + this.expression + ", "+ this.varName +")";
    }
}
