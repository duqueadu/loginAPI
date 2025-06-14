package com.prueba.loginAPI.infrastructure.controllers.Usuario;

import com.prueba.loginAPI.application.services.UsuarioService;
import com.prueba.loginAPI.domain.model.Usuario.Usuario;
import com.prueba.loginAPI.infrastructure.config.TokenGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Usuario")
@Validated
@Api(tags = "Gestión de Usuarios", description = "Operaciones relacionadas con la gestión de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final TokenGenerator tokenGenerator;

    public UsuarioController(UsuarioService usuarioService, TokenGenerator tokenGenerator) {
        this.usuarioService = usuarioService;
        this.tokenGenerator = tokenGenerator;
    }

    // Método para verificar la sesión
    private ResponseEntity<?> verifySession(HttpSession session) {
        String token = (String) session.getAttribute("access_token");
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado: No hay sesión activa");
        }
        return null;
    }

    @PostMapping("/Crear")
    @ApiOperation(value = "Crear un nuevo usuario", notes = "Registra un nuevo usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario creado exitosamente"),
            @ApiResponse(code = 400, message = "Datos de entrada inválidos"),
            @ApiResponse(code = 409, message = "El usuario ya existe"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public ResponseEntity<?> crearUsuario(@RequestBody @Valid Usuario usuario) {
        try {
            Usuario createdUsuario = usuarioService.createUsuario(usuario);
            if (createdUsuario == null) {
                return new ResponseEntity<>("El usuario ya existe", HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error inesperado al crear el usuario: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/Usuarios")
    @ApiOperation(value = "Obtener todos los usuarios", notes = "Retorna una lista de todos los usuarios registrados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de usuarios obtenida correctamente"),
            @ApiResponse(code = 204, message = "No hay usuarios registrados"),
            @ApiResponse(code = 401, message = "No autorizado")
    })
    public ResponseEntity<?> getAllUsuarios(HttpSession session) {
        ResponseEntity<?> verification = verifySession(session);
        if (verification != null) return verification;

        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>("No hay usuarios registrados", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long usuarioId, HttpSession session) {
        ResponseEntity<?> verification = verifySession(session);
        if (verification != null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        if (usuarioId == null || usuarioId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        return usuarioService.getUsuarioById(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{usuarioId}")
    @ApiOperation(value = "Actualizar usuario", notes = "Actualiza los datos de un usuario existente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario actualizado correctamente"),
            @ApiResponse(code = 404, message = "Usuario no encontrado"),
            @ApiResponse(code = 400, message = "Datos de entrada inválidos"),
            @ApiResponse(code = 401, message = "No autorizado")
    })
    public ResponseEntity<Usuario> updateUsuario(
            @PathVariable Long usuarioId,
            @RequestBody @Valid Usuario updatedUsuario,
            HttpSession session) {

        // Verify session - return empty 401 if unauthorized
        if (session.getAttribute("access_token") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Validate ID - return empty 400 if invalid
        if (usuarioId == null || usuarioId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        // Return either the updated user or 404 if not found
        return usuarioService.updateUsuario(usuarioId, updatedUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{usuarioId}")
    @ApiOperation(value = "Eliminar usuario", notes = "Elimina un usuario del sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Usuario eliminado correctamente"),
            @ApiResponse(code = 404, message = "Usuario no encontrado"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 400, message = "ID de usuario inválido")
    })
    public ResponseEntity<?> deleteUsuarioById(@PathVariable Long usuarioId, HttpSession session) {
        ResponseEntity<?> verification = verifySession(session);
        if (verification != null) return verification;

        if (usuarioId == null || usuarioId <= 0) {
            return new ResponseEntity<>("ID de usuario inválido", HttpStatus.BAD_REQUEST);
        }

        if (usuarioService.deleteUsuario(usuarioId)) {
            return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}