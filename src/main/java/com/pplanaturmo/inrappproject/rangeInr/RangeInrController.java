package com.pplanaturmo.inrappproject.rangeInr;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Controlador  de Rangos INR", description = "Operaciones relacionadas con el control de Rangos INR ")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/api/range-inr")
public class RangeInrController {

    @Autowired
    private RangeInrService rangeInrService;

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los rangos de INR", description = "Recuperar una lista con todos los Rangos disponibles de INR", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de Rangos INR recuperada con éxito", content = @Content(schema = @Schema(implementation = RangeInr.class))),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public List<RangeInr> getAllRangeInrs() {
        return rangeInrService.getAllRangeInrs();
    }
}