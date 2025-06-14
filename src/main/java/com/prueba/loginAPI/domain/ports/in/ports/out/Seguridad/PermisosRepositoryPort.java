package com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad;

import com.prueba.loginAPI.domain.model.Seguridad.Permiso;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.Permisos;

import java.util.List;
import java.util.Optional;

public interface PermisosRepositoryPort {
    Permisos save(Permisos permiso);

    Permiso save(Permiso permiso);
    Optional<Permiso> findById(Long id);

    Optional<Permiso> update(Permisos permiso);

    
    List<Permiso> findAll();

    Optional<Permiso> update(Permiso permisos);



    boolean deleteById(Long id);
}
