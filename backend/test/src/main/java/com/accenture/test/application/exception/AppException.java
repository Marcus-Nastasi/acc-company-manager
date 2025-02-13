package com.accenture.test.application.exception;

import java.io.Serial;

/**
 *
 * The App exception.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public class AppException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AppException(String message) {
        super(message);
    }
}
