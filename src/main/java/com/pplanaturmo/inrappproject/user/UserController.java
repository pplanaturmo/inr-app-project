package com.pplanaturmo.inrappproject.user;

import java.util.List;

import com.pplanaturmo.inrappproject.auth.dtos.AuthenticatedUserResponse;
import com.pplanaturmo.inrappproject.auth.token.JwtService;
import com.pplanaturmo.inrappproject.user.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@Tag(name = "Controlador de Usuarios", description = "Operaciones relacionadas con manejo de usuarios")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE })
@RequestMapping("/api/user")
public class UserController {

        @Autowired
        private UserService userService;

        @Autowired
        private JwtService jwtService;

        @PostMapping("/create")
        @Operation(summary = "Crear nuevo usuario", description = "Este endpoint permite la creación de un nuevo usuario a traves de los datos recibidos.", responses = {
                        @ApiResponse(responseCode = "200", description = "Usuario creado con exito", content = @Content(schema = @Schema(implementation = User.class))),
                        @ApiResponse(responseCode = "400", description = "Datos recibidos no validos"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public User createUser(
                        @Parameter(description = "Objeto para validar datos de creación de usuario", required = true) @Valid @RequestBody UserRequest createUserRequest) {
                User user = userService.convertToUser(createUserRequest);
                User newUser = userService.createUser(user);
                userService.assignInrToUser(newUser.getId(),createUserRequest.getRangeInr());
                userService.assignDosePatternToUser(newUser.getId(),createUserRequest.getDosePattern());
                return newUser;
        }

        @GetMapping("/all")
        @Operation(summary = "Obtener listado de usuarios", description = "Este enpoint permite la obtención de un listado de usuarios", responses = {
                        @ApiResponse(responseCode = "200", description = "Listado de usuarios", content = @Content(schema = @Schema(implementation = User.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public List<User> getAllUsers() {
                return userService.getAllUsers();
        }

        @GetMapping("/{userId}")
        @Operation(summary = "Obtener usuario por ID", description = "Recuperar un usuario por su identificador único", responses = {
                        @ApiResponse(responseCode = "200", description = "Detalles del usario", content = @Content(schema = @Schema(implementation = User.class))),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public User getUserById(
                        @Parameter(description = "ID del usuario a recuperar", required = true) @PathVariable("userId") @Valid @NotNull Long userId) {
                return userService.getUserById(userId);
        }

        @PutMapping("/{userId}")
        @Operation(summary = "Actualizar usuario", description = "Actualizar datos de un usuario a partir de su ID único.", responses = {
                        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente", content = @Content(schema = @Schema(implementation = User.class))),
                        @ApiResponse(responseCode = "400", description = "Datos recibidos no validos"),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public AuthenticatedUserResponse updateUser(
                        @Parameter(description = "ID del usuario a actualizar", required = true) @PathVariable("userId") @Valid @NotNull Long userId,
                        @Parameter(description = "Objeto para validar datos de actualización de usuario", required = true) @Valid @RequestBody UpdateUserRequest updateUserRequest) {
                User user = userService.getUserById(userId);
                userService.updateUser(user, updateUserRequest);

                var jwtToken = jwtService.generateToken(user);
                var refreshToken = jwtService.generateRefreshToken(user);

               // return userService.getUserById(userId);
                return AuthenticatedUserResponse.builder()
                        .accessToken(jwtToken)
                        .refreshToken(refreshToken)
                        .id(user.getId())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .department(user.getDepartment() != null ? user.getDepartment().getId() : null)
                        .supervisor(user.getSupervisor() != null ? user.getSupervisor().getId() : null)
                        .rangeInr(user.getRangeInr() != null ? user.getRangeInr().getId() : null)
                        .dosePattern(user.getDosePattern() != null ? user.getDosePattern().getId() : null)
                        .role(user.getUserRole())
                        .email(user.getEmail())
                        .dataConsent(user.getDataConsent())
                        .build();
        }

        @PutMapping("/{userId}/department")
        @Operation(summary = "Asignar departamento a usuario", description = "Se asigna un usuario a partir de su ID a un departamento", responses = {
                        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente", content = @Content(schema = @Schema(implementation = User.class))),
                        @ApiResponse(responseCode = "400", description = "Datos recibidos no validos"),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public User assignDepartmentToUser(
                        @Parameter(description = "ID del usuario que se asigna a un servicio o departamento", required = true) @PathVariable("userId") @Valid @NotNull Long userId,
                        @Parameter(description = "Objeto para validar datos de actualización de departamento de un usuario", required = true) @RequestBody @Valid UpdateUserDepartment updateUserDepartment) {
                Long departmentId = updateUserDepartment.getDepartmentId();
                return userService.assignDepartmentToUser(userId, departmentId);
        }

        @PutMapping("/{userId}/supervisor")
        @Operation(summary = "Asignar supervisor a un usuario", description = "Asignar supervisor a un usuario identificado por su ID", responses = {
                        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente", content = @Content(schema = @Schema(implementation = User.class))),
                        @ApiResponse(responseCode = "400", description = "Datos recibidos no validos"),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public User assignSupervisorToUser(
                        @Parameter(description = "ID del usuario al que se le asigna un supervisor", required = true) @PathVariable("userId") @Valid @NotNull Long userId,
                        @Parameter(description = "Objeto para validar datos de actualización de supervisor de usuario", required = true) @RequestBody @Valid UpdateUserSupervisor updateUserSupervisor) {
                Long professionalId = updateUserSupervisor.getProfessionalId();
                return userService.assignSupervisorToUser(userId, professionalId);
        }

        @PutMapping("/{userId}/range-inr")
        @Operation(summary = "Establecer rango terapéutico de un usuario", description = "Establecer rango terapéutico de un usuario a partir de su ID", responses = {
                        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente", content = @Content(schema = @Schema(implementation = User.class))),
                        @ApiResponse(responseCode = "400", description = "Datos recibidos no validos"),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public void setInrRange(
                        @Parameter(description = "ID del usuario al que se le asigna un rango terapéutico", required = true) @PathVariable("userId") @Valid @NotNull Long userId,
                        @Parameter(description = "Objeto para validar datos de actualización de rango INR de usuario", required = true) @RequestBody @Valid UpdateUserInrRange updateUserInrRange) {
                Long rangeId = updateUserInrRange.getRangeId();
                userService.assignInrToUser(userId, rangeId);
        }

        @PutMapping("/{userId}/dose-pattern")
        @Operation(summary = "Establecer patrón de dosificación de un usuario", description = "Establecer patrón de dosificación del usuario a partir de su ID.", responses = {
                        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente", content = @Content(schema = @Schema(implementation = User.class))),
                        @ApiResponse(responseCode = "400", description = "Datos recibidos no validos"),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public void setDosePattern(
                        @Parameter(description = "ID del usuario al que se le asigna un patrón de dosificación", required = true) @PathVariable("userId") @Valid @NotNull Long userId,
                        @Parameter(description = "Objeto para validar datos de actualización de patrón de dosificación de usuario", required = true) @RequestBody @Valid UpdateUserPattern updateUserPattern) {
                Long patternId = updateUserPattern.getPatternId();
                userService.assignDosePatternToUser(userId, patternId);
        }

        @PutMapping("/{userId}/role")
        @Operation(summary = "Establecer rol de un usuario", description = "Asignar rol a un usuario a partir de su ID.", responses = {
                        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente", content = @Content(schema = @Schema(implementation = User.class))),
                        @ApiResponse(responseCode = "400", description = "Datos recibidos no validos"),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public void setRole(
                        @Parameter(description = "ID del usuario al que se le asigna un rol", required = true) @PathVariable("userId") @Valid @NotNull Long userId,
                        @Parameter(description = "Objeto para validar datos de actualización de rol de usuario", required = true) @RequestBody @Valid UpdateUserRole updateUserRole) {
                // String roleName = updateUserRole.getAssignedRole();
                userService.assignRoleToUser(userId, updateUserRole.getAssignedRole());
        }

        @DeleteMapping("/{userId}")
        @Operation(summary = "Delete user", description = "Delete a user by their unique ID.", responses = {
                        @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public void deleteUser(
                        @Parameter(description = "ID del usuario a eliminar", required = true) @Valid @PathVariable("userId") @NotNull Long userId) {
                userService.deleteUser(userId);
        }

}
