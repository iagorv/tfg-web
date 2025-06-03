package com.example.achievity.Service;

import com.example.achievity.Model.DTOs.BusquedaResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BusquedaService {

    private final WebClient webClient;

    public BusquedaService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8080").build(); // URL de la API
    }

    public BusquedaResponseDTO buscar(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/busqueda")
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .bodyToMono(BusquedaResponseDTO.class)
                .block(); // ← usa block solo si estás en un controlador MVC
    }
}

