package com.example.achievity.Service;


import com.example.achievity.Model.DTOs.JuegoDetalleDTO;
import com.example.achievity.Model.DTOs.JuegoNombreDTO;
import com.example.achievity.Model.DTOs.JuegoResumenDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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
    public List<JuegoResumenDTO> obtenerJuegosPopulares() {
        return webClient.get()
                .uri("/api/juegos/populares") // Cambiamos el endpoint aquí
                .retrieve()
                .bodyToFlux(JuegoResumenDTO.class)
                .collectList()
                .block();
    }
    public JuegoNombreDTO obtenerNombreJuegoPorId(Long id) {
        try {
            return webClient.get()
                    .uri("/api/juegos/{id}/nombre", id)
                    .retrieve()
                    .bodyToMono(JuegoNombreDTO.class)
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            return null;
        }
    }
    public JuegoDetalleDTO obtenerJuegoPorId(Long id) {
        try {
            return webClient.get()
                    .uri("/api/juegos/{id}", id)
                    .retrieve()
                    .bodyToMono(JuegoDetalleDTO.class)
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            return null; // Puedes redirigir o mostrar error si es null
        }
    }

    public List<JuegoResumenDTO> obtenerJuegosSimilares(Long id) {
        return webClient.get()
                .uri("/api/juegos/{id}/similares", id)
                .retrieve()
                .bodyToFlux(JuegoResumenDTO.class)
                .collectList()
                .block();
    }


}
