package Model.Values;

import Model.Types.RefType;
import Model.Types.Type;

public class RefValue implements Value{
    final Type locationType;
    int address;
    public RefValue(Type locationType, int address) {
        this.locationType = locationType;
        this.address = address;
    }

    public int getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "(" +this.locationType +  ", " + this.address+")";
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }
}
