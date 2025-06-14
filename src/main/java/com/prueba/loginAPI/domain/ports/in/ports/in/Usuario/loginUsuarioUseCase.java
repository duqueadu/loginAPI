package com.prueba.loginAPI.domain.ports.in.ports.in.Usuario;

public interface loginUsuarioUseCase {
    String login(String email, String password) throws Exception;
}
