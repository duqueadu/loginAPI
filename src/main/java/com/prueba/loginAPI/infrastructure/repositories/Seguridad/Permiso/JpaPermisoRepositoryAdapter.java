package com.prueba.loginAPI.infrastructure.repositories.Seguridad.Permiso;



import com.prueba.loginAPI.domain.model.Seguridad.Permiso;

import com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.PermisosRepositoryPort;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.Permisos;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Component
public class JpaPermisoRepositoryAdapter implements PermisosRepositoryPort {

        private final JpaPermisoRepository jpaPermisosRepository;

        public JpaPermisoRepositoryAdapter(JpaPermisoRepository jpaPermisosRepository) {
            this.jpaPermisosRepository = jpaPermisosRepository;
        }

        @Override
        public Permisos save(Permisos Permisos) {
            Permisos PermisosEntity = Permisos;
            Permisos savedPermisosEntity = jpaPermisosRepository.save(PermisosEntity);
            return PermisosEntity;
        }

    @Override
    public Permiso save(Permiso Permiso) {
        return null;
    }


    @Override
        public Optional<Permiso> findById(Long id) {
            return jpaPermisosRepository.findById(id).map(Permisos::toDomainModel);
        }

    @Override
    public Optional<Permiso> update(Permisos Permiso) {
        return Optional.empty();
    }

    @Override
        public List<Permiso> findAll() {
            return jpaPermisosRepository.findAll().stream()
                    .map(Permisos::toDomainModel)
                    .collect(Collectors.toList());
        }

        @Override
        public Optional<Permiso> update(Permiso permisos) {
            if (jpaPermisosRepository.existsById(permisos.getId())) {
                Permisos permisosEntity = Permisos.fromDomainModel(permisos);
                Permisos updatedPermsoEntity = jpaPermisosRepository.save(permisosEntity);
                return Optional.of(updatedPermsoEntity.toDomainModel());
            }
            return Optional.empty();
        }


        @Override
        public boolean deleteById(Long id) {
            if (jpaPermisosRepository.existsById(id)) {
                jpaPermisosRepository.deleteById(id);
                return true;
            }
            return false;
        }
    }
