package Model.except;

public class ExpressionException extends Exception{
    private final String message;


    public ExpressionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
