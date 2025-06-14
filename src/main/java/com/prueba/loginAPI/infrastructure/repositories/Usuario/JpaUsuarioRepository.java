package com.prueba.loginAPI.infrastructure.repositories.Usuario;

import com.prueba.loginAPI.infrastructure.entities.Usuario.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUsuarioRepository extends JpaRepository<Usuarios, Long> {
    boolean existsByEmail(String email);

    Usuarios findByEmail(String email);
    // Puedes agregar consultas personalizadas si lo necesitas
}
