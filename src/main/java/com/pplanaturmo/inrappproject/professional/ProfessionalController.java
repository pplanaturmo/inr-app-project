package com.pplanaturmo.inrappproject.professional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.professional.Professional.TypeEnum;
import com.pplanaturmo.inrappproject.professional.dtos.ProfessionalRequest;
import com.pplanaturmo.inrappproject.user.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/api/professionals")
@Tag(name = "Controlador de Profesionales", description = "Operaciones relacinadas con profesionales")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @PostMapping
    @Operation(summary = "Crear profesional", description = "Crear un nuevo profesional", requestBody = @RequestBody(description = "DTO de validación de datos del profesional a crear", required = true, content = @Content(schema = @Schema(implementation = Professional.class))), responses = {
            @ApiResponse(responseCode = "200", description = "Profesional creado con éxito", content = @Content(schema = @Schema(implementation = Professional.class))),
            @ApiResponse(responseCode = "400", description = "Datos recibidos no válidos"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public Professional createProfessional(@Valid @RequestBody ProfessionalRequest professionalRequest) {
        Professional newProfesional = professionalService.convertToProfessional(professionalRequest);
        return professionalService.createProfessional(newProfesional);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los profesionales", description = "Recuperar un listado de todos los profesionales", responses = {
            @ApiResponse(responseCode = "200", description = "Listado d profesionales", content = @Content(schema = @Schema(implementation = Professional.class))),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public List<Professional> getAllProfessionals() {
        return professionalService.getAllProfessionals();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener profesional por ID", description = "Recuperar datos de un profesional por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Datos del profesional", content = @Content(schema = @Schema(implementation = Professional.class))),
            @ApiResponse(responseCode = "404", description = "Profesional no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public Professional getProfessionalById(
            @Parameter(description = "ID del profesional", required = true) @PathVariable("id") @Valid @NotNull Long id) {
        return professionalService.getProfessionalById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un professional", description = "Actualizar detalles de un profesional existente", requestBody = @RequestBody(description = "DTO de validación de datos a actualizar", required = true, content = @Content(schema = @Schema(implementation = Professional.class))), responses = {
            @ApiResponse(responseCode = "200", description = "Profesional actualizado con éxito", content = @Content(schema = @Schema(implementation = Professional.class))),
            @ApiResponse(responseCode = "400", description = "Datos recibidos no válidos"),
            @ApiResponse(responseCode = "404", description = "Profesional no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public Professional updateProfessional(
            @Parameter(description = "ID del profesional", required = true) @PathVariable("id") @Valid @NotNull Long id,
            @Valid @RequestBody ProfessionalRequest professionalRequest) {
        Professional profesionalToUpdate = professionalRepository.getReferenceById(id);
        profesionalToUpdate.setRegisterNumber(professionalRequest.getRegisterNumber());
        profesionalToUpdate.setUserId(professionalRequest.getUserId());
        profesionalToUpdate.setType(TypeEnum.valueOf(professionalRequest.getType()));

        return professionalService.updateProfessional(profesionalToUpdate);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar un profesional", description = "Borrar un profesional existente por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Profesional borrado con éxito"),
            @ApiResponse(responseCode = "404", description = "Profesional no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public void deleteProfessional(
            @Parameter(description = "ID del profesional", required = true) @PathVariable("id") @Valid @NotNull Long id) {
        professionalService.deleteProfessional(id);
    }

    @GetMapping("/{professionalId}/supervised-users")
    @Operation(summary = "Obtener usuarios supervisados a partir del ID del profesional", description = "Recuperar la lista de usuarios supervisados por el profesional especificado", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios supervisados", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Profesional no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public List<User> getSupervisedUsers(
            @Parameter(description = "ID del profesional", required = true) @PathVariable Long professionalId) {
        return professionalService.getSupervisedUsers(professionalId);
    }
}
