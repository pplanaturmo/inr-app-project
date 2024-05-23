package com.pplanaturmo.inrappproject.dosage;

import java.util.List;

import com.pplanaturmo.inrappproject.dosage.dtos.DosageRequest;
import com.pplanaturmo.inrappproject.dosage.dtos.DosageResponse;
import com.pplanaturmo.inrappproject.user.User;
import com.pplanaturmo.inrappproject.user.dtos.UserRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pplanaturmo.inrappproject.aggregatedDtos.DatesBetweenDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/api/dosage")
@Tag(name = "Control de dosis", description = "Operaciones relacionadas con el manejo de dosis")
public class DosageController {

    @Autowired
    private DosageService dosageService;

    @Operation(summary = "Obtener Dosis por ID", description = "Recuperar dosis por su ID", responses = {
            @ApiResponse(responseCode = "200", description = "Dosis encontrada", content = @Content(schema = @Schema(implementation = Dosage.class))),
            @ApiResponse(responseCode = "404", description = "Dosis no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public ResponseEntity<Dosage> getDosageById(
            @Parameter(description = "ID de la dosis a recuperar", required = true) @PathVariable Long id) {
        Dosage dosage = dosageService.getDosageById(id);
        if (dosage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dosage, HttpStatus.OK);
    }

    @PostMapping("/between-dates")
    @Operation(summary = "Obtener dosis entre fechas", description = "Recuperar un listado de dosis entre fechas específicas para un usuario", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Date range for retrieving dosages", required = true, content = @Content(schema = @Schema(implementation = DatesBetweenDto.class))), responses = {
            @ApiResponse(responseCode = "200", description = "Listado de dosis entre fechas para un usuario", content = @Content(schema = @Schema(implementation = Dosage.class))),
            @ApiResponse(responseCode = "400", description = "Datos enviados no validos"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public List<DosageResponse> getDosagesBetweenDates(@Valid @RequestBody DatesBetweenDto datesBetweenDto) {
        return dosageService.getDosagesBetweenDates(datesBetweenDto);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtener todas las dosis por ID de usuario", description = "Recuperar un listados de todas las dosis para un usuario específico", responses = {
            @ApiResponse(responseCode = "200", description = "Listado de dosis del usuario", content = @Content(schema = @Schema(implementation = Dosage.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public ResponseEntity<List<Dosage>> getAllDosagesByUser(
            @Parameter(description = "ID del usuario al que pertenecen las dosis a recuperar", required = true) @PathVariable Long userId) {
        List<Dosage> dosages = dosageService.getAllDosagesByUser(userId);
        return new ResponseEntity<>(dosages, HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Actualizar dosis", description = "Actualizar datos de una dosis a partir de su ID único.", responses = {
            @ApiResponse(responseCode = "200", description = "Dosis actualizada correctamente", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Datos recibidos no validos"),
            @ApiResponse(responseCode = "404", description = "Dosis no encontrad"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public Dosage updateDosage(
            @Valid @RequestBody DosageRequest dosageRequest) {
        Dosage dosageToUpdate = dosageService.convertToDosageToUpdate(dosageRequest);

        return dosageService.updateDosage(dosageToUpdate);
    }

}
