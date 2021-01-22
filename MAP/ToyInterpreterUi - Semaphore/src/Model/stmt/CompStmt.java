package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.except.MyException;

public class CompStmt implements IStmt{
    IStmt st1;
    IStmt st2;
    public CompStmt(IStmt st1, IStmt st2){
        this.st1 = st1;
        this.st2 = st2;
    }
    @Override
    public PrgState execute(PrgState state) {
        IStack<IStmt> exeStack = state.getExeStack();
        exeStack.push(this.st2);
        exeStack.push(this.st1);
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        return this.st2.typecheck(this.st1.typecheck(typeEnv));
    }

    @Override
    public String toString() {
        return  this.st1.toString() +" ; "+ this.st2.toString();
    }
}
