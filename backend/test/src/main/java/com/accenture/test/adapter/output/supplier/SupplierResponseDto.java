package com.accenture.test.adapter.output.supplier;

import com.accenture.test.adapter.output.company.CompanyCleanDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 *
 * The supplier response dto.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public record SupplierResponseDto(
        UUID id,
        String cnpj_cpf,
        String rg,
        LocalDate nascimento,
        String nome,
        String email,
        String cep,
        boolean e_pf,
        List<CompanyCleanDto> empresas
) implements Serializable {}
