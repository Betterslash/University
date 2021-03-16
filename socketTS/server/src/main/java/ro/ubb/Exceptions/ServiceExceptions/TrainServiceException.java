package ro.ubb.Exceptions.ServiceExceptions;

public class TrainServiceException extends RuntimeException{
    public TrainServiceException(String message) {
        super(message);
    }
}
