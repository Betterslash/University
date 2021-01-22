package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import Model.adt.*;
import Model.except.MyException;
import Model.except.StatementException;
import Model.exp.Expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewSemaphoreStmt implements IStmt{
    final int var;
    final Expression expression1;
    final Expression expression2;
    public NewSemaphoreStmt(int var, Expression expression1, Expression expression2){
        this.var = var;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException {
        IDict<String, Value> symTable = state.getSymTable();
        ISemaphoreTable<Integer, MyTPair<Integer,List<Integer>, Integer>> semaphoreTable = state.getSemaphoreTable();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        IntValue value1 = (IntValue)this.expression1.evaluate(symTable, fileTable, heapTable);
        IntValue value2 = (IntValue)this.expression2.evaluate(symTable, fileTable, heapTable);
        Integer freeLocation = semaphoreTable.useAddress();
        List<Integer> integers = new ArrayList<>();
        Integer number1 = value1.getValue();
        Integer number2 = value2.getValue();
        semaphoreTable.add(freeLocation, new MyTPair<Integer, List<Integer>, Integer>(number1, new ArrayList<>(), number2));
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
