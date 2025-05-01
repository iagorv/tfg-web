package com.example.achievity.Controller;

import com.example.achievity.Model.DTOs.RegistroDTO;
import com.example.achievity.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro"; // Muestra registro.html
    }

    @PostMapping("/registro")
    public String registrarUsuario(RegistroDTO registroDTO, Model model, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.registrar(registroDTO);
            redirectAttributes.addFlashAttribute("success", "Cuenta creada con éxito. Por favor, inicia sesión.");
            return "redirect:/login";
        } catch (HttpClientErrorException e) {
            model.addAttribute("error", e.getResponseBodyAsString());
            return "registro";
        }
    }

}
