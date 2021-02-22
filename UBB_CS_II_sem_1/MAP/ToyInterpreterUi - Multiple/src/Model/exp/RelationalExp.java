package Model.exp;

import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.adt.IStack;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.TypeCheckException;

import java.io.BufferedReader;

public class RelationalExp extends Expression{
    Expression left;
    Expression right;
    String op;
    public RelationalExp(String op,Expression l, Expression r){
        this.left = l;
        this.right = r;
        this.op = op;
    }
    @Override
    public Value evaluate(IStack<IDict<String, Value>> symTable, IFDict<String, BufferedReader> fileTable, IHeap<Integer, Value> heapTable) throws MyException, ExpressionException {
        if(!left.evaluate(symTable, fileTable, heapTable).getType().equals(new IntType()))
        {
            throw new ExpressionException("The left operand in not integer!");
        }
        if(!right.evaluate(symTable, fileTable, heapTable).getType().equals(new IntType()))
        {
            throw new ExpressionException("The right operand in not integer!");
        }
        IntValue le = (IntValue)this.left.evaluate(symTable, fileTable, heapTable);
        IntValue ri = (IntValue)this.right.evaluate(symTable, fileTable, heapTable);
        switch (op) {
            case "==" : return new BoolValue(le.getValue() == ri.getValue());
            case "!=" : return new BoolValue(le.getValue() != ri.getValue());
            case "<" : return new BoolValue(le.getValue() < ri.getValue());
            case ">" : return new BoolValue(le.getValue() > ri.getValue());
            case ">=" : return new BoolValue(le.getValue() >= ri.getValue());
            case "<=" : return new BoolValue(le.getValue() <= ri.getValue());
        }
        throw new ExpressionException("Wrong operator!");
    }

    @Override
    public Type typeCheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        Type type1;
        Type type2;
        type1 = this.left.typeCheck(typeEnv);
        type2 = this.right.typeCheck(typeEnv);
        if(type1.equals(new IntType())){
            if(type2.equals(new IntType())){
                return new BoolType();
            }else {
                throw new TypeCheckException("Left opperand is not an integer!");
            }
        }
        else{
            throw new TypeCheckException("Right opperand is not an integer!");
        }
    }

    @Override
    public String toString() {
        return "("+left+" "+op+" "+right + ")";
    }
}
