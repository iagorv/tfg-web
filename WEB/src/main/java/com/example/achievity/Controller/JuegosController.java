package com.example.achievity.Controller;

import com.example.achievity.Authentication.SessionManager;
import com.example.achievity.Model.DTOs.JuegoDetalleDTO;
import com.example.achievity.Service.JuegoApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    @GetMapping("/juegos/{idJuego}/review")
    public String mostrarFormularioReview(@PathVariable Long idJuego, Model model) {
        if (!sessionManager.estaLogeado()) {
            return "redirect:/login";
        }
        var juegoNombre = juegoApiService.obtenerNombreJuegoPorId(idJuego);

        if (juegoNombre == null) {
            return "redirect:/index";
        }

        model.addAttribute("juego", juegoNombre);
        return "review";
    }

    @GetMapping("/juegos/{id}")
    public String detalleJuego(@PathVariable Long id, Model model) {
        JuegoDetalleDTO juego = juegoApiService.obtenerJuegoPorId(id);
        if (!sessionManager.estaLogeado()) {
            return "redirect:/login";
        }
        if (juego == null) {
            return "redirect:/index"; // o una vista de error
        }

        model.addAttribute("juego", juego);
        return "detalle-juego";
    }




}
