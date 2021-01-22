package Model.stmt;

import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import Model.adt.ICBarrierTable;
import Model.adt.IDict;
import Model.adt.MyPair;
import Model.except.MyException;
import Model.except.StatementException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitStmt implements IStmt{
    final String var;
    final Lock lock = new ReentrantLock();

    public AwaitStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException {
        lock.lock();
        IDict<String, Value> symTable = state.getSymTable();
        ICBarrierTable<Integer, MyPair<Integer, List<Integer>>> barrierTable = state.getBarrierTable();
        Value value = symTable.lookup(var);
        if (value != null){
        if(value.getType().equals(new IntType())){
            IntValue intValue = (IntValue) value;
            Integer foundIndex = intValue.getValue();
            if(barrierTable.lookup(foundIndex) != null){
                MyPair<Integer, List<Integer>> myPair = barrierTable.lookup(foundIndex);
                Integer NL = myPair.getValue().size();
                Integer N1 = myPair.getKey();
                if (N1 > NL) {
                    if(!myPair.getValue().contains(state.getId())) {
                        myPair.getValue().add(state.getId());
                    }
                    state.getExeStack().push(this);
                }else {
                    return null;
                }
            }else{
                throw new MyException("No such barrier!");
            }
        }
        }else {
            throw new MyException("Value not found!");
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
