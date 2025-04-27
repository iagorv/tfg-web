package com.example.achievity.Controller;

import com.example.achievity.Model.DTOs.RegistroDTO;
import com.example.achievity.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String registrarUsuario(RegistroDTO registroDTO, Model model) {
        boolean exito = usuarioService.registrar(registroDTO);

        if (exito) {
            return "redirect:/login"; // Redirige a login si se registra bien
        } else {
            model.addAttribute("error", "Error al registrar. El correo puede estar en uso.");
            return "registro"; // Vuelve al registro si falla
        }
    }
}
