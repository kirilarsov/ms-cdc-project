package edu.kirilarsov.cdc.card.exception;

/**
 * RestApiNotFoundException exception for signaling not found.
 */
public class RestApiNotFoundException extends RuntimeException {

    private final RestApiErrorCode code;

    public RestApiNotFoundException(RestApiErrorCode code) {
        super(code.getErrorMessage());
        this.code = code;
    }

    public RestApiErrorCode getCode() {
        return code;
    }

}
