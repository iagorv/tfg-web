package com.example.achievity.Controller;

import com.example.achievity.Service.JuegoApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JuegosController {

    private final JuegoApiService juegoApiService;

    public JuegosController(JuegoApiService juegoApiService) {
        this.juegoApiService = juegoApiService;
    }

    @GetMapping("/juegos")
    public String mostrarJuegos(Model model) {
        model.addAttribute("juegos", juegoApiService.obtenerResumenDeJuegos());
        return "juegos"; // Renderiza juegos.html
    }
}
