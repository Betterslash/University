package ro.ubb.Model.Exceptions.ServerExceptions;

public class TCPServerException extends RuntimeException{
    public TCPServerException(String message){
        super(message);
    }
}
