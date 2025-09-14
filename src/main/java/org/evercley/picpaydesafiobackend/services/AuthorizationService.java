package org.evercley.picpaydesafiobackend.services;

import org.evercley.picpaydesafiobackend.dtos.AuthorizeRequestDTO;
import org.evercley.picpaydesafiobackend.dtos.AuthorizeResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthorizationService {
    private final WebClient webClient;

    public AuthorizationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://util.devi.tools/api/v2").build();
    }

    public boolean authorizeTransaction(Long payerId, Double value) {
        AuthorizeRequestDTO requestDTO = new AuthorizeRequestDTO();
        requestDTO.setPayerId(payerId);
        requestDTO.setValue(value);

        AuthorizeResponseDTO responseDTO = webClient.post()
                .uri("/authorize")
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(AuthorizeResponseDTO.class)
                .block();

        return responseDTO != null && responseDTO.isAuthorized();
    }

}
