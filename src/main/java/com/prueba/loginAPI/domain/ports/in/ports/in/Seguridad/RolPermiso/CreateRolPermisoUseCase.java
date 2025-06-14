package com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.RolPermiso;


import com.prueba.loginAPI.infrastructure.entities.Seguridad.RolPermisos;


public interface CreateRolPermisoUseCase {
        RolPermisos  createRolPermiso(RolPermisos rolPermisos);
}