package com.example.achievity.Controller;


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

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO, Model model) {
        UsuarioDTO usuario = usuarioService.login(loginDTO);

        if (usuario != null) {
            return "redirect:/juegos";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }
}