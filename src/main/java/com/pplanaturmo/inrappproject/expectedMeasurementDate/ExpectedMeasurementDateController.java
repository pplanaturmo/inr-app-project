package com.pplanaturmo.inrappproject.expectedMeasurementDate;

import com.pplanaturmo.inrappproject.user.User;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;



@RestController
@Tag(name = "Controlador de Fechas Previstas de Medición", description = "Operaciones relacionadas con manejo de fechas previstas de medición")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/api/expected-measurement-dates")
public class ExpectedMeasurementDateController {

    @Autowired
    private ExpectedMeasurementDateService expectedMeasurementDateService;


    @Operation(summary = "Obtener una fecha prevista de medición por ID", description = "Recuperar una fecha prevista de medición por su identificador único.", responses = {
            @ApiResponse(responseCode = "200", description = "Fecha prevista de medición recuperada con éxito", content = @Content(schema = @Schema(implementation = ExpectedMeasurementDate.class))),
            @ApiResponse(responseCode = "404", description = "Fecha prevista de medición no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExpectedMeasurementDate> getExpectedMeasurementDateById(
            @Parameter(description = "ID de la fecha prevista de medición", required = true) @PathVariable Long id) {
        ExpectedMeasurementDate date = expectedMeasurementDateService.findById(id);
        if (date == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(date);
    }

    @Operation(summary = "Obtener todas las fechas previstas de medición", description = "Devuelve todas las fechas previstas de medición.", responses = {
            @ApiResponse(responseCode = "200", description = "Listado de fechas previstas de medición recuperado con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<ExpectedMeasurementDate>> getAllExpectedMeasurementDates() {
        return ResponseEntity.ok(expectedMeasurementDateService.findAll());
    }



    @Operation(summary = "Obtener fechas previstas de medición por ID de usuario", description = "Obtener fechas previstas de medición por el identificador del usuario.", responses = {
            @ApiResponse(responseCode = "200", description = "Dechas previstas de medición recuperado con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpectedMeasurementDate>> getExpectedMeasurementDatesByUserId(
            @Parameter(description = "ID del usuario", required = true) @PathVariable Long userId) {
        return ResponseEntity.ok(expectedMeasurementDateService.findByUserId(userId));
    }

    @Operation(summary = "Obtener fechas previstas de medición por cumplimiento", description = "Recuperar fechas previstas de medición por el cumplimiento.", responses = {
            @ApiResponse(responseCode = "200", description = "Listado de fechas previstas de medición recuperado con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/fulfilled")
    public ResponseEntity<List<ExpectedMeasurementDate>> getExpectedMeasurementDatesByFulfilled(
            @Parameter(description = "Estado de cumplimiento", required = true) @RequestParam Boolean fulfilled) {
        return ResponseEntity.ok(expectedMeasurementDateService.findByFulfilled(fulfilled));
    }

    @Operation(summary = "Obtener fechas previstas de medición con fecha esperada y cumplida no coincidentes", description = "Recuperar fechas previstas de medición con fecha esperada y cumplida no coincidentes.", responses = {
            @ApiResponse(responseCode = "200", description = "Listado de fechas previstas de medición con ffecha esperada y cumplida no coincidentes recuperado con éxito")
    })
    @GetMapping("/mismatched-dates")
    public ResponseEntity<List<ExpectedMeasurementDate>> getExpectedMeasurementDatesWithMismatchedDates() {
        return ResponseEntity.ok(expectedMeasurementDateService.findByMismatchedDates());
    }
    @Operation(summary = "Obtener la última fecha prevista de medición para un usuario", description = "Recuperar la última fecha prevista de medición para un usuario por su identificador único.", responses = {
            @ApiResponse(responseCode = "200", description = "Última fecha prevista de medición recuperada con éxito", content = @Content(schema = @Schema(implementation = ExpectedMeasurementDate.class))),
            @ApiResponse(responseCode = "404", description = "Fecha prevista de medición no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/user/{userId}/last")
    public ExpectedMeasurementDate getLastExpectedMeasurementDateByUserId(
            @Parameter(description = "ID del usuario", required = true) @PathVariable Long userId) {
        ExpectedMeasurementDate expectedDateObject = expectedMeasurementDateService.getLastExpectedMeasurementDateByUserId(userId);

        if (expectedDateObject == null) {
            return null;
        }
        return expectedDateObject;
    }

    @Operation(summary = "Actualizar una fecha prevista de medición", description = "Actualizar los datos de una fecha prevista de medición a partir de su ID único.", responses = {
            @ApiResponse(responseCode = "200", description = "Fecha prevista de medición actualizada con éxito", content = @Content(schema = @Schema(implementation = ExpectedMeasurementDate.class))),
            @ApiResponse(responseCode = "404", description = "Fecha prevista de medición no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ExpectedMeasurementDate> updateExpectedMeasurementDate(
            @Parameter(description = "ID de la fecha prevista de medición", required = true) @PathVariable Long id,
            @Parameter(description = "Objeto actualizado de la fecha prevista de medición", required = true) @RequestBody ExpectedMeasurementDate updatedDate) {
        if (expectedMeasurementDateService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        updatedDate.setId(id);
        return ResponseEntity.ok(expectedMeasurementDateService.updateExpectedMeasurementDate(updatedDate));
    }

    @Operation(summary = "Borrar una fecha prevista de medición", description = "Borrar una fecha prevista de medición por su identificador único.", responses = {
            @ApiResponse(responseCode = "204", description = "Fecha prevista de medición borrada con éxito"),
            @ApiResponse(responseCode = "404", description = "Fecha prevista de medición no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpectedMeasurementDate(
            @Parameter(description = "ID de la fecha prevista de medición", required = true) @PathVariable Long id) {
        if (expectedMeasurementDateService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        expectedMeasurementDateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}