package com.prueba.loginAPI.domain.model.Usuario;


import java.util.Date;

public class AdditionalUsuarioInfo {

        private final Long id;
        private final String nombre;
        private final String email;
        private final String contrasena;
        private final Long rol;
        private final Date fechaCreacion;

        public AdditionalUsuarioInfo(Long id, String nombre, String email, String contrasena, Long rol, Date fechaCreacion) {
            this.id = id;
            this.nombre = nombre;
            this.email = email;
            this.contrasena = contrasena;
            this.rol = rol;
            this.fechaCreacion = fechaCreacion;
        }

        public Long getId() {
            return id;
        }
        public String getNombre() {
        return nombre;
       }
        public String getEmail() {
            return email;
        }
        public String getContrasena() {
        return contrasena;
    }
        public Long getRol() {
            return rol;
        }
        public Date getFechaCreacion() {
        return fechaCreacion;
    }
    }

