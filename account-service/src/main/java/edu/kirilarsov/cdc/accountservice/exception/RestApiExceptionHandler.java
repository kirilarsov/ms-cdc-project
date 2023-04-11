package edu.kirilarsov.cdc.accountservice.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * RestApiExceptionHandler class for handling/mapping exceptions to error responses.
 */
@ControllerAdvice(annotations = RestController.class)
public class RestApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestApiExceptionHandler.class);

    /**
     * Handle {@link Exception} exception.
     *
     * @param exception  Exception
     * @param webRequest WebRequest
     * @return RestApiErrorResponse as response and set status to HttpStatus.INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RestApiErrorResponse handleInternalServerException(
            Exception exception,
            WebRequest webRequest
    ) {
        var supportId = UUID.randomUUID().toString().replace("-", "");

        logger.error(
                "Internal server exception, for web request: {}, with supportId: {}",
                webRequest.getDescription(false), supportId, exception
        );

        var apiErrorType = RestApiErrorCode.INTERNAL_SERVER_ERROR;

        var error = new RestApiError(
                exception.getMessage(),
                apiErrorType.getErrorCode(),
                supportId
        );

        var errors = new ArrayList<RestApiError>();
        errors.add(error);

        return new RestApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                errors
        );
    }

    /**
     * Handle {@link RestApiBadRequestException} exception.
     *
     * @param exception  Exception
     * @param webRequest WebRequest
     * @return RestApiErrorResponse as response and set status to HttpStatus.BAD_REQUEST
     */
    @ExceptionHandler(RestApiBadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestApiErrorResponse handleBadRequest(
            RestApiBadRequestException exception,
            WebRequest webRequest
    ) {
        logger.debug(
                "Bad request exception: {}, for web request: {}",
                exception.getMessage(), webRequest.getDescription(false)
        );

        var error = new RestApiError(
                exception.getCode().getErrorMessage(),
                exception.getCode().getErrorCode()
        );

        var errors = new ArrayList<RestApiError>();
        errors.add(error);

        return new RestApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                errors
        );
    }

    /**
     * Handle {@link RestApiNotFoundException} exception.
     *
     * @param exception  Exception
     * @param webRequest WebRequest
     * @return RestApiErrorResponse as response and set status to HttpStatus.NOT_FOUND
     */
    @ExceptionHandler(RestApiNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public RestApiErrorResponse handleBadRequest(
        RestApiNotFoundException exception,
        WebRequest webRequest
    ) {
        logger.debug(
            "Not found exception: {}, for web request: {}",
            exception.getMessage(), webRequest.getDescription(false)
        );

        return new RestApiErrorResponse(
            HttpStatus.NOT_FOUND,
            List.of(
                new RestApiError(
                    RestApiErrorCode.NOT_FOUND.getErrorMessage() + ": " + exception.getMessage(),
                    exception.getCode().getErrorCode()
                )
            )
        );
    }

    /**
     * Handle {@link MissingServletRequestParameterException} exception.
     * This includes validation errors on querues etc.
     *
     * @param exception  Exception
     * @param webRequest WebRequest
     * @return RestApiErrorResponse as response and set status to HttpStatus.BAD_REQUEST
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestApiErrorResponse handleQueryValidationErrors(
            MissingServletRequestParameterException exception,
            WebRequest webRequest
    ) {
        logger.debug(
                "Parameter exception: {}, for web request: {}",
                exception.getMessage(), webRequest.getDescription(false)
        );

        var apiErrorType = RestApiErrorCode.VALIDATION_FAILED;

        var error = new RestApiError(
                exception.getMessage(),
                apiErrorType.getErrorCode()
        );

        var errors = new ArrayList<RestApiError>();
        errors.add(error);

        return new RestApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                errors
        );
    }

    /**
     * Handle {@link HttpMessageNotReadableException} exception.
     * This includes sending corrupted data, like "123abc" in a BigDecimal etc.
     *
     * @param exception  Exception
     * @param webRequest WebRequest
     * @return RestApiErrorResponse as response and set status to HttpStatus.BAD_REQUEST
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestApiErrorResponse handleMessageNotReadableErrors(
        InvalidFormatException exception,
        WebRequest webRequest
    ) {
        logger.debug(
            "Invalid format exception: {}, for web request: {}",
            exception.getMessage(), webRequest.getDescription(false)
        );

        var apiErrorType = RestApiErrorCode.VALIDATION_FAILED;

        var error = new RestApiError(
            exception.getMessage(),
            apiErrorType.getErrorCode()
        );

        var errors = new ArrayList<RestApiError>();
        errors.add(error);

        return new RestApiErrorResponse(
            HttpStatus.BAD_REQUEST,
            errors
        );
    }

    /**
     * Handle {@link MethodArgumentNotValidException} by setting the response status
     * and collecting error messages.
     *
     * @param exception MethodArgumentNotValidException
     * @return RestApiErrorResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestApiErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            WebRequest webRequest
    ) {
        var errorMessages = new ArrayList<String>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errorMessages.add(error.getDefaultMessage());
        }
        for (ObjectError error : exception.getBindingResult().getGlobalErrors()) {
            errorMessages.add(error.getDefaultMessage());
        }

        var apiErrorType = RestApiErrorCode.VALIDATION_FAILED;
        List<RestApiError> errors = errorMessages
                .stream()
                .map(message -> new RestApiError(
                        message,
                        apiErrorType.getErrorCode()
                    )
                )
                .collect(Collectors.toList());

        logger.debug(
                "Not valid exception: {}, for web request: {}",
                errorMessages, webRequest.getDescription(false)
        );

        return new RestApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                errors
        );
    }

}
