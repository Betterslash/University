package Model.exp;

import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.TypeCheckException;

import java.io.BufferedReader;

public class LogExp extends Expression{
    Expression right;
    Expression left;
    char op;
    @Override
    public Value evaluate(IDict<String, Value> symTable, IFDict<String, BufferedReader> fileTable, IHeap<Integer, Value> heapTable) throws MyException, ExpressionException {
        BoolValue vL = (BoolValue) this.left.evaluate(symTable, fileTable, heapTable);
        BoolValue vR = (BoolValue) this.right.evaluate(symTable, fileTable, heapTable);
        return switch (this.op) {
            case '&' -> new BoolValue(vL.isValue() & vR.isValue());
            case '|' -> new BoolValue(vL.isValue() || vR.isValue());
            default -> new BoolValue(false);
        };
    }

    @Override
    public Type typeCheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        Type type1;
        Type type2;
        type1 = this.left.typeCheck(typeEnv);
        type2 = this.right.typeCheck(typeEnv);
        if(type1.equals(new BoolType())){
            if(type2.equals(new BoolType())){
                return new BoolType();
            }else {
                throw new TypeCheckException("Left opperand is not a boolean!");
            }
        }
        else{
            throw new TypeCheckException("Right opperand is not a boolean!");
        }
    }
}
