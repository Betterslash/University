package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.StatementException;
import Model.except.TypeCheckException;

import java.io.IOException;

public class ReturnStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException, ExpressionException {
        IStack<IDict<String, Value>> symTable = state.getSymTable();
        symTable.pop();
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "return;";
    }
}
