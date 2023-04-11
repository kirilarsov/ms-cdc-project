package edu.kirilarsov.cdc.transaction.exception;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * RestApiError record for defining error response schema.
 */
public record RestApiError(
        @Schema(
                required = true,
                example = "Required request parameter 'requesterUuid' for method parameter type String is not present",
                name = "developerMessage",
                title = "Developer contract.message"
        )
        String developerMessage,

        @Schema(
                required = true,
                example = "invalid.request.data",
                name = "developerCode",
                title = "Developer code"
        )
        String developerCode,

        @Schema(
                example = "Please provide an amount bigger than zero",
                name = "userMessage",
                title = "User contract.message"
        )
        String userMessage,

        @Schema(
                example = "amount.not.positive",
                name = "userMessageCode",
                title = "User contract.message code"
        )
        String userMessageCode,

        @Schema(
                example = "Confluence link to documentation on how to fix the problem",
                name = "moreInfo",
                title = "More info"
        )
        String moreInfo,

        @Schema(
                example = "477195011b2541c3bde28d16e19c7388",
                name = "supportId",
                title = "Support id"
        )
        String supportId
) {
    public RestApiError(String developerMessage, String developerCode) {
        this(developerMessage, developerCode, null, null, null, null);
    }

    public RestApiError(String developerMessage, String developerCode, String supportId) {
        this(developerMessage, developerCode, null, null, null, supportId);
    }
}
