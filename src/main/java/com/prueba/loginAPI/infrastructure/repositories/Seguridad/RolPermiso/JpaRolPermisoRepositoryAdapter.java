package com.prueba.loginAPI.infrastructure.repositories.Seguridad.RolPermiso;



import com.prueba.loginAPI.domain.model.Seguridad.RolPermiso;
import com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.RolPermisoRepositoryPort;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.RolPermisos;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Component
public class JpaRolPermisoRepositoryAdapter implements RolPermisoRepositoryPort {

        private final JpaRolPermisoRepository  jpaRolPermisoRepository;

        public JpaRolPermisoRepositoryAdapter(JpaRolPermisoRepository jpaRolPermisoRepository) {
            this.jpaRolPermisoRepository = jpaRolPermisoRepository;
        }

        @Override
        public RolPermisos save(RolPermisos rolPermisos) {
            RolPermisos RolPermisoEntity = rolPermisos;
            RolPermisos savedRolPermisoEntity = jpaRolPermisoRepository.save(RolPermisoEntity);
            return RolPermisoEntity;
        }

        @Override
        public RolPermiso save(RolPermiso rolPermiso) {
            return null;
        }


       @Override
        public Optional<RolPermiso> findById(Long id) {
            return jpaRolPermisoRepository.findById(id).map(RolPermisos::toDomainModel);
        }

       @Override
        public Optional<RolPermiso> update(RolPermisos rolPermiso) {
            return Optional.empty();
        }

        @Override
        public List<RolPermiso> findAll() {
            return jpaRolPermisoRepository.findAll().stream()
                    .map(RolPermisos::toDomainModel)
                    .collect(Collectors.toList());
        }

        @Override
        public Optional<RolPermiso> update(RolPermiso RolPermisos) {
            return Optional.empty();
        }


        @Override
            public boolean deleteById(Long id) {
                if (jpaRolPermisoRepository.existsById(id)) {
                    jpaRolPermisoRepository.deleteById(id);
                    return true;
                }
                return false;
        }
    }
