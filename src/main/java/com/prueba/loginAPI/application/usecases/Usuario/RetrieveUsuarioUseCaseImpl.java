package com.prueba.loginAPI.application.usecases.Usuario;

import com.prueba.loginAPI.domain.model.Usuario.Usuario;
import com.prueba.loginAPI.domain.ports.in.ports.in.Usuario.RetrieveUsuarioUseCase;
import com.prueba.loginAPI.domain.ports.in.ports.out.Usuario.UsuarioRepositoryPort;

import java.util.List;
import java.util.Optional;

public class RetrieveUsuarioUseCaseImpl implements RetrieveUsuarioUseCase {

    private final UsuarioRepositoryPort  UsuarioRepositoryPort;

    public RetrieveUsuarioUseCaseImpl(UsuarioRepositoryPort UsuarioRepositoryPort) {
        this.UsuarioRepositoryPort = UsuarioRepositoryPort;
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return UsuarioRepositoryPort.findById(id);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return UsuarioRepositoryPort.findAll();
    }
}
