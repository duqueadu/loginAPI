package com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.Permiso;


import com.prueba.loginAPI.infrastructure.entities.Seguridad.Permisos;


public interface CreatePermisoUseCase {
    Permisos createPermiso(Permisos permiso);
}