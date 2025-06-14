package com.prueba.loginAPI.infrastructure.repositories.Usuario;

import com.prueba.loginAPI.domain.model.Usuario.Usuario;
import com.prueba.loginAPI.domain.ports.in.ports.out.Usuario.UsuarioRepositoryPort;
import com.prueba.loginAPI.infrastructure.config.TokenGenerator;
import com.prueba.loginAPI.infrastructure.entities.Usuario.Usuarios;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaUsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final JpaUsuarioRepository usuarioRepository;

    public JpaUsuarioRepositoryAdapter(JpaUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuarios save(Usuarios usuario) {
        return null;
    }

    @Override
    public Usuario save(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return  null;
        }
        Usuarios taskEntity = Usuarios.fromDomainModel(usuario);
        Usuarios savedTaskEntity = usuarioRepository.save(taskEntity);
        return savedTaskEntity.toDomainModel();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id).map(Usuarios::toDomainModel);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll().stream()
                .map(Usuarios::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> update(Usuarios usuario) {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> update(Usuario usuario) {
        if (usuarioRepository.existsById(usuario.getId())) {
            Usuarios taskEntity = Usuarios.fromDomainModel(usuario);
            Usuarios updatedTaskEntity = usuarioRepository.save(taskEntity);
            return Optional.of(updatedTaskEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public String login(String email, String password) throws Exception {
        Usuarios usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }

        // Verificar si la contraseña coincide
        if (!usuario.getContrasena().equals(password)) {
            throw new Exception("Credenciales inválidas");
        }

        return TokenGenerator.generateToken(usuario.getEmail(),usuario.getId(),usuario.getRol());
    }







}
