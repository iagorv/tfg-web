package com.example.achievity.Controller;

import com.example.achievity.Authentication.SessionManager;
import com.example.achievity.Service.JuegoApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {


    private final SessionManager sessionManager;

    public UsuarioController( SessionManager sessionManager) {

        this.sessionManager = sessionManager;
    }



    @GetMapping("/perfil")
    public String mostrarIndex(Model model) {
        if (!sessionManager.estaLogeado()) {
            return "redirect:/login";
        }

        return "perfil";
    }
}
