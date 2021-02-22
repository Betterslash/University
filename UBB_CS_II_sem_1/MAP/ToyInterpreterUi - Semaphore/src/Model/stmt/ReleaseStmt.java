package Model.stmt;

import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.ISemaphoreTable;
import Model.adt.MyTPair;
import Model.except.MyException;
import Model.except.StatementException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReleaseStmt implements IStmt{
    final String var;
    Lock lock = new ReentrantLock();
    public ReleaseStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException {
        lock.lock();
        IDict<String, Value> symTable = state.getSymTable();
        ISemaphoreTable<Integer, MyTPair<Integer, List<Integer>, Integer>> semaphoreTable = state.getSemaphoreTable();
        int foundIndex;
        Value value = symTable.lookup(var);
        if(value.getType().equals(new IntType())){
            IntValue intValue = (IntValue) value;
            foundIndex = intValue.getValue();
            Integer currentID = state.getId();
            MyTPair<Integer, List<Integer>, Integer> myTPair = semaphoreTable.lookup(foundIndex);
            myTPair.getList().remove(currentID);
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
        return "release("+this.var+")";
    }
}
