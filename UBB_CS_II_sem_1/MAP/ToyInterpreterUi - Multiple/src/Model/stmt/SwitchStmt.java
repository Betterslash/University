package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.adt.IDict;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.StatementException;
import Model.except.TypeCheckException;
import Model.exp.Expression;
import Model.exp.RelationalExp;

import java.io.IOException;

public class SwitchStmt implements IStmt{
    final Expression exp1;
    final Expression exp2;
    final Expression exp3;
    final IStmt stmt1;
    final IStmt stmt2;
    final IStmt stmt3;
    public SwitchStmt(Expression exp1, Expression exp2, Expression exp3, IStmt stmt1, IStmt stmt2, IStmt stmt3) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException, ExpressionException {
        state.getExeStack().push(new IfStmt(new RelationalExp("==", exp1, exp2),
                stmt1,
                new IfStmt(new RelationalExp("==", exp1, exp3),stmt2, stmt3)));
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "switch(" +exp1 +")"
                +"\n(case("+exp1+")" + stmt1 + ")"
                +"\n(case("+exp2+")" + stmt2 + ")"
                +"\n(default " + stmt3 + ")";
    }
}
