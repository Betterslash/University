package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.ILockTable;
import Model.except.MyException;
import Model.except.StatementException;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnlockStmt implements IStmt{
    String var;
    private static final Lock lock = new ReentrantLock();
    public UnlockStmt(String var){
        this.var = var;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException {
        lock.lock();
        IDict<String, Value> symTable = state.getSymTable();
        Integer foundIndex = Integer.parseInt(symTable.lookup(var).toString());
        ILockTable<Integer, Integer> lockTable = state.getLockTable();
        Integer lockValue = Integer.parseInt(lockTable.lookup(foundIndex).toString());
        if (null == state.getSymTable().lookup(var))
            throw new MyException("No such index in latchTable");
        if (lockValue.equals(state.getId())) {
            lockTable.update(foundIndex, -1);
            state.setLockTable(lockTable);
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
        return "unlock(" + this.var + ")";
    }

}
