package com.example.achievity.Controller;

import com.example.achievity.Authentication.SessionManager;
import com.example.achievity.Model.DTOs.ReviewDTO;
import com.example.achievity.Model.DTOs.UsuarioInfoDTO;
import com.example.achievity.Service.JuegoApiService;
import com.example.achievity.Service.ReviewService;
import com.example.achievity.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsuarioController {


    private final SessionManager sessionManager;
    private final UsuarioService usuarioService;
    private final ReviewService reviewService;


    public UsuarioController(UsuarioService usuarioService, SessionManager sessionManager, ReviewService reviewService) {
        this.usuarioService = usuarioService;
        this.sessionManager = sessionManager;
        this.reviewService = reviewService;
    }


    @GetMapping("/perfil")
    public String mostrarPerfil(Model model) {
        if (!sessionManager.estaLogeado()) {
            return "redirect:/login";
        }

        Long userId = sessionManager.getIdUsuarioLogeado();
        UsuarioInfoDTO usuarioInfo = usuarioService.obtenerInfoUsuario(userId);

        List<ReviewDTO> ultimasReviews = reviewService.obtenerUltimasTresReviews(userId);

        model.addAttribute("usuario", usuarioInfo);
        model.addAttribute("ultimasReviews", ultimasReviews);

        return "perfil";
    }
}
