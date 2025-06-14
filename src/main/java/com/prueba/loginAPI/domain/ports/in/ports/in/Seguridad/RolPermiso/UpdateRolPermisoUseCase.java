package com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.RolPermiso;

import com.prueba.loginAPI.domain.model.Seguridad.RolPermiso;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.RolPermisos;

import java.util.Optional;

public interface UpdateRolPermisoUseCase {
    Optional<RolPermiso> updateRolPermiso(Long id, RolPermisos updatedRolPermiso);
}
