package com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.RolPermiso;

import com.prueba.loginAPI.domain.model.Seguridad.RolPermiso;

import java.util.List;
import java.util.Optional;

public interface RetrieveRolPermisoUseCase {
    Optional<RolPermiso> getRolPermisoById(Long id);
    List<RolPermiso> getAllRolPermiso();
}
