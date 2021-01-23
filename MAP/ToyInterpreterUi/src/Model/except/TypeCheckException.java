package Model.except;

public class TypeCheckException extends Exception{
    private final String message;


    public TypeCheckException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
