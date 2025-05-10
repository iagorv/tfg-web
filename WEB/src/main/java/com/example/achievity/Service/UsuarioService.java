package com.example.achievity.Service;

import com.example.achievity.Model.DTOs.LoginDTO;
import com.example.achievity.Model.DTOs.RegistroDTO;
import com.example.achievity.Model.DTOs.UsuarioDTO;
import com.example.achievity.Model.DTOs.UsuarioInfoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class UsuarioService {
    private final WebClient webClient;

    public UsuarioService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public UsuarioDTO login(LoginDTO loginDTO) {
        return webClient.post()
                .uri("/api/login")
                .bodyValue(loginDTO)
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();
    }

    public void registrar(RegistroDTO registroDTO) {
        webClient.post()                   // metodo POST para enviar datos, es los juegos tengo get
                .uri("/api/user/registro")       // URL del endpoint
                .bodyValue(registroDTO)     // lo que le envías a la api
                .retrieve()                 // queremos recibir una respuesta
                .toBodilessEntity()         // no necesitamos un objeto ni nada de la respuesta, solo si se completó bien
                .block();                   //para hacerlo sincronico
    }

    public UsuarioInfoDTO obtenerInfoUsuario(Long id) {
        return webClient.get()
                .uri("/api/user/" + id)
                .retrieve()
                .bodyToMono(UsuarioInfoDTO.class)
                .block();
    }



}