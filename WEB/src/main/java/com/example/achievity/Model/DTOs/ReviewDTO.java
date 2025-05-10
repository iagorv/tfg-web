package com.example.achievity.Model.DTOs;

import java.time.Instant;

public class ReviewDTO {
    private Long id;
    private String tituloJuego;
    private String reseña;
    private Integer nota;
    private Long juegoId;
    private Instant fechaReview;

    public ReviewDTO(Long id, String tituloJuego, String reseña, Integer nota, Long juegoId, Instant fechaReview) {
        this.id = id;
        this.tituloJuego = tituloJuego;
        this.reseña = reseña;
        this.nota = nota;
        this.juegoId = juegoId;
        this.fechaReview = fechaReview;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloJuego() {
        return tituloJuego;
    }

    public void setTituloJuego(String tituloJuego) {
        this.tituloJuego = tituloJuego;
    }

    public String getReseña() {
        return reseña;
    }

    public void setReseña(String reseña) {
        this.reseña = reseña;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Long getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(Long juegoId) {
        this.juegoId = juegoId;
    }

    public Instant getFechaReview() {
        return fechaReview;
    }

    public void setFechaReview(Instant fechaReview) {
        this.fechaReview = fechaReview;
    }
}

