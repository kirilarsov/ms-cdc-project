package edu.kirilarsov.cdc.card.exception;

/**
 * NonExistentAccountException exception for signaling non existent account.
 */
public class NonExistentCardException extends RuntimeException {

    public NonExistentCardException() {

    }

    public NonExistentCardException(String message) {
        super(message);
    }

}
