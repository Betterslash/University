package Model.stmt;

import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.adt.IDict;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.StatementException;
import Model.except.TypeCheckException;
import Model.exp.Expression;
import Model.exp.RelationalExp;
import Model.exp.VarExp;

import java.io.IOException;

public class ForStmt implements IStmt{
    final String var;
    final Expression exp1;
    final Expression exp2;
    final Expression exp3;
    final IStmt doBody;
    public ForStmt(String var, Expression exp1, Expression exp2, Expression exp3, IStmt doBody) {
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.doBody = doBody;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException, ExpressionException {
        IStmt declS = new VarDeclStmt("v", new IntType());
        IStmt assg = new AssignStmt("v", this.exp1);
        Expression relationalExp = new RelationalExp("<", new VarExp("v") ,exp2);
        IStmt dow = new CompStmt(doBody, new AssignStmt("v", exp3));
        state.getExeStack().push(new WhileStmt(relationalExp, dow));
        state.getExeStack().push(assg);
        state.getExeStack().push(declS);



        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "for( v = " + exp1 + ";v < " + exp2+"; v = "+ exp3+")"
                + doBody;
    }
}
