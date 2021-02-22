package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.adt.IDict;
import Model.adt.ILockTable;
import Model.except.MyException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStmt implements IStmt {
    private final String var;
    private static final Lock lock = new ReentrantLock();

    public LockStmt(String var){
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        Integer foundIndex = Integer.parseInt(state.getSymTable().lookup(var).toString());

        if(null == state.getSymTable().lookup(var))
            throw new MyException("No such variable in symbolTable");
        ILockTable<Integer, Integer> lockTable = state.getLockTable();
        Integer lockValue = lockTable.lookup(foundIndex);
        if (lockValue == null)
            throw new MyException("No such index in latchTable");
        if (lockValue == -1) {
            lockTable.update(foundIndex, state.getId());
            state.setLockTable(lockTable);
        }
        else{
            state.getExeStack().push(this);
        }

        lock.unlock();
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString(){
        return "lock( " + var + " )";
    }
}
