package com.example.achievity.Controller;

import com.example.achievity.Authentication.SessionManager;
import com.example.achievity.Service.JuegoUsuarioEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class JuegoUsuarioEstadoController {

    @Autowired
    private JuegoUsuarioEstadoService estadoJuegoService;

    @Autowired
    private SessionManager sessionManager;

    @PostMapping("/estado-juego/cambiar")
    public String cambiarEstado(@RequestParam Long juegoId,
                                @RequestParam String estado) {
        Long usuarioId = sessionManager.getIdUsuarioLogeado();

        if (usuarioId == null) {
            return "redirect:/index"; // Por si acaso
        }

        estadoJuegoService.guardarEstadoJuego(juegoId, usuarioId, estado);

        return "redirect:/juegos/" + juegoId;
    }
}
