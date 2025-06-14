package com.prueba.loginAPI.infrastructure.controllers.Seguridad;

import com.prueba.loginAPI.application.services.Seguridad.RolPermisoService;
import com.prueba.loginAPI.domain.model.Seguridad.RolPermiso;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.RolPermisos;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/RolPermiso")
public class RolPermisoController {

    private final RolPermisoService rolPermisoService;

    public RolPermisoController(RolPermisoService rolPermisoService) {
        this.rolPermisoService = rolPermisoService;
    }

    private ResponseEntity<?> verifySession(HttpSession session) {
        String token = (String) session.getAttribute("access_token");
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado: No hay sesión activa");
        }
        return null; // Retorna null si la sesión es válida
    }

    @PostMapping
    @ApiOperation(value = "Crear nuevo RolPermiso")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "RolPermiso creado"),
            @ApiResponse(code = 401, message = "No autorizado")
    })
    public ResponseEntity<RolPermisos> createRolPermiso(@RequestBody RolPermisos rolPermiso, HttpSession session) {
        ResponseEntity<?> verification = verifySession(session);
        if (verification != null) return (ResponseEntity<RolPermisos>) verification;

        RolPermisos createdRol = rolPermisoService.createRolPermiso(rolPermiso);
        return new ResponseEntity<>(createdRol, HttpStatus.CREATED);
    }

    @GetMapping("/{rolId}")
    @ApiOperation(value = "Obtener RolPermiso por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "RolPermiso encontrado"),
            @ApiResponse(code = 404, message = "RolPermiso no encontrado"),
            @ApiResponse(code = 401, message = "No autorizado")
    })
    public ResponseEntity<RolPermiso> getRolPermisoById(@PathVariable Long rolId, HttpSession session) {
        ResponseEntity<?> verification = verifySession(session);
        if (verification != null) return (ResponseEntity<RolPermiso>) verification;

        return rolPermisoService.getRolPermisoById(rolId)
                .map(rolPermiso -> new ResponseEntity<>(rolPermiso, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/todos")
    @ApiOperation(value = "Obtener todos los RolPermisos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de RolPermisos"),
            @ApiResponse(code = 401, message = "No autorizado")
    })
    public ResponseEntity<List<RolPermiso>> getAllRolPermisos(HttpSession session) {
        ResponseEntity<?> verification = verifySession(session);
        if (verification != null) return (ResponseEntity<List<RolPermiso>>) verification;

        List<RolPermiso> rolPermiso = rolPermisoService.getAllRolPermiso();
        return new ResponseEntity<>(rolPermiso, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualizar RolPermiso")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "RolPermiso actualizado"),
            @ApiResponse(code = 404, message = "RolPermiso no encontrado"),
            @ApiResponse(code = 401, message = "No autorizado")
    })
    public ResponseEntity<RolPermiso> updateRolPermiso(@PathVariable Long id,
                                                       @RequestBody RolPermisos updatedRol,
                                                       HttpSession session) {
        ResponseEntity<?> verification = verifySession(session);
        if (verification != null) return (ResponseEntity<RolPermiso>) verification;

        return rolPermisoService.updateRolPermiso(id, updatedRol)
                .map(rolPermiso -> new ResponseEntity<>(rolPermiso, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{rolId}")
    @ApiOperation(value = "Eliminar RolPermiso por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "RolPermiso eliminado"),
            @ApiResponse(code = 404, message = "RolPermiso no encontrado"),
            @ApiResponse(code = 401, message = "No autorizado")
    })
    public ResponseEntity<Void> deleteRolPermisoById(@PathVariable Long rolId, HttpSession session) {
        ResponseEntity<?> verification = verifySession(session);
        if (verification != null) return (ResponseEntity<Void>) verification;

        if (rolPermisoService.deleteRolPermiso(rolId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}