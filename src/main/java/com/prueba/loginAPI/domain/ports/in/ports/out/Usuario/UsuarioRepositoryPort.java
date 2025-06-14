package com.prueba.loginAPI.domain.ports.in.ports.out.Usuario;



import com.prueba.loginAPI.domain.model.Usuario.Usuario;
import com.prueba.loginAPI.infrastructure.entities.Usuario.Usuarios;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuarios save(Usuarios usuario);

    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    List<Usuario> findAll();

    Optional<Usuario> update(Usuarios usuario);

    Optional<Usuario> update(Usuario usuario);
    boolean deleteById(Long id);

     String login(String email, String password) throws Exception;
}
