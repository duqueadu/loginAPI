package com.prueba.loginAPI.domain.ports.in.ports.in.Usuario;


import com.prueba.loginAPI.domain.model.Usuario.Usuario;

public interface CreateUsuarioUseCase {
    Usuario createUsuario(Usuario task);

}