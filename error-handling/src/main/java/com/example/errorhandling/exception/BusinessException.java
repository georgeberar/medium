package com.example.errorhandling.exception;

import com.example.errorhandling.exception.policy.BusinessExceptionPolicy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;


/**
 * Custom exception class to use in case of a business exception.
 */
@Getter
@Setter
public class BusinessException extends RuntimeException implements BusinessExceptionPolicy {

    protected final String code;
    protected final String message;
    protected final HttpStatus httpStatus;

    /**
     * Constructor accepting an exception reason.
     *
     * @param reason the reason of the exception
     */
    public BusinessException(final BusinessExceptionReason reason) {
        this.code = reason.getCode();
        this.message = reason.getMessage();
        this.httpStatus = reason.getHttpStatus();
    }

    /**
     * Constructor accepting an exception reason and an http status that will override the default one from the reason.
     *
     * @param reason               the reason of the exception
     * @param overridingHttpStatus the http status which overrides the one from the reason
     */
    public BusinessException(final BusinessExceptionReason reason, final HttpStatus overridingHttpStatus) {
        this.code = reason.getCode();
        this.message = reason.getMessage();
        this.httpStatus = overridingHttpStatus;
    }

    /**
     * Constructor accepting an excepting reason and optional parameters which are replaced in the
     * message.
     *
     * @param reason     the reason of the exception
     * @param parameters the optional parameters
     */
    public BusinessException(final BusinessExceptionReason reason, final Object... parameters) {
        if (parameters != null) {
            this.message = format(reason.getMessage(), parameters);
        } else {
            this.message = reason.getMessage();
        }

        this.code = reason.getCode();
        this.httpStatus = reason.getHttpStatus();
    }


    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    public String toString() {
        return format("BusinessException(code=%s, message=%s, httpStatus=%s)", this.getCode(), this.getMessage(),
                this.getHttpStatus().value());
    }
}
