package com.prueba.loginAPI.application.services;

import com.prueba.loginAPI.domain.model.Usuario.Usuario;
import com.prueba.loginAPI.domain.ports.in.ports.in.Usuario.*;
import java.util.List;
import java.util.Optional;

public class UsuarioService implements CreateUsuarioUseCase, RetrieveUsuarioUseCase, UpdateUsuarioUseCase, DeleteUsuarioUseCase, com.prueba.loginAPI.domain.ports.in.ports.in.Usuario.loginUsuarioUseCase {

    private final CreateUsuarioUseCase createUsuarioUseCase;
    private final RetrieveUsuarioUseCase retrieveUsuarioUseCase;
    private final UpdateUsuarioUseCase updateUsuarioUseCase;
    private final DeleteUsuarioUseCase deleteUsuarioUseCase;

    private final loginUsuarioUseCase loginUsuarioUseCase;

    public UsuarioService(CreateUsuarioUseCase createUsuarioUseCase, RetrieveUsuarioUseCase retrieveUsuarioUseCase,
                       UpdateUsuarioUseCase updateUsuarioUseCase, DeleteUsuarioUseCase deleteUsuarioUseCase,loginUsuarioUseCase loginUsuarioUseCase
                        ) {
        this.createUsuarioUseCase = createUsuarioUseCase;
        this.loginUsuarioUseCase = loginUsuarioUseCase;
        this.retrieveUsuarioUseCase = retrieveUsuarioUseCase;
        this.updateUsuarioUseCase = updateUsuarioUseCase;
        this.deleteUsuarioUseCase = deleteUsuarioUseCase;
    }




    @Override
    public Usuario createUsuario(Usuario Usuario) {
        return createUsuarioUseCase.createUsuario(Usuario);
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return retrieveUsuarioUseCase.getUsuarioById(id);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return retrieveUsuarioUseCase.getAllUsuarios();
    }

    @Override
    public Optional<Usuario> updateUsuario(Long id, Usuario updatedUsuario) {
        return updateUsuarioUseCase.updateUsuario(id, updatedUsuario);
    }









    @Override
    public boolean deleteUsuario(Long id) {
        return deleteUsuarioUseCase.deleteUsuario(id);
    }

    @Override
    public String login(String email, String password) throws Exception{

        return loginUsuarioUseCase.login(email,password);
    }

}