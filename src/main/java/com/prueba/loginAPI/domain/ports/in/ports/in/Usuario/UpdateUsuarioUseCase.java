package com.prueba.loginAPI.domain.ports.in.ports.in.Usuario;

import com.prueba.loginAPI.domain.model.Usuario.Usuario;

import java.util.Optional;

public interface UpdateUsuarioUseCase {
    Optional<Usuario> updateUsuario(Long id, Usuario updatedUsuario);
}
