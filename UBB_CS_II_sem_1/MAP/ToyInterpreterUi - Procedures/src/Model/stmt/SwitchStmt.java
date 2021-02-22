package Model.stmt;

import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.except.MyException;
import Model.except.StatementException;
import Model.exp.Expression;
import Model.exp.RelationalExp;

import java.io.BufferedReader;
import java.io.IOException;

public class SwitchStmt implements IStmt{
    final Expression sExpression;
    final IStmt statement1;
    final Expression expression1;
    final IStmt statement2;
    final Expression expression2;
    final IStmt statement3;

    public SwitchStmt(Expression sExpression, IStmt statement1, Expression expression1, IStmt statement2, Expression expression2, IStmt statement3) {
        this.sExpression = sExpression;
        this.statement1 = statement1;
        this.expression1 = expression1;
        this.statement2 = statement2;
        this.expression2 = expression2;
        this.statement3 = statement3;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException {
        IDict<String, Value> symTable = state.getTopOfSymStack();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        if(sExpression.evaluate(symTable, fileTable, heapTable).getType() instanceof IntType){
            if(expression1.evaluate(symTable, fileTable, heapTable).getType() instanceof IntType){
                if(expression2.evaluate(symTable, fileTable, heapTable).getType() instanceof IntType){
                    state.getExeStack().push(new IfStmt(new RelationalExp("==", sExpression, expression1),
                            statement1, new IfStmt(new RelationalExp("==", sExpression, expression2),
                            statement2,statement3)));
                    return null;
                }else{
                    throw new MyException("Third expression is not an integer!");
                }
            }else{
                throw new MyException("Second expression is not an integer!");
            }
        }else{
            throw new MyException("Initial expression is not an integer!");
        }
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "Switch(" + sExpression +")"+
                "\ncase"+ expression1 +" : " + statement1 +
                "\ncase"+ expression2 +" : " + statement2 +
                "\ndefault :" + statement3;
    }
}
