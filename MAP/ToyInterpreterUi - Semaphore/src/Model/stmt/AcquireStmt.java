package Model.stmt;

import Model.PrgState;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.RefValue;
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

public class AcquireStmt implements IStmt{
    final String var;
    Lock lock = new ReentrantLock();
    public AcquireStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException {
        lock.lock();
        IDict<String, Value> symTable = state.getSymTable();
        Value value = symTable.lookup(var);
        ISemaphoreTable<Integer, MyTPair<Integer, List<Integer>, Integer>> semaphoreTable = state.getSemaphoreTable();
        if(value.getType().equals(new IntType())) {
            int foundIndex;
            if(value instanceof RefValue){
                RefValue intValue = (RefValue) value;
                Value val =  state.getHeapTable().lookup(intValue.getAddress());
                foundIndex = ((IntValue) val).getValue();
            }else {
                IntValue intValue = (IntValue) value;
                foundIndex = intValue.getValue();
            }MyTPair<Integer, List<Integer>, Integer> myTPair = semaphoreTable.lookup(foundIndex);
            if(myTPair != null){
                int NL = myTPair.getList().size();
                int N1 = myTPair.getFirst();
                int N2 = myTPair.getSecond();
                if((N1 - N2) > NL){
                    Integer integer =state.getId();
                    if (!myTPair.getList().contains(integer)) {
                        myTPair.getList().add(state.getId());
                    }
                    return null;
                }
            }
        }
        state.getExeStack().push(this);
        lock.unlock();
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "acquire("+this.var+")";
    }
}
