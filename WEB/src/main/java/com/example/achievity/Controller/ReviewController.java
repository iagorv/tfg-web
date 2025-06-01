package com.example.achievity.Controller;

import com.example.achievity.Authentication.SessionManager;
import com.example.achievity.Model.DTOs.ReviewCrearDTO;
import com.example.achievity.Service.ReviewService;
import com.example.achievity.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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


}
