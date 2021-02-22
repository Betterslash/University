package Model.stmt;

import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import Model.adt.*;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.StatementException;
import Model.except.TypeCheckException;
import Model.exp.Expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CreateSemaphoreStmt implements IStmt{
    final String var;
    final Expression expression1;
    final Lock lock = new ReentrantLock();
    public CreateSemaphoreStmt(String var, Expression expression1) {
        this.var = var;
        this.expression1 = expression1;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException, ExpressionException {
        lock.lock();
        IStack<IDict<String, Value>> symTable = state.getSymTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        Value value = this.expression1.evaluate(symTable, fileTable, heapTable);
        ISemaphoreTable<Integer, MyPair<Integer, List<Integer>>> semaphoreTable = state.getSemaphoreTable();
        if(value.getType().equals(new IntType())){
            IntValue intValue = (IntValue) value;
            Integer number1 = intValue.getValue();
            int freeLocation =  semaphoreTable.getAddress() - 1;
            semaphoreTable.update(freeLocation, new MyPair<>(number1, new ArrayList<>()));
            Value value1 = symTable.clone().pop().lookup(var);
            if(value1 != null){
                symTable.clone().pop().update(var, new IntValue(freeLocation));
            }else{
                throw new StatementException("There's no variable stored for semaphore!");
            }
        }else{
            throw new StatementException("The expression of Semaphore shoul return an int!");
        }
        lock.unlock();
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        Type type1 = this.expression1.typeCheck(typeEnv);
        Type type2 = typeEnv.lookup(this.var);
        if(!type1.equals(new IntType())){
            throw new TypeCheckException("Semaphore's expression should have int type!");
        }
        if(!type2.equals(new IntType())){
            throw new TypeCheckException("Semaphore's variable should have int type!");
        }
        return typeEnv;
    }

    @Override
    public String toString() {
        return "createSemaphore(" +this.var +", "+this.expression1+")";
    }
}
