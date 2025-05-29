package com.example.achievity.Controller;

import com.example.achievity.Service.UsuarioService;
import com.example.achievity.Model.DTOs.RegistroDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RegistroControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private RegistroController registroController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registroController).build();
    }

    @Test
    void testRegistroExitoso() throws Exception {
        // Mockeamos que el servicio registrar no lanza excepción y no hace nada
        doNothing().when(usuarioService).registrar(any());

        // Enviamos datos de formulario (form-urlencoded), no JSON porque es MVC normal
        mockMvc.perform(post("/registro")
                        .param("nombre", "John")
                        .param("email", "john@example.com")
                        .param("password", "1234")
                        .param("confirmPassword", "1234")
                        .param("fechaNacimiento", "2000-01-01")
                )
                .andExpect(status().is3xxRedirection())    // Esperamos redirect al login
                .andExpect(redirectedUrl("/login"))
                .andExpect(flash().attribute("success", "Cuenta creada con éxito. Por favor, inicia sesión."));
    }


}