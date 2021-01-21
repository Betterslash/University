package Model.Values;

import Model.Types.Type;

public class UnknownValue implements Value{
    Type type;
    public UnknownValue(Type type){
        this.type = type;
    }
    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type + "@UnknownValue";
    }
}
