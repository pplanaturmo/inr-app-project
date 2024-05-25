package com.pplanaturmo.inrappproject.observation;

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

import com.pplanaturmo.inrappproject.aggregatedDtos.DatesBetweenDto;
import com.pplanaturmo.inrappproject.observation.dtos.ObservationRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE })
@RequestMapping("/api/observation")
public class ObservationController {

        @Autowired
        private ObservationService observationService;

        // @PostMapping("/create/{userId}")
        @PostMapping("/create/")
        @Operation(summary = "Crear una observación", description = "Crear una nueva observación para un usuario específico", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la observación a crear", required = true, content = @Content(schema = @Schema(implementation = ObservationRequest.class))), responses = {
                        @ApiResponse(responseCode = "200", description = "Observacin ´creada con éxito", content = @Content(schema = @Schema(implementation = Observation.class))),
                        @ApiResponse(responseCode = "400", description = "Datos recibidos no válidos"),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public Observation createObservation(
                        // @Parameter(description = "ID del usuario", required = true)
                        // @PathVariable("userId") @Valid @NotNull Long userId,
                        @Valid @RequestBody ObservationRequest observationRequest) {

                Observation newObservation = observationService.convertToObservation(observationRequest);
                return observationService.createObservation(newObservation);
        }

        @GetMapping("/")
        @Operation(summary = "Obtener todas las observaciones", description = "Recuperar un listado de todas las observaciones", responses = {
                        @ApiResponse(responseCode = "200", description = "Listado de observaciones", content = @Content(schema = @Schema(implementation = Observation.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public List<Observation> getAllObservations() {
                return observationService.getAllObservations();
        }

        @GetMapping("/user/{userId}")
        @Operation(summary = "Obtener observaciones por ID de usuario", description = "Recuperar listado de observaciones para un usuario específico", responses = {
                        @ApiResponse(responseCode = "200", description = "Listado de observaciones", content = @Content(schema = @Schema(implementation = Observation.class))),
                        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public List<Observation> getObservationsByUserId(
                        @Parameter(description = "ID del usuario", required = true) @PathVariable("userId") @Valid @NotNull Long userId) {
                return observationService.getObservationsByUserId(userId);
        }

        @GetMapping("/measurement/{measurementId}")
        @Operation(summary = "Obtener observaciones por ID de medida", description = "Recuperar un listado de observaciones relacionadas con una Medida específica", responses = {
                        @ApiResponse(responseCode = "200", description = "Listado de observaciones", content = @Content(schema = @Schema(implementation = Observation.class))),
                        @ApiResponse(responseCode = "404", description = "Medida no encontrada"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public List<Observation> getObservationsByMeasurementId(
                        @Parameter(description = "ID de la medida", required = true) @PathVariable("measurementId") @Valid @NotNull Long measurementId) {
                return observationService.getObservationsByMeasurementId(measurementId);
        }

        @PostMapping("/between-dates")
        @Operation(summary = "Obtener observaciones entre dos fechas", description = "Recuperar un listado de observaciones que se encuentran entre dos fechas específicas", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO de validación de las fechas entre las que buscar las observaciones", required = true, content = @Content(schema = @Schema(implementation = DatesBetweenDto.class))), responses = {
                        @ApiResponse(responseCode = "200", description = "Listado de observaciones", content = @Content(schema = @Schema(implementation = Observation.class))),
                        @ApiResponse(responseCode = "400", description = "Datos recibidos invalidos"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public List<Observation> getObservationsBetweenDates(@Valid @RequestBody DatesBetweenDto datesBetweenDto) {
                return observationService.getObservationsBetweenDates(datesBetweenDto);
        }

        @PutMapping("/{observationId}")
        @Operation(summary = "Actualizar una observación", description = "Actualizar detalles de una observación existente", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO de validación de los detalles a actualizar de la observación", required = true, content = @Content(schema = @Schema(implementation = ObservationRequest.class))), responses = {
                        @ApiResponse(responseCode = "200", description = "Observación actualizada con éxito", content = @Content(schema = @Schema(implementation = Observation.class))),
                        @ApiResponse(responseCode = "400", description = "Datos recibidos invalidos"),
                        @ApiResponse(responseCode = "404", description = "Observación no encontrada"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public Observation updateMeasurement(
                        @Parameter(description = "ID of the observation", required = true) @PathVariable("observationId") @Valid @NotNull Long observationId,
                        @Valid @RequestBody ObservationRequest observationRequest) {

                return observationService.updateObservation(observationId, observationRequest);
        }

        @DeleteMapping("/{observationId}")
        @Operation(summary = "Borrar una observación", description = "Borrar una observación a partir de la ID", responses = {
                        @ApiResponse(responseCode = "200", description = "Observación borrada con éxito"),
                        @ApiResponse(responseCode = "404", description = "Observación no encontrada"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public void deleteUser(
                        @Parameter(description = "ID de la observación", required = true) @PathVariable("observationId") @Valid @NotNull Long observationId) {
                observationService.deleteObservation(observationId);
        }

}
