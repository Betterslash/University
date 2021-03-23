package ro.ubb.Model.Exceptions.ServiceExceptions;

public class AbstractServiceException extends RuntimeException{
    public AbstractServiceException(String message) {
        super(message);
    }
}
