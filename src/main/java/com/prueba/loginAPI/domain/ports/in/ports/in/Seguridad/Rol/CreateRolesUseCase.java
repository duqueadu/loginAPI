package com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.Rol;


import com.prueba.loginAPI.infrastructure.entities.Seguridad.Roles;

public interface CreateRolesUseCase {
    Roles createRol(Roles rol);
}