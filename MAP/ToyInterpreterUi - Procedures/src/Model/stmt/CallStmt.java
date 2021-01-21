package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IFDict;
import Model.adt.IHeap;
import Model.adt.MyPair;
import Model.except.MyException;
import Model.except.StatementException;
import Model.exp.Expression;
import Model.exp.ValueExp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CallStmt implements IStmt{
    String name;
    final List<Expression> expressionsList;
    public CallStmt(String name, List<Expression> expressionsList){
        this.name = name;
        this.expressionsList = expressionsList;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, StatementException {
        IDict<String, Value> symTable = state.getTopOfSymStack();
        IHeap<Integer, Value> heapTable = state.getHeapTable();
        IFDict<String, BufferedReader> valueIFDict = state.getFileTable();
        IDict<String, MyPair<List<String>, IStmt>> globalTable = state.getGlobalTable();
        if(globalTable.lookup(name) == null){
            throw new MyException("No so procedure called" + name + " was found!");
        }else{
            MyPair<List<String>, IStmt> procedure = state.getGlobalTable().lookup(name);
            List<Value> values = this.expressionsList.stream()
                    .map(e -> {
                        try {
                            return e.evaluate(symTable, valueIFDict, heapTable);
                        } catch (MyException myException) {
                            myException.printStackTrace();
                        }
                        return null;
                    }).collect(Collectors.toList());
            List<String> mappingList = procedure.getKey();
            state.getSymTables().push(state.getTopOfSymStack().clone());
            state.getExeStack().push(new ReturnStmt());
            state.getExeStack().push(procedure.getValue());
            for(int i = 0; i < values.size(); i++){
                String value = mappingList.get(i);
                state.getExeStack().push(new AssignStmt(value, new ValueExp(values.get(i))));
                state.getExeStack().push(new VarDeclStmt(value, values.get(i).getType()));
            }
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "Call "+ this.name +"(" +
                this.expressionsList.toString() +" )";
    }
}
