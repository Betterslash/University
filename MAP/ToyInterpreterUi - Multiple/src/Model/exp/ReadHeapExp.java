package Model.exp;

import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.adt.IStack;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.TypeCheckException;

import java.io.BufferedReader;

public class ReadHeapExp extends Expression {
    final Expression expression;

    public ReadHeapExp(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value evaluate(IStack<IDict<String, Value>> symTable, IFDict<String, BufferedReader> fileTable, IHeap<Integer, Value> heapTable) throws MyException, ExpressionException {
        Value value = this.expression.evaluate(symTable, fileTable, heapTable);
        if(value.getType() instanceof RefType){
            RefValue refValue = (RefValue)value;
            return heapTable.lookup(refValue.getAddress());
        }
        return null;
    }

    @Override
    public Type typeCheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        Type type;
        type = this.expression.typeCheck(typeEnv);
        if(type instanceof RefType){
                RefType reft = (RefType) type;
                return reft.getInner();
        }
        else {
            throw new TypeCheckException("Expression of rH is not a reference!");
        }
    }

    @Override
    public String toString() {
        return "rH(" + this.expression +")";
    }
}
