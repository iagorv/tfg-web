package com.example.achievity.Service;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class JuegoApiService {


    private final WebClient webClient;

    public JuegoApiService() {
        this.webClient = WebClient.create("http://localhost:8080");
    }

    public List<String> obtenerNombresDeJuegos() {
        return webClient.get()
                .uri("/api/juegos/nombres")
                .retrieve()
                .bodyToFlux(String.class)
                .collectList()
                .block(); // ¡Ojo! Esto es síncrono, está bien para este caso simple.
    }
}
