package com.academy.models;

public class Usuario {
    private Integer idUsuario;
    private String numeroIdentificacion;
    private String nombre;
    private String correo;

    // Constructor vacío necesario para deserialización JSON
    public Usuario() {
    }

    // Constructor con campos requeridos
    public Usuario(String numeroIdentificacion, String nombre, String correo) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters y Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}