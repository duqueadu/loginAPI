package com.prueba.loginAPI.infrastructure.entities.Seguridad;


import com.prueba.loginAPI.domain.model.Seguridad.RolPermiso;
import jakarta.persistence.*;


@Entity
 @Table(name = "public.RolPermisos")

 public class RolPermisos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Roles rol;

    @ManyToOne
    @JoinColumn(name = "permiso_id", nullable = false)
    private Permisos permiso;

    // Getters y Setters


    public RolPermisos() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters y Setters


    public RolPermisos(Long id, Roles rol, Permisos permiso) {
        this.id = id;
        this.rol = rol;
        this.permiso = permiso;
    }



    public static RolPermisos fromDomainModel(RolPermiso rolPermiso) {
        return new RolPermisos(rolPermiso.getId(), rolPermiso.getRol(), rolPermiso.getPermiso());
    }

    public RolPermiso toDomainModel() {
        return new RolPermiso(id, rol, permiso);
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public Permisos getPermiso() {
        return permiso;
    }

    public void setPermiso(Permisos permiso) {
        this.permiso = permiso;
    }
}