package com.example.achievity.Controller;


import com.example.achievity.Authentication.SessionManager;
import com.example.achievity.Model.DTOs.LoginDTO;
import com.example.achievity.Model.DTOs.UsuarioDTO;
import com.example.achievity.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    private final UsuarioService usuarioService;
    private final SessionManager sessionManager;


    public LoginController(UsuarioService usuarioService, SessionManager sessionManager) {
        this.usuarioService = usuarioService;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO, Model model) {
        UsuarioDTO usuario = usuarioService.login(loginDTO);

        if (usuario != null) {
            sessionManager.login(usuario.getId(), usuario.getNombre(), usuario.isEsPremium());

            return "redirect:/index";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout() {
        sessionManager.logout();
        return "redirect:/login";
    }
}