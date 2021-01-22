package Model.except;

public class StatementException extends Exception {
    private final String message;

    public StatementException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
