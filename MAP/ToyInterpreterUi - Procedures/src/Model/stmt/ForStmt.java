package Model.stmt;

import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.except.MyException;
import Model.exp.Expression;
import Model.exp.RelationalExp;
import Model.exp.ValueExp;

import java.io.BufferedReader;

public class ForStmt implements IStmt{
    final Expression iterValue;
    final Expression expression1;
    final Expression expression2;
    final Expression expression3;
    final IStmt statement;

    public ForStmt(Expression iterValue, Expression expression1, Expression expression2, Expression expression3, IStmt statement) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
        this.statement = statement;
        this.iterValue = iterValue;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDict<String, Value> symTable = state.getTopOfSymStack();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        Value value1 = this.expression1.evaluate(symTable, fileTable, heapTable);
        if(value1.getType().equals(new IntType())){
            Value value2 = this.expression2.evaluate(symTable, fileTable, heapTable);
            if(value2.getType().equals(new IntType())){

                new AssignStmt(iterValue.toString(), expression1).execute(state);
                Expression expression = new RelationalExp("<", iterValue, new ValueExp(value2));
                IStmt exeStatement = new CompStmt(this.statement, new AssignStmt(iterValue.toString(), expression3));
                state.getExeStack().push(new WhileStmt(expression, exeStatement));
                return null;
            }else{
                throw new MyException("Initial value isn't integer!");
            }
        }else{
            throw new MyException("Second value isn't integer!");
        }
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        Type type1 = expression1.typeCheck(typeEnv);
        Type type2 = expression2.typeCheck(typeEnv);
        if(type1 instanceof IntType && type2 instanceof IntType){
            return typeEnv;
        }else {
            throw new MyException("Missmatch between types!");
        }
    }

    @Override
    public String toString() {
        return "for(v = "+this.expression1+"; v < "+this.expression2+"; v = "+ this.expression3+")"+"{"+this.statement+"}";
    }
}
