package com.prueba.loginAPI.infrastructure.entities.Seguridad;

import com.prueba.loginAPI.domain.model.Seguridad.Permiso;
import jakarta.persistence.*;


@Entity
@Table(name = "public.Permisos")
public class Permisos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    public Permisos() {
    }

    public Permisos(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public static Permisos fromDomainModel(Permiso permiso) {
        return new Permisos(permiso.getId(), permiso.getNombre(), permiso.getDescripcion());
    }

    public Permiso toDomainModel() {
        return new Permiso(id, nombre, descripcion);
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
