package com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.Permiso;

import com.prueba.loginAPI.domain.model.Seguridad.Permiso;

import java.util.List;
import java.util.Optional;

public interface RetrievePermisoUseCase {
    Optional<Permiso> getPermisoById(Long id);
    List<Permiso> getAllPermiso();
}
