package com.accenture.test.infrastructure.gateway.cep;

import com.accenture.test.application.gateways.cep.CepGateway;
import com.accenture.test.domain.cep.Cep;
import com.accenture.test.infrastructure.exception.InfraException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * The Cep service implementation.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public class CepRepoGateway implements CepGateway {

    private final WebClient webClient = WebClient.create();

    /**
     *
     * This function allows to retrieve data from a location, by postal code.
     *
     * @param cep the cep string.
     *
     * @return the location info in a Cep entity model.
     */
    @Override
    public Cep getCep(String cep) {
        try {
            cep = cep.replaceAll("\\D", "").trim();
            if (cep.length() > 8) throw new InfraException("Invalid cep");
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            ResponseEntity<Cep> cepResponseEntity = webClient.get().uri(url).retrieve().toEntity(Cep.class).block();
            if (cepResponseEntity == null) throw new InfraException("Unable to get CEP, equals null");
            return cepResponseEntity.getBody();
        } catch (RuntimeException e) {
            throw new InfraException("Error getting CEP: " + e.getMessage());
        }
    }
}
