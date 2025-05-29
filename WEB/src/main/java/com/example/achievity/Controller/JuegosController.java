package com.example.achievity.Controller;

import com.example.achievity.Authentication.SessionManager;
import com.example.achievity.Model.DTOs.JuegoDetalleDTO;
import com.example.achievity.Model.DTOs.JuegoResumenDTO;
import com.example.achievity.Model.DTOs.ReviewConUsuarioDTO;
import com.example.achievity.Model.DTOs.ReviewDTO;
import com.example.achievity.Service.JuegoApiService;
import com.example.achievity.Service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class JuegosController {

    private final JuegoApiService juegoApiService;
    private final SessionManager sessionManager;
    private final ReviewService reviewService;

    public JuegosController(JuegoApiService juegoApiService, SessionManager sessionManager, ReviewService reviewService) {
        this.juegoApiService = juegoApiService;
        this.sessionManager = sessionManager;
        this.reviewService = reviewService;
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
        if (!sessionManager.estaLogeado()) {
            return "redirect:/login";
        }

        JuegoDetalleDTO juego = juegoApiService.obtenerJuegoPorId(id);

        if (juego == null) {
            return "redirect:/index";
        }

        List<ReviewConUsuarioDTO> reviews = reviewService.obtenerReviewsPorJuego(id);

        // 👇 Juegos similares
        List<JuegoResumenDTO> juegosSimilares = juegoApiService.obtenerJuegosSimilares(id);

        model.addAttribute("juego", juego);
        model.addAttribute("reviews", reviews);
        model.addAttribute("similares", juegosSimilares); // ⬅️ Añadido

        return "detalle-juego";
    }







}
