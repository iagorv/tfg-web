package com.example.achievity.Controller;

import com.example.achievity.Authentication.SessionManager;
import com.example.achievity.Model.DTOs.JuegoDetalleDTO;
import com.example.achievity.Model.DTOs.JuegoResumenDTO;
import com.example.achievity.Model.DTOs.ReviewConUsuarioDTO;
import com.example.achievity.Model.DTOs.ReviewDTO;
import com.example.achievity.Service.JuegoApiService;
import com.example.achievity.Service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String mostrarJuegos(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "12") int size,
                                Model model) {

        Page<JuegoResumenDTO> juegosPage = juegoApiService.obtenerJuegosPaginados(page, size);

        model.addAttribute("juegos", juegosPage.getContent());
        model.addAttribute("paginaActual", page);
        model.addAttribute("totalPaginas", juegosPage.getTotalPages());
        model.addAttribute("size", size);
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
