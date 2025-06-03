package com.example.achievity.Model.DTOs;

import java.util.List;

public class BusquedaResponseDTO {
    private List<JuegoResumenDTO> juegos;
    private List<UsuarioDTO> usuarios;
    private List<ReviewDTO> reviews;

    public List<JuegoResumenDTO> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<JuegoResumenDTO> juegos) {
        this.juegos = juegos;
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}
