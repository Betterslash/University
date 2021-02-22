package Model.stmt;

import Model.PrgState;
import Model.Types.IntType;
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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewSemaphoreStmt implements IStmt{
    final String var;
    final Expression expression1;
    final Expression expression2;
    Lock lock = new ReentrantLock();
    public NewSemaphoreStmt(String var, Expression expression1, Expression expression2){
        this.var = var;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException {
        lock.lock();
        IDict<String, Value> symTable = state.getSymTable();
        ISemaphoreTable<Integer, MyTPair<Integer,List<Integer>, Integer>> semaphoreTable = state.getSemaphoreTable();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        IntValue value1 = (IntValue)this.expression1.evaluate(symTable, fileTable, heapTable);
        IntValue value2 = (IntValue)this.expression2.evaluate(symTable, fileTable, heapTable);
        int freeLocation = semaphoreTable.useAddress()- 1;
        Integer number1 = value1.getValue();
        Integer number2 = value2.getValue();
        semaphoreTable.add(freeLocation, new MyTPair<>(number1, new ArrayList<>(), number2));
        symTable.update(var, new IntValue(freeLocation));
        lock.unlock();
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "newSemaphore(" +this.var +", "+ this.expression1+", "+ this.expression2+")";
    }
}
