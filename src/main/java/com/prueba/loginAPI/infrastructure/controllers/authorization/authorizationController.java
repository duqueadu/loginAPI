package com.prueba.loginAPI.infrastructure.controllers.authorization;

import com.prueba.loginAPI.application.services.UsuarioService;
import com.prueba.loginAPI.domain.model.Usuario.Usuario;
import com.prueba.loginAPI.domain.model.Usuario.loginUsuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/authorization")
public class authorizationController {

    private final UsuarioService UsuarioService;

    public authorizationController(UsuarioService UsuarioService) {

        this.UsuarioService = UsuarioService;
    }

    // Método privado para verificar la sesión
    private ResponseEntity<?> verifySession(HttpSession session) {
        String token = (String) session.getAttribute("access_token");
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado: No hay sesión activa");
        }
        return null; // Retorna null si la sesión es válida
    }

    @Operation(
            summary = "Iniciar sesión",
            description = "Endpoint público para autenticación basada en sesiones HTTP",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Autenticación exitosa"),
                    @ApiResponse(responseCode = "401", description = "Credenciales inválidas"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody loginUsuario loginRequest,    HttpSession session   ) {
        try {
            String token = UsuarioService.login(loginRequest.getEmail(), loginRequest.getContrasena());
            if (token != null) {
                Map<String, String> response = new HashMap<>();
                response.put("access_token", token);
                session.setAttribute("access_token", token); // Guarda el usuario en sesión

                return ResponseEntity.ok(response);
            } else {
                return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/check-session")
    public ResponseEntity<?> checkSession(HttpSession session) {
        ResponseEntity<?> verification = verifySession(session);
        if (verification != null) return verification;

        String token = (String) session.getAttribute("access_token");
        return ResponseEntity.ok("Sesión activa. Token: " + token);
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        // 1. Invalidar la sesión HTTP (elimina todos los atributos)
        session.invalidate();

        // 2. Limpiar cookies (opcional, útil para frontends web)
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0); // Expira inmediatamente
                cookie.setValue("");
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

        // 3. Limpiar cabeceras de autenticación (para APIs)
        response.setHeader("Authorization", "");

        return ResponseEntity.ok("Sesión cerrada y datos limpiados exitosamente");
    }





    @Operation(summary = "Registrar usuario", description = "Endpoint público")
    @PostMapping("/CrearUsuario")
    public ResponseEntity<?> usuarios(@RequestBody Usuario usuario) {
        try {
            // Intentar crear el usuario
            Usuario createdUsuario = UsuarioService.createUsuario(usuario);
            if(createdUsuario == null ){
                return new ResponseEntity<>(null, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("ok", HttpStatus.CREATED);


        } catch (Exception e) {
            // Manejo general de excepciones
            return new ResponseEntity<>("Error inesperado al crear el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}

