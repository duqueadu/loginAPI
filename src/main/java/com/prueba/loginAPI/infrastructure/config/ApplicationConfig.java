package com.prueba.loginAPI.infrastructure.config;


import com.prueba.loginAPI.application.services.Seguridad.PermisosService;
import com.prueba.loginAPI.application.services.Seguridad.RolPermisoService;
import com.prueba.loginAPI.application.services.Seguridad.RolesService;
import com.prueba.loginAPI.application.services.UsuarioService;
import com.prueba.loginAPI.application.usecases.Seguridad.Permiso.CreatePermisosUseCaseImpl;
import com.prueba.loginAPI.application.usecases.Seguridad.Permiso.DeletePermisoUseCaseImpl;
import com.prueba.loginAPI.application.usecases.Seguridad.Permiso.RetrievePermisoUseCaseImpl;
import com.prueba.loginAPI.application.usecases.Seguridad.Permiso.UpdatePermisoUseCaseImpl;
import com.prueba.loginAPI.application.usecases.Seguridad.Rol.CreateRolesUseCaseImpl;
import com.prueba.loginAPI.application.usecases.Seguridad.Rol.DeleteRolUseCaseImpl;
import com.prueba.loginAPI.application.usecases.Seguridad.Rol.RetrieveRolUseCaseImpl;
import com.prueba.loginAPI.application.usecases.Seguridad.Rol.UpdateRolUseCaseImpl;
import com.prueba.loginAPI.application.usecases.Seguridad.RolPermiso.CreateRolPermisosUseCaseImpl;
import com.prueba.loginAPI.application.usecases.Seguridad.RolPermiso.DeleteRolPermisoUseCaseImpl;
import com.prueba.loginAPI.application.usecases.Seguridad.RolPermiso.RetrieveRolPermisoUseCaseImpl;
import com.prueba.loginAPI.application.usecases.Seguridad.RolPermiso.UpdateRolPermisoUseCaseImpl;
import com.prueba.loginAPI.application.usecases.Usuario.*;
import com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.PermisosRepositoryPort;
import com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.RolPermisoRepositoryPort;
import com.prueba.loginAPI.domain.ports.in.ports.out.Seguridad.RolesRepositoryPort;
import com.prueba.loginAPI.domain.ports.in.ports.out.Usuario.UsuarioRepositoryPort;
import com.prueba.loginAPI.infrastructure.repositories.Seguridad.Permiso.JpaPermisoRepositoryAdapter;
import com.prueba.loginAPI.infrastructure.repositories.Seguridad.Rol.JpaRolRepositoryAdapter;
import com.prueba.loginAPI.infrastructure.repositories.Seguridad.RolPermiso.JpaRolPermisoRepositoryAdapter;
import com.prueba.loginAPI.infrastructure.repositories.Usuario.JpaUsuarioRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity


public class ApplicationConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/authorization/login",
                                "/api/authorization/logout",
                                "api/authorization/CrearUsuario",
                                "/api/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/configuration/**"
                        ).permitAll()
                        .requestMatchers("/api/**").authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins(
                                "http://localhost:8080" // Producción (ajusta según tu frontend)
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600); // Cacheo de preflight por 1 hora
            }
        };
    }

    @Bean
    public UsuarioService UsuarioService(UsuarioRepositoryPort usuarioRepositoryPort) {
        return new UsuarioService(
                new CreateUsuarioUseCaseImpl(usuarioRepositoryPort),
                new RetrieveUsuarioUseCaseImpl(usuarioRepositoryPort),
                new UpdateUsuarioUseCaseImpl(usuarioRepositoryPort),
                new DeleteUsuarioUseCaseImpl(usuarioRepositoryPort),
                new LoginUsuarioUseCaseImpl(usuarioRepositoryPort)

        );
    }

    @Bean
    public UsuarioRepositoryPort usuarioRepositoryPort(JpaUsuarioRepositoryAdapter jpaUsuarioRepositoryAdapter) {
        return jpaUsuarioRepositoryAdapter;
    }

    // ROLES
    @Bean
    public RolesService RolesService(RolesRepositoryPort RepositoryPort) {
        return new RolesService(
                new CreateRolesUseCaseImpl(RepositoryPort),
                new RetrieveRolUseCaseImpl(RepositoryPort),
                new UpdateRolUseCaseImpl(RepositoryPort),
                new DeleteRolUseCaseImpl(RepositoryPort)
        );
    }

    @Bean
    public RolesRepositoryPort rolesRepositoryPort(JpaRolRepositoryAdapter jpaRolRepositoryAdapter) {
        return jpaRolRepositoryAdapter;
    }

    //PERMISO
    @Bean
    public PermisosService PermisosService(PermisosRepositoryPort permisosRepositoryPort) {
        return new PermisosService(
                new CreatePermisosUseCaseImpl(permisosRepositoryPort),
                new RetrievePermisoUseCaseImpl(permisosRepositoryPort),
                new UpdatePermisoUseCaseImpl(permisosRepositoryPort),
                new DeletePermisoUseCaseImpl(permisosRepositoryPort)
        );
    }


    @Bean
    public PermisosRepositoryPort PermisosRepositoryPort(JpaPermisoRepositoryAdapter jpaPermisoRepositoryAdapter) {
        return jpaPermisoRepositoryAdapter;
    }

    //ROL PERMISO
    @Bean
    public RolPermisoService RolPermisoService(RolPermisoRepositoryPort rolPermisoRepositoryPort) {
        return new RolPermisoService(
                new CreateRolPermisosUseCaseImpl(rolPermisoRepositoryPort),
                new RetrieveRolPermisoUseCaseImpl(rolPermisoRepositoryPort),
                new UpdateRolPermisoUseCaseImpl(rolPermisoRepositoryPort),
                new DeleteRolPermisoUseCaseImpl(rolPermisoRepositoryPort)
        );
    }

    @Bean
    public RolPermisoRepositoryPort RolPermisoRepositoryPort(JpaRolPermisoRepositoryAdapter jpaRolPermisoRepositoryAdapter) {
        return jpaRolPermisoRepositoryAdapter;
    }




}
