package Model.stmt;

import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.adt.ILatchTable;
import Model.except.MyException;
import Model.except.StatementException;
import Model.exp.Expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewCountdownLatchStmt implements IStmt{
    final String var;
    final Expression expression;
    private Lock lock = new ReentrantLock();
    public NewCountdownLatchStmt(String var, Expression expression) {
        this.var = var;
        this.expression = expression;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException {
        lock.lock();
        IDict<String, Value> symTable = state.getSymTable();
        ILatchTable<Integer, Integer> latchTable = state.getLatchTable();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        Value value= this.expression.evaluate(symTable, fileTable, heapTable);
        if(value.getType().equals(new IntType())){
            IntValue intValue = (IntValue) value;
            Integer number = intValue.getValue();
            Integer freeLocation = latchTable.getAddress();
            latchTable.update(freeLocation, number);
            symTable.update(var, new IntValue(freeLocation));
        }else{
            throw new MyException("Parameters have incorrect types!");
        }
        lock.unlock();
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "newLatch("+this.var+", "+this.expression+")";
    }
}
