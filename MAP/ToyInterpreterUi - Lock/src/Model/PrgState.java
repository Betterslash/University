package Model;

import Model.Values.Value;
import Model.adt.*;
import Model.except.MyException;
import Model.except.StatementException;
import Model.stmt.IStmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class PrgState {
    private static int id = 0;
    int ID;
    ILockTable<Integer, Integer> lockTable;
    IDict<String, Value> symTable;
    IStack<IStmt> exeStack;
    IList<Value> out;
    IFDict<String, BufferedReader> fileTable;
    IHeap<Integer, Value> heapTable;
    IStmt originalProgram;

    public void setExeStack(IStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setLockTable(ILockTable<Integer, Integer> lockTable) {
        this.lockTable = lockTable;
    }

    public void setSymTable(IDict<String, Value> symTable) {
        this.symTable = symTable;
    }

    public ILockTable<Integer, Integer> getLockTable() {
        return lockTable;
    }

    static synchronized void incId(){
        id += 1;
    }
    public PrgState(ILockTable<Integer, Integer> lockTable, IDict<String, Value> symTable, IStack<IStmt> exeStack, IList<Value> out, IFDict<String, BufferedReader> fileTable, IStmt originalProgram, IHeap<Integer, Value> heapTable){
        this.lockTable = lockTable;
        this.exeStack = exeStack;
        this.originalProgram = originalProgram;
        this.out = out;
        this.symTable = symTable;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        ID = id * 10;
        incId();
    }
    public PrgState(IStmt iStmt){
        this.lockTable = new LockTable<>();
        this.heapTable = new MyHeap<>();
        this.exeStack = new MyStack<>();
        this.originalProgram = iStmt;
        this.out = new MyList<>();
        this.symTable = new MyDict<>();
        this.fileTable = new MyIFDict<>();
        this.exeStack.push(iStmt);
        ID = id * 10;
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
