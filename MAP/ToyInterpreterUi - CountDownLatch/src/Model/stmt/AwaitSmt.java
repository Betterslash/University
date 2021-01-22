package Model.stmt;

import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.ILatchTable;
import Model.except.MyException;
import Model.except.StatementException;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitSmt implements IStmt{
    final String var;
    private final Lock lock = new ReentrantLock();
    public AwaitSmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException {
        lock.lock();
        IDict<String, Value> symTable = state.getSymTable();
        ILatchTable<Integer, Integer> latchTable = state.getLatchTable();
        Value value = symTable.lookup(var);
        if(value.getType().equals(new IntType())){
            IntValue intValue = (IntValue) value;
            Integer freeLocation = intValue.getValue();
            Integer currentLatchValue = latchTable.lookup(freeLocation);
            if(currentLatchValue == 0){
                return null;
            }else {
                state.getExeStack().push(this);
            }
        }else{
            throw new MyException("No such latchValue in the table!");
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
        return "await("+this.var+")";
    }
}
