
package com.pplanaturmo.inrappproject.alerts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.pplanaturmo.inrappproject.alerts.dtos.AlertResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@Tag(name = "Control de Alertas", description = "Operaciones relacionadas con manejo de alertas")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE })
@RequestMapping("/api/alerts")
public class AlertController {

        @Autowired
        private AlertService alertService;

        @GetMapping("/all")
        @Operation(summary = "Obtener listado de alertas", description = "Este endpoint permite la obtención de un listado con todas las alertas", responses = {
                        @ApiResponse(responseCode = "200", description = "Listado de alertas", content = @Content(schema = @Schema(implementation = AlertResponse.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public List<AlertResponse> getAllAlerts() {
                List<Alert> alerts = alertService.getAllAlerts();
                return alertService.convertToAlertResponseList(alerts);
        }

        @GetMapping("/pending")
        @Operation(summary = "Obtener listado de alertas pendientes", description = "Este endpoint permite la obtención de un listado con todas las alertas pendientes de revisión", responses = {
                        @ApiResponse(responseCode = "200", description = "Listado de alertas pendientes", content = @Content(schema = @Schema(implementation = AlertResponse.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public List<AlertResponse> getAllPending() {
                List<Alert> alerts = alertService.getAllAlerts();
                return alertService.convertToPendingList(alerts);
        }

        @GetMapping("/{alertId}")
        @Operation(summary = "Obtener alerta por ID", description = "Recuperar una alerta por su identificador único", responses = {
                        @ApiResponse(responseCode = "200", description = "Detalles de la alerta", content = @Content(schema = @Schema(implementation = AlertResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Alerta no encontrada"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public AlertResponse getAlertById(
                        @Parameter(description = "ID de la alerta a recuperar", required = true) @PathVariable("alertId") @Valid @NotNull Long alertId) {
                Alert alert = alertService.getAlertById(alertId);
                return alertService.convertToResponse(alert);
        }

        @GetMapping("/department/{departmentId}")
        @Operation(summary = "Obtener alertas por departamento", description = "Este endpoint permite la obtención de un listado de alertas por departamento", responses = {
                        @ApiResponse(responseCode = "200", description = "Listado de alertas del departamento", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AlertResponse.class)))),
                        @ApiResponse(responseCode = "404", description = "Departamento no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public List<AlertResponse> getAlertsByDepartmentId(
                        @Parameter(description = "ID del departamento a recuperar alertas", required = true) @PathVariable("departmentId") @Valid @NotNull Long departmentId) {

                List<Alert> alerts = alertService.getAlertsByDepartment(departmentId);
                return alertService.convertToAlertResponseList(alerts);
        }

        @GetMapping("/professional/{professionalId}")
        @Operation(summary = "Obtener alertas por profesional", description = "Este endpoint permite la obtención de un listado de alertas por profesional", responses = {
                        @ApiResponse(responseCode = "200", description = "Listado de alertas del profesional", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AlertResponse.class)))),
                        @ApiResponse(responseCode = "404", description = "Profesional no encontrado"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public List<AlertResponse> getAlertsByProfessionalId(
                        @Parameter(description = "ID del profesional a recuperar alertas", required = true) @PathVariable("professionalId") @Valid @NotNull Long professionalId) {
                List<Alert> alerts = alertService.getAlertsByProfessional(professionalId);
                return alertService.convertToAlertResponseList(alerts);
        }

        @DeleteMapping("/{alertId}")
        @Operation(summary = "Eliminar alerta", description = "Eliminar una alerta por su identificador único", responses = {
                        @ApiResponse(responseCode = "200", description = "Alerta eliminada correctamente"),
                        @ApiResponse(responseCode = "404", description = "Alerta no encontrada"),
                        @ApiResponse(responseCode = "500", description = "Error interno de servidor")
        })
        public void deleteAlert(
                        @Parameter(description = "ID de la alerta a eliminar", required = true) @PathVariable("alertId") @Valid @NotNull Long alertId) {
                alertService.deleteAlertById(alertId);
        }
}
