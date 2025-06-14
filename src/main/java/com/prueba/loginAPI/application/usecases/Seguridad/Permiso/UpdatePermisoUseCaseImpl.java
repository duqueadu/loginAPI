package com.prueba.loginAPI.application.usecases.Seguridad.Permiso;

import com.prueba.loginAPI.domain.model.Seguridad.Permiso;
import com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.Permiso.UpdatePermisoUseCase;

import com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.PermisosRepositoryPort;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.Permisos;

import java.util.Optional;

public class UpdatePermisoUseCaseImpl implements UpdatePermisoUseCase {

    private final PermisosRepositoryPort PermisoRepositoryPort;

    public UpdatePermisoUseCaseImpl(PermisosRepositoryPort PermisoRepositoryPort) {
        this.PermisoRepositoryPort = PermisoRepositoryPort;
    }

    @Override
    public Optional<Permiso> updatePermiso(Long id, Permisos updatePermiso) {
        return PermisoRepositoryPort.update(updatePermiso);
    }
}
