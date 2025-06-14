package com.prueba.loginAPI.infrastructure.controllers.Seguridad;

import com.prueba.loginAPI.application.services.Seguridad.PermisosService;
import com.prueba.loginAPI.domain.model.Seguridad.Permiso;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.Permisos;
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
@RequestMapping("/api/permisos")
@Validated
public class PermisoController {

    private final PermisosService permisoService;

    public PermisoController(PermisosService permisoService) {
        this.permisoService = permisoService;
    }

    // Método privado para verificar la sesión
    private ResponseEntity<?> verifySession(HttpSession session) {
        String token = (String) session.getAttribute("access_token");
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado: No hay sesión activa");
        }
        return null; // Retorna null si la sesión es válida
    }

    @PostMapping
    @ApiOperation(value = "Crear un nuevo permiso")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Permiso creado exitosamente"),
            @ApiResponse(code = 400, message = "Datos de entrada inválidos"),
            @ApiResponse(code = 401, message = "No autorizado")
    })
    public ResponseEntity<?> createPermiso(@RequestBody @Valid Permisos permiso, HttpSession session) {
        ResponseEntity<?> verification = verifySession(session);
        if (verification != null) return verification;

        Permisos createdPermiso = permisoService.createPermiso(permiso);
        return new ResponseEntity<>(createdPermiso, HttpStatus.CREATED);
    }

    @GetMapping("/{permisoId}")
    @ApiOperation(value = "Obtener permiso por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Permiso encontrado"),
            @ApiResponse(code = 404, message = "Permiso no encontrado"),
            @ApiResponse(code = 401, message = "No autorizado")
    })
    public ResponseEntity<Permiso> getPermisoById(@PathVariable Long permisoId, HttpSession session) {
        ResponseEntity<?> verification = verifySession(session);
        if (verification != null) return (ResponseEntity<Permiso>) verification;

        return permisoService.getPermisoById(permisoId)
                .map(permiso -> new ResponseEntity<>(permiso, HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }



    @GetMapping("/todos")
    @ApiOperation(value = "Obtener todos los permisos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de permisos obtenida"),
            @ApiResponse(code = 204, message = "No hay permisos disponibles"),
            @ApiResponse(code = 401, message = "No autorizado")
    })
    public ResponseEntity<?> getAllPermisos(HttpSession session) {
        ResponseEntity<?> verification = verifySession(session);
        if (verification != null) return verification;

        List<Permiso> permisos = permisoService.getAllPermiso();
        if (permisos.isEmpty()) {
            return new ResponseEntity<>("No hay permisos disponibles", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(permisos, HttpStatus.OK);
    }



    @DeleteMapping("/{permisoId}")
    @ApiOperation(value = "Eliminar un permiso")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Permiso eliminado exitosamente"),
            @ApiResponse(code = 404, message = "Permiso no encontrado"),
            @ApiResponse(code = 401, message = "No autorizado")
    })
    public ResponseEntity<?> deletePermisoById(@PathVariable Long permisoId, HttpSession session) {
        ResponseEntity<?> verification = verifySession(session);
        if (verification != null) return verification;

        if (permisoService.deletePermiso(permisoId)) {
            return new ResponseEntity<>("Permiso eliminado exitosamente", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("No se pudo eliminar el permiso con ID: " + permisoId, HttpStatus.NOT_FOUND);
        }
    }
}