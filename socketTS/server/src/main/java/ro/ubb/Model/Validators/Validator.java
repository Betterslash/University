package ro.ubb.Model.Validators;

import ro.ubb.Exceptions.ValidatorException;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
