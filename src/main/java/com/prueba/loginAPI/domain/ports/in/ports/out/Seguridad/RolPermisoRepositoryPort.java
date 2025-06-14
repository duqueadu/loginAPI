package com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad;

import com.prueba.loginAPI.domain.model.Seguridad.RolPermiso;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.RolPermisos;

import java.util.List;
import java.util.Optional;

public interface RolPermisoRepositoryPort {
    RolPermisos save(RolPermisos rolPermisos);

    RolPermiso save(RolPermiso rolPermisos);
    Optional<RolPermiso> findById(Long id);

    Optional<RolPermiso> update(RolPermisos rolPermisos);

    List<RolPermiso> findAll();

    Optional<RolPermiso> update(RolPermiso RolPermisos);

    boolean deleteById(Long id);
}
