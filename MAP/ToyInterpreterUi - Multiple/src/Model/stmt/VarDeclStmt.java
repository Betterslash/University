package Model.stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.UnknownValue;
import Model.Values.Value;
import Model.adt.IDict;
import Model.adt.IStack;


public class VarDeclStmt implements IStmt{
    public String id;
    Type type;
    public VarDeclStmt(String id, Type type){
        this.id = id;
        this.type = type;
    }
    @Override
    public PrgState execute(PrgState state) {
        IDict<String, Value> symTable = state.getSymTable().clone().pop();
        Value v = new UnknownValue(this.type);
        symTable.add(this.id,v);
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) {
        typeEnv.add(id,type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type + " " +id ;
    }
}
