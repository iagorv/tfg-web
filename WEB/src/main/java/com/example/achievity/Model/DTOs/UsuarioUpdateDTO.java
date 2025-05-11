package com.example.achievity.Model.DTOs;


import java.time.LocalDate;

public class UsuarioUpdateDTO {
    private String nombre;
    private LocalDate fechaNacimiento;

    public UsuarioUpdateDTO(String nombre, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
}

