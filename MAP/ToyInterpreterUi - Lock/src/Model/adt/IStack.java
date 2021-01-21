package Model.adt;

import Model.stmt.IStmt;

import java.util.Stack;

public interface IStack<T>{
    T pop();
    void push(T item);
    boolean isEmpty();

    Stack<T> getStack();
}
