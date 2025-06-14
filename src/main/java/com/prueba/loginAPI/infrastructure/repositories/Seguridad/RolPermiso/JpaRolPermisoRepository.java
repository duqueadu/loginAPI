package com.prueba.loginAPI.infrastructure.repositories.Seguridad.RolPermiso;

import com.prueba.loginAPI.infrastructure.entities.Seguridad.RolPermisos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRolPermisoRepository extends JpaRepository<RolPermisos, Long> {
    // Puedes agregar consultas personalizadas si lo necesitas
}
