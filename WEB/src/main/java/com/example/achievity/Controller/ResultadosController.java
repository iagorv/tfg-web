package com.example.achievity.Controller;

import com.example.achievity.Model.DTOs.BusquedaResponseDTO;
import com.example.achievity.Service.BusquedaService;
import com.example.achievity.Service.JuegoApiService;
import com.example.achievity.Service.ReviewService;
import com.example.achievity.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultadosController {

    private final BusquedaService busquedaService;

    public ResultadosController(BusquedaService busquedaService) {
        this.busquedaService = busquedaService;
    }

    @GetMapping("/resultados")
    public String mostrarResultados(@RequestParam("query") String query, Model model) {
        BusquedaResponseDTO resultados = busquedaService.buscar(query);

        model.addAttribute("query", query);
        model.addAttribute("juegos", resultados.getJuegos());
        model.addAttribute("usuarios", resultados.getUsuarios());
        model.addAttribute("reviews", resultados.getReviews());

        return "resultados"; // Thymeleaf: src/main/resources/templates/resultados.html
    }
}


