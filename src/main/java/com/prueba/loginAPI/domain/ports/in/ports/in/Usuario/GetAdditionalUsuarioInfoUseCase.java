package com.prueba.loginAPI.domain.ports.in.ports.in.Usuario;

import com.prueba.loginAPI.domain.model.Usuario.AdditionalUsuarioInfo;

public interface GetAdditionalUsuarioInfoUseCase {
    AdditionalUsuarioInfo getAdditionalUsuarioInfo(Long id);

}
