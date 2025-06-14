package com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.Rol;

import com.prueba.loginAPI.domain.model.Seguridad.Rol;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.Roles;

import java.util.Optional;

public interface UpdateRolUseCase {
    Optional<Rol> updateRol(Long id, Roles updatedRol);
}
