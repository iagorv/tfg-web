package com.example.achievity.Controller;

import com.example.achievity.Authentication.SessionManager;
import com.example.achievity.Model.DTOs.BitacoraDTO;
import com.example.achievity.Model.DTOs.ReviewCrearDTO;
import com.example.achievity.Model.DTOs.ReviewDTO;
import com.example.achievity.Service.BitacoraService;
import com.example.achievity.Service.ReviewService;
import com.example.achievity.Service.UsuarioService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ReviewController {


    private final BitacoraService bitacoraService;
    private final ReviewService reviewService;
    private final SessionManager sessionManager;

    public ReviewController(ReviewService reviewService, SessionManager sessionManager,BitacoraService bitacoraService) {
        this.reviewService = reviewService;
        this.sessionManager = sessionManager;
        this.bitacoraService = bitacoraService;
    }

    @PostMapping("/reviews")
    public String enviarReview(@ModelAttribute ReviewCrearDTO reviewDTO,
                               RedirectAttributes redirectAttributes) {
        Long usuarioId = sessionManager.getIdUsuarioLogeado();

        if (usuarioId == null) {
            return "redirect:/index";
        }

        reviewDTO.setUsuarioId(usuarioId);


        System.out.println("Enviando review: " + reviewDTO);

        try {
            reviewService.enviarReview(reviewDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Reseña enviada con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al enviar la reseña.");
        }

        return "redirect:/index";
    }


    @GetMapping("/mis-reviews")
    public String verMisActividad(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "6") int size,
                                  @RequestParam(defaultValue = "reseñas") String view,
                                  Model model) {

        Long usuarioId = sessionManager.getIdUsuarioLogeado();
        if (usuarioId == null) return "redirect:/index";

        model.addAttribute("usuario", Map.of("id", usuarioId));
        model.addAttribute("view", view);

        if ("reseñas".equals(view)) {
            Page<ReviewDTO> reviewsPage = reviewService.obtenerReviewsPaginadas(usuarioId, page, size);

            model.addAttribute("misReviews", reviewsPage.getContent());
            model.addAttribute("paginaActual", page);
            model.addAttribute("totalPaginas", reviewsPage.getTotalPages());
            model.addAttribute("size", size);
        } else if ("bitacora".equals(view)) {
            Page<BitacoraDTO> bitacoraPage = bitacoraService.obtenerBitacorasPaginadas(usuarioId, page, size);

            model.addAttribute("misBitacoras", bitacoraPage.getContent());
            model.addAttribute("paginaActual", page);
            model.addAttribute("totalPaginas", bitacoraPage.getTotalPages());
            model.addAttribute("size", size);
        }

        return "mis-reviews";
    }

    @GetMapping("/reviews/editar")
    public String mostrarFormularioReviewEdicion(
            @RequestParam Long reviewId,
            @RequestParam Long juegoId,
            @RequestParam String juegoNombre,
            @RequestParam int nota,
            @RequestParam String reseña,
            Model model) {

        if (!sessionManager.estaLogeado()) {
            return "redirect:/index";
        }


        Map<String, Object> juego = new HashMap<>();
        juego.put("id", juegoId);
        juego.put("nombre", juegoNombre);

        model.addAttribute("juego", juego); // Esto ya cubre juego.id y juego.nombre en el HTML
        model.addAttribute("esEdicion", true);
        model.addAttribute("nota", nota);
        model.addAttribute("reseña", reseña);
        model.addAttribute("reviewId", reviewId);

        return "review"; // Reutiliza el HTML existente
    }

    @GetMapping("/reviews")
    public String verReviewsPublicas(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "6") int size,
                                     Model model) {

        Page<ReviewDTO> reviewsPage = reviewService.obtenerTodasLasReviews(page, size);

        model.addAttribute("reviews", reviewsPage.getContent());
        model.addAttribute("paginaActual", page);
        model.addAttribute("totalPaginas", reviewsPage.getTotalPages());
        model.addAttribute("size", size);

        return "reviews";
    }








}
