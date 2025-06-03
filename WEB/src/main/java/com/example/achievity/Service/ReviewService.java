package com.example.achievity.Service;


import com.example.achievity.Model.DTOs.ReviewConUsuarioDTO;
import com.example.achievity.Model.DTOs.ReviewCrearDTO;
import com.example.achievity.Model.DTOs.ReviewDTO;
import com.example.achievity.util.RestResponsePage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReviewService {

    private final WebClient webClient;

    public ReviewService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }


    public List<ReviewDTO> obtenerUltimasTresReviews(Long usuarioId) {
        return webClient.get()
                .uri("/api/review/usuario/{id}/ultimas", usuarioId)
                .retrieve()
                .bodyToFlux(ReviewDTO.class)
                .collectList()
                .block();
    }

    public void enviarReview(ReviewCrearDTO reviewDTO) {
        webClient.post()
                .uri("/api/review/add")
                .bodyValue(reviewDTO)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public List<ReviewConUsuarioDTO> obtenerReviewsPorJuego(Long juegoId) {
        return webClient.get()
                .uri("/api/review/juego/{juegoId}/con-usuario", juegoId)
                .retrieve()
                .bodyToFlux(ReviewConUsuarioDTO.class)
                .collectList()
                .block();
    }

    public Page<ReviewDTO> obtenerReviewsPaginadas(Long usuarioId, int page, int size) {
        ParameterizedTypeReference<RestResponsePage<ReviewDTO>> responseType =
                new ParameterizedTypeReference<>() {};

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/review/usuario/{id}/paginas")
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build(usuarioId))
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }






}
