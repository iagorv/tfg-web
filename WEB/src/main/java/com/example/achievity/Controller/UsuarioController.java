package com.example.achievity.Controller;

import com.example.achievity.Authentication.SessionManager;
import com.example.achievity.Model.DTOs.UsuarioInfoDTO;
import com.example.achievity.Service.JuegoApiService;
import com.example.achievity.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {


    private final SessionManager sessionManager;
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService, SessionManager sessionManager) {
        this.usuarioService = usuarioService;
        this.sessionManager = sessionManager;
    }


    @GetMapping("/perfil")
    public String mostrarPerfil(Model model) {
        if (!sessionManager.estaLogeado()) {
            return "redirect:/login";
        }

        Long userId = sessionManager.getIdUsuarioLogeado(); // 🔹 Obtén el ID del usuario desde la sesión
        UsuarioInfoDTO usuarioInfo = usuarioService.obtenerInfoUsuario(userId); // 🔹 Llama al servicio para traer datos

        model.addAttribute("usuario", usuarioInfo); // 🔹 Añade al modelo para que Thymeleaf lo use
        return "perfil";
    }
}
