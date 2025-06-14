package com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.Permiso;

import com.prueba.loginAPI.domain.model.Seguridad.Permiso;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.Permisos;

import java.util.Optional;

public interface UpdatePermisoUseCase {
    Optional<Permiso> updatePermiso(Long id, Permisos updatedPermiso);
}
