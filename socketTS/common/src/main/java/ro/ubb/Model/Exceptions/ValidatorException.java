package ro.ubb.Model.Exceptions;

import ro.ubb.Model.Exceptions.DomainExceptions.DomainException;

public class ValidatorException extends DomainException {
    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}