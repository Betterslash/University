package Model.except;

public class MyException extends Exception{
    private final String message;

    public MyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
