package edu.kirilarsov.cdc.transaction.exception;

/**
 * RestApiBadRequestException exception for signaling bad requests.
 */
public class RestApiBadRequestException extends RuntimeException {

    private final RestApiErrorCode code;

    public RestApiBadRequestException(RestApiErrorCode code) {
        super(code.getErrorMessage());
        this.code = code;
    }

    public RestApiErrorCode getCode() {
        return code;
    }
}

