package org.evercley.picpaydesafiobackend.services;

import org.evercley.picpaydesafiobackend.dtos.AuthorizeResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthorizationService {
    private final WebClient webClient;

    public AuthorizationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://util.devi.tools/api/v2").build();
    }

    public Boolean authorizeTransaction(Long payerId, Double value) {
        AuthorizeResponseDTO responseDTO = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/authorize")
                        .build()
                )
                .retrieve()
                .bodyToMono(AuthorizeResponseDTO.class)
                .block();

        return responseDTO != null
                && responseDTO.getData().isAuthozization();
    }

}
