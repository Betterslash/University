package ro.ubb.Exceptions;

public class DBOServiceException extends RuntimeException{
    public DBOServiceException(String message) {
        super(message);
    }

    public DBOServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBOServiceException(Throwable cause) {
        super(cause);
    }
}
