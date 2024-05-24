package com.pplanaturmo.inrappproject.measurement;

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

import com.pplanaturmo.inrappproject.alerts.AlertService;
import com.pplanaturmo.inrappproject.dosage.DosageService;
import com.pplanaturmo.inrappproject.expectedMeasurementDate.ExpectedMeasurementDateService;
import com.pplanaturmo.inrappproject.measurement.dtos.MeasurementRequest;
import com.pplanaturmo.inrappproject.user.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE })
@RequestMapping("/api/measurement")
@Tag(name = "Control de Medidas", description = "Operaciones relacionadas con el control de medidas")
public class MeasurementController {

        @Autowired
        private MeasurementService measurementService;

        @Autowired
        private DosageService dosageService;

        @Autowired
        private AlertService alertService;

        @Autowired
        private ExpectedMeasurementDateService expectedMeasurementDateService;

        @PostMapping("/create/{userId}")
        @Operation(summary = "Crear nueva medida", description = "Crear nueva medida para un usuario especifico proporcionando los datos relevantes.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Detalles de la medida a crear", required = true, content = @Content(schema = @Schema(implementation = MeasurementRequest.class))), responses = {
                        @ApiResponse(responseCode = "200", description = "Medida creada correctament"),
                        @ApiResponse(responseCode = "400", description = "Datos introducidos incorrectos"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public Long createMeasurement(
                        @Parameter(description = "ID del usuario que crea la medición", required = true) @PathVariable("userId") @Valid @NotNull Long userId,
                        @Valid @RequestBody MeasurementRequest measurementRequest) {

                Measurement measurement = measurementService.convertToMeasurement(userId, measurementRequest);
                Measurement newMeasurement = measurementService.createMeasurement(measurement);

                alertService.createAlertIfNeeded(newMeasurement);
                dosageService.createDosagesByMeasurement(newMeasurement);
                Integer daysToNextMeasurement = newMeasurement.getDosagesValuesList().length;
                User measurementUser = measurement.getUser();
                expectedMeasurementDateService.generateExpectedMeasurementDate(daysToNextMeasurement, measurementUser, measurement.getValue());
                return newMeasurement.getId();
        }

        @GetMapping("/")
        @Operation(summary = "Obtener todas las medidas", description = "Retrieve a list of all measurements.", responses = {
                        @ApiResponse(responseCode = "200", description = "Listado de medidas", content = @Content(schema = @Schema(implementation = Measurement.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public List<Measurement> getAllMeasurements() {
                return measurementService.getAllMeasurements();
        }

        @GetMapping("/{measurementId}")
        @Operation(summary = "Obtener medida a partir del ID", description = "Obterner una medida específica usando el ID.", responses = {
                        @ApiResponse(responseCode = "200", description = "Measurement details", content = @Content(schema = @Schema(implementation = Measurement.class))),
                        @ApiResponse(responseCode = "404", description = "Medida no encontrada"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public Measurement getMeasurementById(
                        @Parameter(description = "ID de la medida a obtener", required = true) @PathVariable("measurementId") @Valid @NotNull Long measurementId) {
                return measurementService.getMeasurementById(measurementId);
        }

        @GetMapping("/user/{userId}")
        @Operation(summary = "Obtener medidas por el ID de usuario", description = "Recuperar un listado de medidas para un usuario específico partiendo de su ID.", responses = {
                        @ApiResponse(responseCode = "200", description = "Listado de medidas", content = @Content(schema = @Schema(implementation = Measurement.class))),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public List<Measurement> getMeasurementsByUserId(
                        @Parameter(description = "ID del usuario del que se quiere obtener el listado de medidas", required = true) @PathVariable("userId") @Valid @NotNull Long userId) {
                return measurementService.getMeasurementsByUserId(userId);
        }

        @GetMapping("/last/{userId}")
        @Operation(summary = "Obtener la última medida partiendo del ID de usuario.", description = "Obtener la última medida para un usuario específico partiendo de su ID.", responses = {
                        @ApiResponse(responseCode = "200", description = "Detalles de la última medida", content = @Content(schema = @Schema(implementation = Measurement.class))),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public Measurement findLatestMeasurementByUserId(
                        @Parameter(description = "ID del usuario del que se quiere obtener la última medida", required = true) @PathVariable("userId") @Valid @NotNull Long userId) {
                return measurementService.findLatestMeasurementByUserId(userId);
        }

        @PutMapping("/{measurementId}")
        @Operation(summary = "Actualizar una medida", description = "Actualizar detalles de una medida específica a partir de su ID y nuevos datos.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated details of the measurement", required = true, content = @Content(schema = @Schema(implementation = MeasurementRequest.class))), responses = {
                        @ApiResponse(responseCode = "200", description = "Medida actualizada con éxito", content = @Content(schema = @Schema(implementation = Measurement.class))),
                        @ApiResponse(responseCode = "404", description = "Medida no encontrada"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public Measurement updateMeasurement(
                        @Parameter(description = "ID de la medida a actualizar", required = true) @PathVariable("measurementId") @Valid @NotNull Long measurementId,
                        @Valid @RequestBody MeasurementRequest measurementRequest) {

                Measurement measurementToUpdate = getMeasurementById(measurementId);
                measurementToUpdate.setValue(measurementRequest.getValue());

                return measurementService.updateMeasurement(measurementToUpdate);
        }

        @DeleteMapping("/{measurementId}")
        @Operation(summary = "Borrar una medida", description = "Borrar una medida especifica por su ID.", responses = {
                        @ApiResponse(responseCode = "200", description = "Medida borrada correctamente"),
                        @ApiResponse(responseCode = "404", description = "Medida no encontrada"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public void deleteMeasurement(
                        @Parameter(description = "ID de la medida a borrar", required = true) @PathVariable("measurementId") @Valid @NotNull Long measurementId) {
                measurementService.deleteMeasurement(measurementId);
        }

}