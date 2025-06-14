package com.prueba.loginAPI.domain.ports.in.ports.in.Seguridad.Rol;

import com.prueba.loginAPI.domain.model.Usuario.AdditionalUsuarioInfo;

public interface GetAdditionalUsuarioInfoUseCase {
    AdditionalUsuarioInfo getAdditionalUsuarioInfo(Long id);

}
