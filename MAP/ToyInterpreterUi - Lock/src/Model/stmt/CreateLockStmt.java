package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.ILockTable;
import Model.except.MyException;
import Model.except.StatementException;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CreateLockStmt implements IStmt{
    private final String var;
    private static final Lock lock = new ReentrantLock();
    public CreateLockStmt(String var){
        this.var = var;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException {
        lock.lock();
        ILockTable<Integer,Integer> lockTable = state.getLockTable();
        IDict<String, Value> symTable = state.getSymTable();
        int location = state.getLockTable().getAddress();
        symTable.update(var, new IntValue(location));
        lockTable.update(location, -1);
        lock.unlock();
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "newLock(" +this.var+ ")";
    }
}
