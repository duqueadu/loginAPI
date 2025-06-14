package com.prueba.loginAPI.application.usecases.Usuario;

import com.prueba.loginAPI.domain.ports.in.ports.in.Usuario.loginUsuarioUseCase;
import com.prueba.loginAPI.domain.ports.in.ports.out.Usuario.UsuarioRepositoryPort;

public class LoginUsuarioUseCaseImpl implements loginUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public LoginUsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public String login(String email, String password) throws Exception{
        return this.usuarioRepositoryPort.login(email,password);
    }
}
