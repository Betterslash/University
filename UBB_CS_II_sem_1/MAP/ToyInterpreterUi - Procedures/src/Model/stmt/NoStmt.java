package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.adt.IDict;
import Model.except.MyException;

import java.io.IOException;

public class NoStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        return null;
    }
}
