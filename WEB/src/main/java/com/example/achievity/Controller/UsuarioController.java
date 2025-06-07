package com.example.achievity.Controller;

import com.example.achievity.Authentication.SessionManager;
import com.example.achievity.Model.DTOs.ReviewDTO;
import com.example.achievity.Model.DTOs.UsuarioInfoDTO;
import com.example.achievity.Model.DTOs.UsuarioUpdateDTO;
import com.example.achievity.Service.JuegoApiService;
import com.example.achievity.Service.ReviewService;
import com.example.achievity.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            return "redirect:/index";
        }

        Long userId = sessionManager.getIdUsuarioLogeado();
        UsuarioInfoDTO usuarioInfo = usuarioService.obtenerInfoUsuario(userId);

        List<ReviewDTO> ultimasReviews = reviewService.obtenerUltimasTresReviews(userId);

        model.addAttribute("usuario", usuarioInfo);
        model.addAttribute("ultimasReviews", ultimasReviews);

        return "perfil";
    }
    @PostMapping("/perfil/actualizar")
    public String actualizarPerfil(@ModelAttribute UsuarioUpdateDTO usuarioUpdateDTO, RedirectAttributes redirectAttributes) {
        Long userId = sessionManager.getIdUsuarioLogeado();

        try {
            usuarioService.actualizarUsuario(userId, usuarioUpdateDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Perfil actualizado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo actualizar el perfil: " + e.getMessage());
        }

        return "redirect:/perfil";
    }
}
