package com.prueba.loginAPI.infrastructure.controllers.Seguridad;

import com.prueba.loginAPI.application.services.Seguridad.RolesService;
import com.prueba.loginAPI.domain.model.Seguridad.Rol;
import com.prueba.loginAPI.infrastructure.entities.Seguridad.Roles;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Validated
public class RolController {

    private final RolesService rolService;

    public RolController(RolesService rolService) {
        this.rolService = rolService;
    }

    private ResponseEntity<Rol> verifySession(HttpSession session) {
        String token = (String) session.getAttribute("access_token");
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return null;
    }


    @GetMapping("/{rolId}")
    public ResponseEntity<Rol> getRolById(@PathVariable Long rolId, HttpSession session) {
        ResponseEntity<Rol> verification = verifySession(session);
        if (verification != null) return verification;

        return rolService.getRolById(rolId)
                .map(rol -> new ResponseEntity<>(rol, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Rol>> getAllRoles(HttpSession session) {
        ResponseEntity<Rol> verification = verifySession(session);
        if (verification != null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        List<Rol> roles = rolService.getAllRol();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PutMapping("/{rolId}")
    public ResponseEntity<Rol> updateRol(@PathVariable Long rolId,
                                         @RequestBody @Valid Roles updatedRol,
                                         HttpSession session) {
        ResponseEntity<Rol> verification = verifySession(session);
        if (verification != null) return verification;

        return rolService.updateRol(rolId, updatedRol)
                .map(rol -> new ResponseEntity<>(rol, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{rolId}")
    public ResponseEntity<Void> deleteRolById(@PathVariable Long rolId, HttpSession session) {
        ResponseEntity<Rol> verification = verifySession(session);
        if (verification != null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        if (rolService.deleteRol(rolId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}