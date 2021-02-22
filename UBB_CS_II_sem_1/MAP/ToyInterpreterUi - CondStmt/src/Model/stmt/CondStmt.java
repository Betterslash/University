package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.StatementException;
import Model.except.TypeCheckException;
import Model.exp.Expression;

import java.io.BufferedReader;
import java.io.IOException;

public class CondStmt implements IStmt{
    private final String var;
    private final Expression exp1;
    private final Expression exp2;
    private final Expression exp3;

    public CondStmt(String value, Expression exp1, Expression exp2, Expression exp3) {
        this.var = value;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException, ExpressionException {
        IStmt ifStatement = new IfStmt(exp1, new AssignStmt(this.var, this.exp2), new AssignStmt(this.var, this.exp3));
        state.getExeStack().push(ifStatement);
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return this.var+" = "+this.exp1+"?"+this.exp2+":"+this.exp3;
    }
}
