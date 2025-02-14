package com.accenture.test.infrastructure.exception;

import java.io.Serial;

/**
 *
 * The infrastructure exception.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public class InfraException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InfraException(String message) {
        super(message);
    }
}
