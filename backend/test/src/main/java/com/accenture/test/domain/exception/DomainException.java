package com.accenture.test.domain.exception;

import java.io.Serial;

/**
 *
 * The Domain exception.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public class DomainException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DomainException(String message) {
        super(message);
    }
}
