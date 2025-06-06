package com.example.achievity.Service;

import com.example.achievity.Model.DTOs.BitacoraDTO;
import com.example.achievity.util.RestResponsePage;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BitacoraService {

    private final WebClient webClient;

    public BitacoraService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Page<BitacoraDTO> obtenerBitacorasPaginadas(Long usuarioId, int page, int size) {
        ParameterizedTypeReference<RestResponsePage<BitacoraDTO>> responseType =
                new ParameterizedTypeReference<>() {};

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/bitacora/usuario/{id}/paginas")
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build(usuarioId))
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }
}
