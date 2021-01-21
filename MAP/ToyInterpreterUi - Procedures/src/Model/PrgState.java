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
    IStack<IDict<String, Value>> symTables;
    IStack<IStmt> exeStack;
    IList<Value> out;
    IFDict<String, BufferedReader> fileTable;
    IHeap<Integer, Value> heapTable;
    IStmt originalProgram;
    IDict<String, MyPair<List<String>, IStmt>> globalTable;
    static synchronized void incId(){
        id += 1;
    }
    public PrgState(IStack<IDict<String, Value>> symTable, IStack<IStmt> exeStack, IList<Value> out, IFDict<String, BufferedReader> fileTable, IStmt originalProgram, IHeap<Integer, Value> heapTable, IDict<String, MyPair<List<String>, IStmt>> globalTable){
        this.exeStack = exeStack;
        this.originalProgram = originalProgram;
        this.out = out;
        this.symTables = symTable;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.globalTable = globalTable;
        ID = id;
        incId();
    }
    public PrgState(IStmt iStmt){
        this.globalTable = new MyDict<>();
        this.heapTable = new MyHeap<>();
        this.exeStack = new MyStack<>();
        this.originalProgram = iStmt;
        this.out = new MyList<>();
        this.symTables = new MyStack<>();
        this.symTables.push(new MyDict<>());
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

    public IStack<IDict<String, Value>> getSymTable() {
        return symTables;
    }

    public IList<Value> getOut() {
        return out;
    }

    public IStack<IStmt> getExeStack() {
        return exeStack;
    }
    public IDict<String , Value> getTopOfSymStack(){return
            this.symTables.clone().pop();}
    public IFDict<String, BufferedReader> getFileTable() {
        return fileTable;
    }
    public boolean isNotCompleted(){
        return !this.exeStack.isEmpty();
    }
    @Override
    public String toString() {
        return "\n\nProgram "+ID+" {" +
                "\nsymTable ->" + symTables +
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

    public IStack<IDict<String, Value>> getSymTables() {
        return symTables;
    }

    public int getId() {
        return this.ID;
    }
    public IDict<String, MyPair<List<String>, IStmt>> getGlobalTable(){
        return this.globalTable;
    }
}
