package com.prueba.loginAPI.infrastructure.entities.Seguridad;

import com.prueba.loginAPI.domain.model.Seguridad.Rol;
import jakarta.persistence.*;


@Entity
@Table(name = "public.Roles")

public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    public Roles() {
    }

    public Roles(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public static Roles fromDomainModel(Rol rol) {
        return new Roles(rol.getId(), rol.getNombre(), rol.getDescripcion());
    }

    public Rol toDomainModel() {
        return new Rol(id, nombre, descripcion);
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
