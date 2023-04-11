package edu.kirilarsov.cdc.transaction.exception;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.springframework.http.HttpStatus;

/**
 * RestApiErrorResponse record for wrapping http error status and list of errors.
 */
public record RestApiErrorResponse(
        @Schema(
                required = true,
                example = "400",
                name = "status",
                title = "Status code"
        )
        HttpStatus status,

        @Schema(
                required = true,
                example = "List<RestApiError>",
                name = "errors",
                title = "Errors"
        )
        List<RestApiError> errors
) {
    public int getStatus() {
        return status.value();
    }
}
