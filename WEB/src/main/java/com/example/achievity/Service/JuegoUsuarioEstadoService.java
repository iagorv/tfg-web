package com.example.achievity.Service;


import com.example.achievity.Model.DTOs.JuegoUsuarioEstadoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class JuegoUsuarioEstadoService {


    private final WebClient webClient;

    public JuegoUsuarioEstadoService() {
        this.webClient = WebClient.create("http://localhost:8080");
    }

    public String obtenerEstadoJuegoParaUsuario(Long juegoId, Long usuarioId) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/estado-juego/{idJuego}")
                            .queryParam("usuarioId", usuarioId)
                            .build(juegoId))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            return null;
        }
    }
    public void guardarEstadoJuego(Long juegoId, Long usuarioId, String estado) {
        JuegoUsuarioEstadoDTO dto = new JuegoUsuarioEstadoDTO(usuarioId, juegoId, estado);

        webClient.post()
                .uri("/api/estado-juego")
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(Void.class)
                .block(); // Esperamos respuesta (puedes manejar errores si lo deseas)
    }


}
