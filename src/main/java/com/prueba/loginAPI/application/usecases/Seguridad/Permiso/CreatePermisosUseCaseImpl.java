package com.prueba.loginAPI.application.usecases.Seguridad.Permiso;


import com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.Permiso.CreatePermisoUseCase;
import com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.PermisosRepositoryPort;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.Permisos;

public class CreatePermisosUseCaseImpl implements CreatePermisoUseCase {

    private final com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.PermisosRepositoryPort PermisosRepositoryPort;

    public CreatePermisosUseCaseImpl(PermisosRepositoryPort PermisosRepositoryPort) {
        this.PermisosRepositoryPort = PermisosRepositoryPort;
    }

    @Override
    public Permisos createPermiso(Permisos rol) {
        return PermisosRepositoryPort.save(rol);
    }
}