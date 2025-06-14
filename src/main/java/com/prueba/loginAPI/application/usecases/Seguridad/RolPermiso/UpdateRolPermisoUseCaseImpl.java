package com.prueba.loginAPI.application.usecases.Seguridad.RolPermiso;

import com.prueba.loginAPI.domain.model.Seguridad.RolPermiso;
import com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.RolPermiso.UpdateRolPermisoUseCase;
import com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.RolPermisoRepositoryPort;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.RolPermisos;

import java.util.Optional;

public class UpdateRolPermisoUseCaseImpl implements UpdateRolPermisoUseCase {

    private final RolPermisoRepositoryPort rolPermisoRepositoryPort;

    public UpdateRolPermisoUseCaseImpl(RolPermisoRepositoryPort rolPermisoRepositoryPort) {
        this.rolPermisoRepositoryPort = rolPermisoRepositoryPort;
    }

    @Override
    public Optional<RolPermiso> updateRolPermiso(Long id, RolPermisos updateRolPermiso) {
        return rolPermisoRepositoryPort.update(updateRolPermiso);
    }
}
