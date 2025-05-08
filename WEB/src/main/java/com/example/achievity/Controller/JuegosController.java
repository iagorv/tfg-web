package com.example.achievity.Controller;

import com.example.achievity.Authentication.SessionManager;
import com.example.achievity.Service.JuegoApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JuegosController {

    private final JuegoApiService juegoApiService;
    private final SessionManager sessionManager;

    public JuegosController(JuegoApiService juegoApiService, SessionManager sessionManager) {
        this.juegoApiService = juegoApiService;
        this.sessionManager = sessionManager;
    }
    @GetMapping("/juegos")
    public String mostrarJuegos(Model model) {
        model.addAttribute("juegos", juegoApiService.obtenerResumenDeJuegos());
        return "juegos";
    }


    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        if (!sessionManager.estaLogeado()) {
            return "redirect:/login"; 
        }
        model.addAttribute("juegos", juegoApiService.obtenerJuegosPopulares());
        return "index";
    }
}
