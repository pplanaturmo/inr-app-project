package com.pplanaturmo.inrappproject.auth;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.auth.dtos.AuthenticatedUserResponse;
import com.pplanaturmo.inrappproject.auth.dtos.AuthenticationRequest;
import com.pplanaturmo.inrappproject.user.dtos.UserRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Operaciones relacionadas con la autenticación de usuarios")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Registrar y autenticar un nuevo usuario", description = "Registrar y autenticar un nuevo usuario a partir de los datos enviados", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Detalles del usuario a registrar", required = true, content = @Content(schema = @Schema(implementation = UserRequest.class))), responses = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente", content = @Content(schema = @Schema(implementation = AuthenticatedUserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos recibidos no validos"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public ResponseEntity<AuthenticatedUserResponse> register(
            @Parameter(description = "Detalles del usuario para el registro", required = true) @RequestBody UserRequest userRequest) {

        return ResponseEntity.ok(authenticationService.register(userRequest));
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Autenticar usuario", description = "Authenticate a user by providing the necessary credentials.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Credentials for authentication", required = true, content = @Content(schema = @Schema(implementation = AuthenticationRequest.class))), responses = {
            @ApiResponse(responseCode = "200", description = "Usuario autenticado correctamente", content = @Content(schema = @Schema(implementation = AuthenticatedUserResponse.class))),
            @ApiResponse(responseCode = "401", description = "Credenciales no validas"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public ResponseEntity<AuthenticatedUserResponse> authenticate(
            @Parameter(description = "Petición de autenticación que contiene las credenciales del usuario", required = true) @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "Refresh authentication token", description = "Refresh the authentication token by providing the current token.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "HTTP request containing the current token", required = true, content = @Content(schema = @Schema(implementation = HttpServletRequest.class))), responses = {
            @ApiResponse(responseCode = "200", description = "Token actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Token  invalido"),
            @ApiResponse(responseCode = "500", description = "Error interno de servidor")
    })
    public void refreshToken(
            @Parameter(description = "Petición HTTP que contiene el token actual", required = true) HttpServletRequest request,
            @Parameter(description = "Respuesta HTTP a actualizar con el nuevo token", required = true) HttpServletResponse response)
            throws IOException {
        authenticationService.refreshToken(request, response);
    }

}