package com.prueba.loginAPI.infrastructure.repositories.Seguridad.Rol;



import com.prueba.loginAPI.domain.model.Seguridad.Rol;
import com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.RolesRepositoryPort;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.Roles;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Component
public class JpaRolRepositoryAdapter implements RolesRepositoryPort {

        private final JpaRolRepository jpaRolesRepository;

        public JpaRolRepositoryAdapter(JpaRolRepository jpaRolesRepository) {
            this.jpaRolesRepository = jpaRolesRepository;
        }

        @Override
        public Roles save(Roles Roles) {
            Roles RolesEntity = Roles;
            Roles savedRolesEntity = jpaRolesRepository.save(RolesEntity);
            return RolesEntity;
        }

    @Override
    public Rol save(Rol rol) {
        return null;
    }


    @Override
        public Optional<Rol> findById(Long id) {
            return jpaRolesRepository.findById(id).map(Roles::toDomainModel);
        }

    @Override
    public Optional<Rol> update(Roles rol) {
        return Optional.empty();
    }

    @Override
        public List<Rol> findAll() {
            return jpaRolesRepository.findAll().stream()
                    .map(Roles::toDomainModel)
                    .collect(Collectors.toList());
        }

        @Override
        public Optional<Rol> update(Rol roles) {
            if (jpaRolesRepository.existsById(roles.getId())) {
                Roles rolEntity = Roles.fromDomainModel(roles);
                Roles updatedRolesEntity = jpaRolesRepository.save(rolEntity);
                return Optional.of(updatedRolesEntity.toDomainModel());
            }
            return Optional.empty();
        }


        @Override
        public boolean deleteById(Long id) {
            if (jpaRolesRepository.existsById(id)) {
                jpaRolesRepository.deleteById(id);
                return true;
            }
            return false;
        }
    }
