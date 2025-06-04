package com.example.achievity.Service;

import com.example.achievity.Authentication.UsuarioDesactivadoException;
import com.example.achievity.Model.DTOs.*;
import org.springframework.http.HttpStatus;
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
        try {
            return webClient.post()
                    .uri("/api/login")
                    .bodyValue(loginDTO)
                    .retrieve()
                    .bodyToMono(UsuarioDTO.class)
                    .block();
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                // Credenciales inválidas
                return null;
            } else if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                // Usuario desactivado
                throw new UsuarioDesactivadoException("Usuario desactivado");
            } else {
                throw e;
            }
        }
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
    public void actualizarUsuario(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        webClient.put()
                .uri("/api/user/" + id)
                .bodyValue(usuarioUpdateDTO)
                .retrieve()
                .toBodilessEntity()
                .block();
    }



}