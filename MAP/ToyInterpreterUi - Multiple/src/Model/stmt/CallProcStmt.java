package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import Model.adt.*;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.StatementException;
import Model.except.TypeCheckException;
import Model.exp.Expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CallProcStmt implements IStmt{
    final String procName;
    final List<Expression> expressionsList;
    public CallProcStmt(String procName, List<Expression> expressionsList) {
        this.procName = procName;
        this.expressionsList = expressionsList;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException, ExpressionException {
        IStack<IDict<String, Value>> symTable = state.getSymTable();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        IFDict<String, BufferedReader> fileTable = state.getFileTable();
        IProcTable<String, MyPair<List<String>, IStmt>> procTable = state.getProcTable();
        MyPair<List<String>, IStmt> proc = procTable.lookup(this.procName);
        if(proc != null){
            List<String> variables = proc.getKey();
            IStmt procBody = proc.getValue();
            IDict<String, Value> newSymTable = symTable.clone().pop().clone();
            List<Value> values;
            values = this.expressionsList.stream()
                    .map(e -> {
                        try {
                            return e.evaluate(symTable, fileTable, heapTable);
                        } catch (MyException | ExpressionException myException) {
                            myException.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
            for(int i = 0; i < values.size(); i++){
               newSymTable.add(variables.get(i), values.get(i));
            }
            symTable.push(newSymTable);
            state.getExeStack().push(new ReturnStmt());
            state.getExeStack().push(procBody);
        }else {
            throw new StatementException("This procedure you called doesn't exist!");
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException, ExpressionException, TypeCheckException {
        return typeEnv;
    }

    @Override
    public String toString() {
        String params = this.expressionsList.stream()
                .map(Object::toString)
                .reduce("",(a, b)-> a+", "+b);
        return "call "+this.procName+"("+params+")";
    }
}
