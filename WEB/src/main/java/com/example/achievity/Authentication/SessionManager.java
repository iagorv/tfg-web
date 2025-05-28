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


    public void login(Long idUsuario, String nombreUsuario, boolean esPremium) {
        session.setAttribute("idUsuarioLogeado", idUsuario);
        session.setAttribute("nombreUsuario", nombreUsuario);
        session.setAttribute("esPremium", esPremium);
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
    public boolean isPremium() {
        Boolean premium = (Boolean) session.getAttribute("esPremium");
        return premium != null && premium;
    }
}

