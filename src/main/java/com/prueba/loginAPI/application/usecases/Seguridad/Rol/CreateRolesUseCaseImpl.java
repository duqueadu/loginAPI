package com.prueba.loginAPI.application.usecases.Seguridad.Rol;


import com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.Rol.CreateRolesUseCase;
import com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.RolesRepositoryPort;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.Roles;

public class CreateRolesUseCaseImpl implements CreateRolesUseCase {

    private final com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.RolesRepositoryPort RolesRepositoryPort;

    public CreateRolesUseCaseImpl(RolesRepositoryPort RolesRepositoryPort) {
        this.RolesRepositoryPort = RolesRepositoryPort;
    }

    @Override
    public Roles createRol(Roles rol) {
        return RolesRepositoryPort.save(rol);
    }
}