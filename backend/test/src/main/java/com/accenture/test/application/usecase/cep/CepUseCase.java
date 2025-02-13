package com.accenture.test.application.usecase.cep;

import com.accenture.test.application.gateways.cep.CepGateway;
import com.accenture.test.domain.cep.Cep;

/**
 *
 * The Cep use case.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public class CepUseCase {

    private CepGateway cepGateway;

    public CepUseCase(CepGateway cepGateway) {
        this.cepGateway = cepGateway;
    }

    /**
     *
     * This function allows to get information by cep.
     *
     * @param cep the cep string
     * @return return a Cep entity object.
     */
    public Cep getCep(String cep) {
        return cepGateway.getCep(cep);
    }
}
