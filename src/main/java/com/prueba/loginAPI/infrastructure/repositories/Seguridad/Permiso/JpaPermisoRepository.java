package com.prueba.loginAPI.infrastructure.repositories.Seguridad.Permiso;

import com.prueba.loginAPI.infrastructure.entities.Seguridad.Permisos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPermisoRepository extends JpaRepository<Permisos, Long> {
    // Puedes agregar consultas personalizadas si lo necesitas
}
