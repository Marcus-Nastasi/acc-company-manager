package com.accenture.test.adapter.output.supplier;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * The supplier without companies dto.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public record SupplierCleanDto(
        UUID id,
        String cnpj_cpf,
        String rg,
        LocalDate nascimento,
        String nome,
        String email,
        String cep,
        boolean e_pf
) implements Serializable {}
