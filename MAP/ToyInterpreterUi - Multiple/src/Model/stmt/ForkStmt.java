package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import Model.adt.*;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.TypeCheckException;

import java.io.BufferedReader;
import java.util.List;

public class ForkStmt implements IStmt{
    final IStmt statement;

    public ForkStmt(IStmt statement) {
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) {
        IDict<String, Value> symTable = state.getSymTable().clone();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IStack<IStmt> exeStack = new MyStack<>();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        IList<Value> output = state.getOut();
        ISemaphoreTable<Integer, MyPair<Integer, List<Integer>>> semaphoreTable = state.getSemaphoreTable();
        PrgState prgState = new PrgState(semaphoreTable, symTable, exeStack, output, fileTable, statement, heapTable);
        exeStack.push(statement);
        return prgState;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        statement.typecheck(typeEnv.clone());
        return typeEnv;
    }

    @Override
    public String toString() {
        return "\nfork("+statement+")";
    }
}
