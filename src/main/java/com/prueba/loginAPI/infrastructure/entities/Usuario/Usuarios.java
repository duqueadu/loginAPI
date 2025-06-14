package com.prueba.loginAPI.infrastructure.entities.Usuario;

import com.prueba.loginAPI.domain.model.Usuario.Usuario;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "public.usuarios")
public class Usuarios {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nombre;

        private String email;
        private String contrasena;

        @JoinColumn(name = "rol_id")
        private Long rol;

        @Temporal(TemporalType.TIMESTAMP)
        private Date fechaCreacion;

        // Getters y Setters


    // Getters y Setters

    public Usuarios() {
    }

    public Usuarios(Long id, String nombre, String email, String contrasena, Long rol, Date fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
        this.fechaCreacion = fechaCreacion;
    }

    public static Usuarios fromDomainModel(Usuario usuario) {
        return new Usuarios(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getContrasena(), usuario.getRol(),usuario.getFechaCreacion() );
    }

    public Usuario toDomainModel() {
        return new Usuario(id, nombre, email, contrasena,rol,fechaCreacion);
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Long getRol() {
        return rol;
    }

    public void setRol(Long rol) {
        this.rol = rol;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
