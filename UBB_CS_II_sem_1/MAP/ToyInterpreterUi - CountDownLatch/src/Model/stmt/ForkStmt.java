package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import Model.adt.*;
import Model.except.MyException;

import java.io.BufferedReader;

public class ForkStmt implements IStmt{
    final IStmt statement;

    public ForkStmt(IStmt statement) {
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) {
        ILatchTable<Integer, Integer> latchTable = state.getLatchTable();
        IDict<String, Value> symTable = state.getSymTable().clone();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IStack<IStmt> exeStack = new MyStack<>();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        IList<Value> output = state.getOut();
        PrgState prgState = new PrgState(symTable, exeStack, output, fileTable, statement, heapTable, latchTable);
        exeStack.push(statement);
        return prgState;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        statement.typecheck(typeEnv.clone());
        return typeEnv;
    }

    @Override
    public String toString() {
        return "\nfork("+statement+")";
    }
}
