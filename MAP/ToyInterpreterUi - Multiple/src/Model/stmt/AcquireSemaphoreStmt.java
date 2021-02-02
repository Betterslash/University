package Model.stmt;

import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.ISemaphoreTable;
import Model.adt.IStack;
import Model.adt.MyPair;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.StatementException;
import Model.except.TypeCheckException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AcquireSemaphoreStmt implements IStmt{
    final String var;
    final Lock lock = new ReentrantLock();
    public AcquireSemaphoreStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException, ExpressionException {
        lock.lock();
        IStack<IDict<String, Value>> symTable = state.getSymTable();
        ISemaphoreTable<Integer, MyPair<Integer, List<Integer>>> semaphoreTable = state.getSemaphoreTable();
        Value value = symTable.clone().pop().lookup(var);
        if(value != null){
            if(value.getType().equals(new IntType())){
                IntValue intValue = (IntValue) value;
                Integer foundIndex = intValue.getValue();
                MyPair<Integer, List<Integer>> myPair = semaphoreTable.lookup(foundIndex);
                if(myPair != null){
                    Integer NL = myPair.getValue().size();
                    Integer N1 = myPair.getKey();
                    Integer progID = state.getId();
                    if(N1 > NL){
                        if (!myPair.getValue().contains(progID)) {
                            myPair.getValue().add(progID);
                        }else{
                            return null;
                        }
                    }else{
                        state.getExeStack().push(this);
                    }
                }else{
                    throw new StatementException("Index address is wrong!");
                }
            }else {
                throw new StatementException("Missmatch between types in Acquire Statement!");
            }
        }else {
            throw new StatementException("Variable not found for Acquire Statement!");
        }
        lock.unlock();
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "acquire("+this.var+")";
    }
}
