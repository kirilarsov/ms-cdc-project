package edu.kirilarsov.cdc.transaction.exception;

/**
 * RestApiErrorCode enum for defining error codes.
 */
public enum RestApiErrorCode {

    INTERNAL_SERVER_ERROR(
        "internal.server.error",
        "Internal server error"
    ),
    VALIDATION_FAILED(
        "invalid.request.data",
        "Validation failed"
    ),
    NON_EXISTENT_FUNDING_ACCOUNT(
        "non.existent.funding.account",
        "Funding account with provided companyUuid doesn't exist"
    ),
    NOT_FOUND(
        "not.found",
        "Resource not found"
    ),
    NON_EXISTENT_DEBIT_ACCOUNT(
        "non.existent.debit.account",
        "Debit account with provided cardUuid doesn't exist"
    );

    private final String errorCode;

    private final String errorMessage;

    RestApiErrorCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
