package com.pplanaturmo.inrappproject.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.user.dtos.UpdateUserRole;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/api/roles")
@Tag(name = "Manejo de roles", description = "Operaciones relacionadas con el manejo de roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    @Operation(summary = "Obtener todos los roles", description = "Recuperar una lista con todos los roles disponibles.", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de roles devuelta con éxito", content = @Content(schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

}

// package com.pplanaturmo.inrappproject.role;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RestController;

// import com.pplanaturmo.inrappproject.user.dtos.UpdateUserRole;

// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.media.Content;
// import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.parameters.RequestBody;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.validation.Valid;

// @RestController
// @CrossOrigin(origins = "*", methods = { RequestMethod.GET,
// RequestMethod.POST, RequestMethod.PUT,
// RequestMethod.DELETE })
// @RequestMapping("/api/roles")
// @Tag(name = "Manejo de roles", description = "Operaciones relacionadas con el
// manejo de roles")
// public class RoleController {

// @Autowired
// private RoleService roleService;

// @PostMapping
// @Operation(summary = "Crear nuevo rol", description = "Create a new role by
// providing the necessary details.", requestBody = @RequestBody(description =
// "Details of the role to be created", required = true, content =
// @Content(schema = @Schema(implementation = UpdateUserRole.class))), responses
// = {
// @ApiResponse(responseCode = "200", description = "Role created successfully",
// content = @Content(schema = @Schema(implementation = Role.class))),
// @ApiResponse(responseCode = "400", description = "Invalid input data"),
// @ApiResponse(responseCode = "500", description = "Internal server error")
// })
// public Role createRole(@Valid @RequestBody UpdateUserRole updateUserRole) {

// Role newRole = new Role();

// Role.UserRole assignedRole =
// Role.UserRole.valueOf(updateUserRole.getAssignedRole().toUpperCase());

// newRole.setRole(assignedRole);
// return roleService.createRole(newRole);
// }

// @GetMapping
// public List<Role> getAllRoles() {
// return roleService.getAllRoles();
// }

// @GetMapping("/{id}")
// public Optional<Role> getRoleById(@PathVariable Long id) {
// return roleService.getRoleById(id);
// }

// @PutMapping("/{id}")
// public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
// role.setId(id);
// return roleService.updateRole(role);
// }

// @DeleteMapping("/{id}")
// public void deleteRole(@PathVariable Long id) {
// roleService.deleteRole(id);
// }

// }