package ro.ubb.Model.Exceptions.DomainExceptions;

public class TimeFormatException extends RuntimeException
{
    public TimeFormatException(String message)
    {
        super(message);
    }
}
