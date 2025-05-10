package com.example.achievity.Service;


import com.example.achievity.Model.DTOs.ReviewDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


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

}
