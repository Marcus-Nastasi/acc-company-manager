package com.accenture.test.adapter.input.supplier;

import java.time.LocalDate;

/**
 *
 * The supplier request dto class.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public record SupplierRequestDto(
        String cnpj_cpf,
        String rg,
        LocalDate nascimento,
        String nome,
        String email,
        String cep,
        boolean e_pf
) {}
