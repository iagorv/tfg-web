package com.example.achievity.Controller;

import com.example.achievity.Model.DTOs.RegistroDTO;
import com.example.achievity.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        boolean exito = usuarioService.registrar(registroDTO);

        if (exito) {
            model.addAttribute("success", "Cuenta creada con éxito. Por favor, inicia sesión.");
            redirectAttributes.addFlashAttribute("success", "Cuenta creada con éxito. Por favor, inicia sesión.");
            return "redirect:/login"; // Redirige a login si se registra bien
        } else {
            model.addAttribute("error", "Error al registrar. El correo puede estar en uso.");
            return "registro"; // Vuelve al registro si falla
        }
    }
}
