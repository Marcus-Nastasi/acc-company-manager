package com.accenture.test.adapter.output.company;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * The company without supplier dto.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public record CompanyCleanDto(
        UUID id,
        String cnpj,
        String nome_fantasia,
        String cep
) implements Serializable {}
