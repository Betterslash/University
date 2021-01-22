package Model;

import Model.Values.Value;
import Model.adt.*;
import Model.except.MyException;
import Model.except.StatementException;
import Model.stmt.IStmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PrgState {
    private static int id = 0;
    int ID;
    final ISemaphoreTable<Integer, MyTPair<Integer,List<Integer>, Integer>> semaphoreTable;
    IDict<String, Value> symTable;
    IStack<IStmt> exeStack;
    IList<Value> out;
    IFDict<String, BufferedReader> fileTable;
    IHeap<Integer, Value> heapTable;
    IStmt originalProgram;
    static synchronized void incId(){
        id += 1;
    }
    public PrgState(ISemaphoreTable<Integer, MyTPair<Integer,List<Integer>, Integer>> semaphoreTable, IDict<String, Value> symTable, IStack<IStmt> exeStack, IList<Value> out, IFDict<String, BufferedReader> fileTable, IStmt originalProgram, IHeap<Integer, Value> heapTable){
        this.semaphoreTable = semaphoreTable;
        this.exeStack = exeStack;
        this.originalProgram = originalProgram;
        this.out = out;
        this.symTable = symTable;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        ID = id;
        incId();
    }
    public PrgState(IStmt iStmt){
        this.semaphoreTable = new SemaphoreTable<>();
        this.heapTable = new MyHeap<>();
        this.exeStack = new MyStack<>();
        this.originalProgram = iStmt;
        this.out = new MyList<>();
        this.symTable = new MyDict<>();
        this.fileTable = new MyIFDict<>();
        this.exeStack.push(iStmt);
        ID = id;
        incId();
    }

    public IHeap<Integer, Value> getHeapTable() {
        return heapTable;
    }

    public void setHeapTable(Map<Integer, Value> heapTable) {
        this.heapTable.setTable(heapTable);
    }

    public IDict<String, Value> getSymTable() {
        return symTable;
    }

    public IList<Value> getOut() {
        return out;
    }

    public IStack<IStmt> getExeStack() {
        return exeStack;
    }

    public ISemaphoreTable<Integer, MyTPair<Integer,List<Integer>, Integer>> getSemaphoreTable() {
        return semaphoreTable;
    }

    public IFDict<String, BufferedReader> getFileTable() {
        return fileTable;
    }
    public boolean isNotCompleted(){
        return !this.exeStack.isEmpty();
    }
    @Override
    public String toString() {
        return "\n\nProgram "+ID+" {" +
                "\nsymTable ->" + symTable +
                "\nexeStack ->" + exeStack +
                "\nout ->" + out +
                "\nfileTable ->" + fileTable +
                "\noriginalProgram ->" + originalProgram +
                "\nheapTable ->" + heapTable +
                '}';
    }

    public PrgState oneStep() throws MyException, IOException, StatementException {
        if(exeStack.isEmpty()){
            System.out.println(exeStack);
            throw new MyException("Exe stack is empty!");
        }
        IStmt iStmt = this.getExeStack().pop();
        return iStmt.execute(this);
    }

    public IHeap<Integer, Value> getHeap() {
        return this.heapTable;
    }

    public int getId() {
        return this.ID;
    }
}
