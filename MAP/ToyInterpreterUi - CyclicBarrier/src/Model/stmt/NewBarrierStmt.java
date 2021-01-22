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
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStmt implements IStmt{
    final String var;
    final Expression expression;
    final Lock lock = new ReentrantLock();

    public NewBarrierStmt(String var, Expression expression) {
        this.var = var;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException {
        lock.lock();
        IDict<String, Value> symTable = state.getSymTable();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        ICBarrierTable<Integer, MyPair<Integer, List<Integer>>> barrierTable = state.getBarrierTable();
        Value value = this.expression.evaluate(symTable, fileTable, heapTable);
        if(value.getType().equals(new IntType())){
            IntValue intValue = (IntValue) value;
            Integer number = intValue.getValue();
            Integer freeLocation = barrierTable.getAddress();
            barrierTable.update(freeLocation, new MyPair<>(number, new ArrayList<>()));
            symTable.update(var, new IntValue(freeLocation));
        }else{
            throw new MyException("Incorrect variables!");
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
        return "newBarrier("+this.var+","+this.expression+")";
    }
}
