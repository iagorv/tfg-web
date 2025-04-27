package com.example.achievity.Service;

import com.example.achievity.Model.DTOs.LoginDTO;
import com.example.achievity.Model.DTOs.RegistroDTO;
import com.example.achievity.Model.DTOs.UsuarioDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class UsuarioService {
    private final RestTemplate restTemplate;

    public UsuarioService() {
        this.restTemplate = new RestTemplate();
    }

    // Método para hacer login con la API externa
    public UsuarioDTO login(LoginDTO loginDTO) {
        String url = "http://localhost:8080/api/login";

        try {
            return restTemplate.postForObject(url, loginDTO, UsuarioDTO.class);
        } catch (Exception e) {

            return null;
        }
    }

    public boolean registrar(RegistroDTO registroDTO) {
        String url = "http://localhost:8080/api/registro";

        try {
            restTemplate.postForEntity(url, registroDTO, Void.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}