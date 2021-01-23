package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.adt.IDict;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.StatementException;
import Model.except.TypeCheckException;

import java.io.IOException;

public class CondStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException, ExpressionException {
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        return null;
    }
}
