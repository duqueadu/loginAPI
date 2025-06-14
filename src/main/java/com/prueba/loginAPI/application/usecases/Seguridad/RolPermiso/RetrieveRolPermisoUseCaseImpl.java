package com.prueba.loginAPI.application.usecases.Seguridad.RolPermiso;

import com.prueba.loginAPI.domain.model.Seguridad.RolPermiso;
import com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.RolPermiso.RetrieveRolPermisoUseCase;
import com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.RolPermisoRepositoryPort;

import java.util.List;
import java.util.Optional;

public class RetrieveRolPermisoUseCaseImpl implements RetrieveRolPermisoUseCase {

    private final RolPermisoRepositoryPort  rolPermisoRepositoryPort;

    public RetrieveRolPermisoUseCaseImpl(RolPermisoRepositoryPort rolPermisoRepositoryPort) {
        this.rolPermisoRepositoryPort = rolPermisoRepositoryPort;
    }



    @Override
    public Optional<RolPermiso> getRolPermisoById(Long id) {
        return rolPermisoRepositoryPort.findById(id);
    }

    @Override
    public List<RolPermiso> getAllRolPermiso() {
        return rolPermisoRepositoryPort.findAll();
    }
}
