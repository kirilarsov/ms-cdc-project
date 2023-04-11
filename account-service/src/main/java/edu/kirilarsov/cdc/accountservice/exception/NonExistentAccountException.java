package edu.kirilarsov.cdc.accountservice.exception;

/**
 * NonExistentAccountException exception for signaling non existent account.
 */
public class NonExistentAccountException extends RuntimeException {

    public NonExistentAccountException() {

    }

    public NonExistentAccountException(String message) {
        super(message);
    }

}
