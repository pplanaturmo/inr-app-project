package com.pplanaturmo.inrappproject.dosePattern;

import com.pplanaturmo.inrappproject.rangeInr.RangeInr;
import com.pplanaturmo.inrappproject.rangeInr.RangeInrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Control de Patrones de Dosificación", description = "Operaciones relacionadas con el control de Patrones de Dosificación ")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/api/dose-pattern")
public class DosePatternController {

    @Autowired
    private DosePatternService dosePatternService;

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los patrones de dosificación", description = "Recuperar una lista con todos los patrones de dosificación", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de patrones de dosificación recuperada con éxito", content = @Content(schema = @Schema(implementation = RangeInr.class))),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public List<DosePattern> get() {
        return dosePatternService.getAllDosePatterns();
    }
}