package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

public class BoolValue implements Value{
    boolean value;
    public BoolValue(boolean value){
        this.value = value;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    public boolean isValue() {
        return value;
    }

    @Override
    public Type getType(){
        return new BoolType();
    }
}
