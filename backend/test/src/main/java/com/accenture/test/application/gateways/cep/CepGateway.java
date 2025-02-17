package com.accenture.test.application.gateways.cep;

import com.accenture.test.domain.cep.Cep;

/**
 *
 * The postal code interface.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public interface CepGateway {

    /**
     *
     * This function should allow to get information by postal code.
     *
     * @param cep the postal code string.
     *
     * @return return a postal code entity object.
     */
    Cep getCep(String cep);
}
