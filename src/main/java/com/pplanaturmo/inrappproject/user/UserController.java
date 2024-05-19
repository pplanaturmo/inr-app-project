package com.pplanaturmo.inrappproject.user;

import java.util.List;

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

import com.pplanaturmo.inrappproject.user.dtos.UpdateUserDepartment;
import com.pplanaturmo.inrappproject.user.dtos.UpdateUserInrRange;
import com.pplanaturmo.inrappproject.user.dtos.UpdateUserPattern;
import com.pplanaturmo.inrappproject.user.dtos.UpdateUserSupervisor;
import com.pplanaturmo.inrappproject.user.dtos.UserRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@Tag(name = "Control de Usuario", description = "Operaciones relacionadas con manejo de usuarios")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE })
@RequestMapping("/api/user")
public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping("/create")
        @Operation(summary = "Crear nuevo usuario", description = "Este endpoint permite la creación de un nuevo usuario a traves de los datos recibidos.", responses = {
                        @ApiResponse(responseCode = "200", description = "Usuario creado con exito", content = @Content(schema = @Schema(implementation = User.class))),
                        @ApiResponse(responseCode = "400", description = "Datos recibidos no validos"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public User createUser(
                        @Parameter(description = "Objeto para validar datos de creación de usuario", required = true) @Valid @RequestBody UserRequest createUserRequest) {
                User user = userService.convertToUser(createUserRequest);

                return userService.createUser(user);
        }

        @GetMapping("/")
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
        public User updateUser(
                        @Parameter(description = "ID del usuario a actualizar", required = true) @PathVariable("userId") @Valid @NotNull Long userId,
                        @Valid @RequestBody UserRequest createUserRequest) {

                User user = userService.convertToUser(createUserRequest);
                user.setId(userId);
                return userService.updateUser(user);
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
                        @RequestBody @Valid UpdateUserDepartment updateUserDepartment) {
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
                        @RequestBody @Valid UpdateUserSupervisor updateUserSupervisor) {
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
        public User set(
                        @Parameter(description = "ID del usuario al que se le asigna un rango terapéutico", required = true) @PathVariable("userId") @Valid @NotNull Long userId,
                        @RequestBody @Valid UpdateUserInrRange updateUserInrRange) {
                Long rangeId = updateUserInrRange.getRangeId();
                return userService.assignDepartmentToUser(userId, rangeId);
        }

        @PutMapping("/{userId}/dose-pattern")
        @Operation(summary = "Establecer patrón de dosificación de un usuario", description = "Set the dose pattern for a user by their unique ID.", responses = {
                        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente", content = @Content(schema = @Schema(implementation = User.class))),
                        @ApiResponse(responseCode = "400", description = "Datos recibidos no validos"),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public User set(
                        @Parameter(description = "ID del usuario al que se le asigna un patrón de dosificación", required = true) @PathVariable("userId") @Valid @NotNull Long userId,
                        @RequestBody @Valid UpdateUserPattern updateUserPattern) {
                Long patternId = updateUserPattern.getPatternId();
                return userService.assignDepartmentToUser(userId, patternId);
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
