package Model.exp;

import Model.Types.Type;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.TypeCheckException;

import java.io.BufferedReader;

public abstract class Expression {
    public abstract Value evaluate(IDict<String, Value> symTable, IFDict<String, BufferedReader> fileTable, IHeap<Integer, Value> heapTable) throws MyException, ExpressionException;
    public abstract Type typeCheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException;
}
