package com.example.achievity.Service;


import com.example.achievity.Model.DTOs.JuegoResumenDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class JuegoApiService {


    private final WebClient webClient;

    public JuegoApiService() {
        this.webClient = WebClient.create("http://localhost:8080");
    }

    public List<JuegoResumenDTO> obtenerResumenDeJuegos() {
        return webClient.get()
                .uri("/api/juegos/resumen")
                .retrieve()
                .bodyToFlux(JuegoResumenDTO.class)
                .collectList()
                .block();
    }
}
