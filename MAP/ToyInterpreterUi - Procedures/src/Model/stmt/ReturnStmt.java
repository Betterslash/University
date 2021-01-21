package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.adt.IDict;
import Model.except.MyException;
import Model.except.StatementException;

import java.io.IOException;

public class ReturnStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException {
        state.getSymTables().pop();
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "return;";
    }
}
