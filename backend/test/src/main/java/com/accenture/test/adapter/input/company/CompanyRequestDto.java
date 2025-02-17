package com.accenture.test.adapter.input.company;

import com.accenture.test.domain.supplier.Supplier;

import java.util.List;
import java.util.UUID;

/**
 *
 * The company request dto class.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public record CompanyRequestDto(
        UUID id,
        String cnpj,
        String nome_fantasia,
        String cep,
        List<Supplier> fornecedores
) {}
