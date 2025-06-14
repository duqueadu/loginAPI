package com.prueba.loginAPI.application.usecases.Seguridad.Permiso;


import com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.Permiso.DeletePermisoUseCase;
import com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.PermisosRepositoryPort;

public class DeletePermisoUseCaseImpl implements DeletePermisoUseCase {

    private final com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.PermisosRepositoryPort PermisosRepositoryPort;

    public DeletePermisoUseCaseImpl(PermisosRepositoryPort PermisosRepositoryPort) {
        this.PermisosRepositoryPort = PermisosRepositoryPort;
    }

    @Override
    public boolean deletePermiso(Long id) {
        return PermisosRepositoryPort.deleteById(id);
    }
}
