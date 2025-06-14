package com.prueba.loginAPI.application.usecases.Usuario;

import com.prueba.loginAPI.domain.ports.in.ports.out.Usuario.UsuarioRepositoryPort;
import com.prueba.loginAPI.domain.ports.in.ports.in.Usuario.DeleteUsuarioUseCase;

public class DeleteUsuarioUseCaseImpl implements DeleteUsuarioUseCase {

    private final UsuarioRepositoryPort UsuarioRepositoryPort;

    public DeleteUsuarioUseCaseImpl(UsuarioRepositoryPort UsuarioRepositoryPort) {
        this.UsuarioRepositoryPort = UsuarioRepositoryPort;
    }

    @Override
    public boolean deleteUsuario(Long id) {
        return UsuarioRepositoryPort.deleteById(id);
    }
}
