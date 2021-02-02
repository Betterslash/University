package Model;

import Model.stmt.IStmt;

import java.util.List;

public class Procedure {
    private final String name;
    private final List<String> params;
    private final IStmt procBody;
    public Procedure(String name, List<String> params, IStmt procBody){
        this.name = name;
        this.params = params;
        this.procBody = procBody;
    }

    public String getName() {
        return name;
    }

    public IStmt getProcBody() {
        return procBody;
    }

    public List<String> getParams() {
        return params;
    }

    @Override
    public String toString() {
        String paramsR = this.params.stream()
                .map(Object::toString)
                .reduce("",(a, b)-> a+", "+b);
        paramsR = paramsR.substring(1);
        return this.name +"(" + paramsR + ") " + this.procBody;
    }
}
