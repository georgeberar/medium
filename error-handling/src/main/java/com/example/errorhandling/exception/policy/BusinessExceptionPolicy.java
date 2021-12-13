package com.example.errorhandling.exception.policy;

import org.springframework.http.HttpStatus;

/**
 * Defines the policy contract for custom business exceptions which need to adhere to it for providing a standardized
 * behavior.
 */
public interface BusinessExceptionPolicy extends ExceptionPolicy {

    /**
     * Get the http status associated with the exception.
     *
     * @return the http status associated with the exception
     */
    HttpStatus getHttpStatus();

}
