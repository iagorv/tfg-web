package com.example.achievity.Controller;

import com.example.achievity.Authentication.SessionManager;
import com.example.achievity.Model.DTOs.ReviewCrearDTO;
import com.example.achievity.Model.DTOs.ReviewDTO;
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

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final SessionManager sessionManager;

    public ReviewController(ReviewService reviewService, SessionManager sessionManager) {
        this.reviewService = reviewService;
        this.sessionManager = sessionManager;
    }

    @PostMapping("/reviews")
    public String enviarReview(@ModelAttribute ReviewCrearDTO reviewDTO,
                               RedirectAttributes redirectAttributes) {
        Long usuarioId = sessionManager.getIdUsuarioLogeado();

        if (usuarioId == null) {
            return "redirect:/login";
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
    public String verMisReviews(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "6") int size,
                                Model model) {
        Long usuarioId = sessionManager.getIdUsuarioLogeado();

        if (usuarioId == null) {
            return "redirect:/login";
        }

        Page<ReviewDTO> reviewsPage = reviewService.obtenerReviewsPaginadas(usuarioId, page, size);

        model.addAttribute("misReviews", reviewsPage.getContent());
        model.addAttribute("paginaActual", page);
        model.addAttribute("totalPaginas", reviewsPage.getTotalPages());
        model.addAttribute("size", size);  // <-- agregar tamaño al modelo

        return "mis-reviews";
    }




}
