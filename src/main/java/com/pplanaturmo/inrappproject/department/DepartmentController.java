package com.pplanaturmo.inrappproject.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.department.dtos.DepartmentRequest;
import com.pplanaturmo.inrappproject.department.dtos.UpdateDepartmentManager;
import com.pplanaturmo.inrappproject.measurement.dtos.MeasurementRequest;
import com.pplanaturmo.inrappproject.user.User;
import com.pplanaturmo.inrappproject.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE })
@RequestMapping("/api/department")
@Tag(name = "Controlador de Departamentos", description = "Operaciones relacionadas con el manejo de departamento")
public class DepartmentController {

        @Autowired
        private DepartmentService departmentService;

        @Autowired
        private DepartmentRepository departmentRepository;

        @Autowired
        private UserService userService;

        @PostMapping("/create")
        @Operation(summary = "Crear nuevo departamento", description = "Crear nuevo departamento proporcionando los datos necesarios.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del departamento a crear", required = true, content = @Content(schema = @Schema(implementation = DepartmentRequest.class))), responses = {
                        @ApiResponse(responseCode = "200", description = "Departmento creado con éxito", content = @Content(schema = @Schema(implementation = Department.class))),
                        @ApiResponse(responseCode = "400", description = "Datos recibidos no validos"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public Department createDepartment(
                        @Parameter(description = "Objeto para validar datos de creación de departamento", required = true) @Valid @RequestBody DepartmentRequest createDepartmentRequest) {

                Department department = departmentService.convertToDepartment(createDepartmentRequest);

                return departmentService.saveDepartment(department);
        }

        @GetMapping("/")
        @Operation(summary = "Mostrar todos los departamentos", description = "Retrieve a list of all departments.", responses = {
                        @ApiResponse(responseCode = "200", description = "Listado de departamentos", content = @Content(schema = @Schema(implementation = Department.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public List<Department> getAllDepartments() {
                return departmentService.getAllDepartments();
        }

        @PutMapping("/{departmentId}/manager")
        @Operation(summary = "Asignar coordinador al departamnento", description = "Asignar coordinador al departamento proporcionando los datos necesarios.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del departamento a actualizar", required = true, content = @Content(schema = @Schema(implementation = UpdateDepartmentManager.class))), responses = {
                        @ApiResponse(responseCode = "200", description = "Manager assigned successfully", content = @Content(schema = @Schema(implementation = Department.class))),
                        @ApiResponse(responseCode = "404", description = "Departmento no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public Department assignManagerToDepartment(
                        @Parameter(description = "ID del departamento", required = true) @PathVariable("departmentId") @Valid @NotNull Long departmentId,
                        @Parameter(description = "Objeto para validar datos de actualización de departamento", required = true)@RequestBody @Valid UpdateDepartmentManager updateDepartmentManager) {
                Long profesionalId = updateDepartmentManager.getManagerId();
                return departmentService.assignManagerToDepartment(departmentId, profesionalId);
        }

        @PutMapping("/{departmentId}")
        @Operation(summary = "Actualizar un departamento", description = "Actualizar un departamento proporcionando el ID y los datos", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del departamento a actualizar", required = true, content = @Content(schema = @Schema(implementation = DepartmentRequest.class))), responses = {
                        @ApiResponse(responseCode = "200", description = "Departamento actualizado con exito"),
                        @ApiResponse(responseCode = "404", description = "Departamento no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public void updateDepartment(
                        @Parameter(description = "ID del departamento a actualizar", required = true) @PathVariable("departmentId") @Valid @NotNull Long departmentId,
                        @Valid @RequestBody DepartmentRequest updateDepartmentRequest) {
                Department departmentToUpdate = departmentRepository.getReferenceById(departmentId);
                departmentToUpdate.setName(updateDepartmentRequest.getName());
                departmentToUpdate.setCity(updateDepartmentRequest.getCity());
                departmentService.updateDepartment(departmentToUpdate);
        }

        @DeleteMapping("/{departmentId}")
        @Operation(summary = "Borrar un departamento", description = "Borrar un departamento proporcionando el ID.", responses = {
                        @ApiResponse(responseCode = "200", description = "Departamento borrado con exito"),
                        @ApiResponse(responseCode = "404", description = "Departamento no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public void deleteDepartmentById(
                        @Parameter(description = "ID del departamento a borrar", required = true) @Valid @PathVariable("departmentId") @NotNull Long departmentId) {
                departmentService.deleteDepartmentById(departmentId);
        }

        @GetMapping("/{departmentId}/users")
        @Operation(summary = "Obtener usuarios pertenecientes al departamento", description = "Recuperar listado de usuarios pertenecientes al departamento a partir del ID de departamento.", responses = {
                        @ApiResponse(responseCode = "200", description = "Listado de usuarios", content = @Content(schema = @Schema(implementation = User.class))),
                        @ApiResponse(responseCode = "404", description = "Departamento no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public List<User> getUsersByDepartmentId(
                        @Parameter(description = "ID del departamento al que pertenecen los usuarios a buscar", required = true) @Valid @PathVariable("departmentId") @NotNull Long departmentId) {
                return userService.getUsersByDepartmentId(departmentId);
        }
}
