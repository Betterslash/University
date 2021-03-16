package ro.ubb.Model.Exceptions.ServiceExceptions;

public class TrainServiceException extends RuntimeException{
    public TrainServiceException(String message) {
        super(message);
    }
}
