package com.example.achievity.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;


@Component
public class SessionManager {

    @Autowired
    private final HttpSession session;

    public SessionManager(HttpSession session) {
        this.session = session;
    }


    public void login(Long idUsuario, String nombreUsuario) {
        session.setAttribute("idUsuarioLogeado", idUsuario);
        session.setAttribute("nombreUsuario", nombreUsuario);
    }


    public Long getIdUsuarioLogeado() {
        return (Long) session.getAttribute("idUsuarioLogeado");
    }


    public String getNombreUsuario() {
        return (String) session.getAttribute("nombreUsuario");
    }

    public void logout() {
        session.invalidate();
    }

    public boolean estaLogeado() {
        return session.getAttribute("idUsuarioLogeado") != null;
    }
}

