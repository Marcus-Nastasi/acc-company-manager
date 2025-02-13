package com.accenture.test.application.gateways.cep;

import com.accenture.test.domain.cep.Cep;

/**
 *
 * The Cep interface.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public interface CepGateway {

    /**
     *
     * This function should allow to get information by cep.
     *
     * @param cep the cep string
     * @return return a Cep entity object.
     */
    Cep getCep(String cep);
}
