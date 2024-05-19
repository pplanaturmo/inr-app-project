package com.pplanaturmo.inrappproject.observation.dtos;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.pplanaturmo.inrappproject.observation.Observation.CauseEnum;
import com.pplanaturmo.inrappproject.professional.dtos.ProfessionalRequest.TypeEnumValidator;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO de validación de creación de observación")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObservationRequest {

    @Schema(description = "ID del usuario que crea la observación")
    @NumberFormat
    @NotNull(message = "User id is required")
    private Long userId;

    @Schema(description = "ID de la medida con la que se relaciona la observación")
    @NumberFormat
    private Long measurementId;

    @Schema(description = "Fecha de la observación")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotBlank(message = "Date value is required in format yyyy-MM-dd")
    private String date;

    @Schema(description = "Motivo de la observación")
    @NotNull(message = "Type is required")
    @CauseEnumConstraint(message = "Invalid type")
    private String cause;

    @Schema(description = "Descripción de la observación")
    @NotBlank(message = "Description  is required")
    private String description;

    @Target({ ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Constraint(validatedBy = TypeEnumValidator.class)
    public @interface CauseEnumConstraint {
        String message() default "Invalid cause";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    public class CauseEnumValidator implements ConstraintValidator<CauseEnumConstraint, String> {

        @Override
        public void initialize(CauseEnumConstraint constraintAnnotation) {
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null) {
                return false;
            }
            try {
                CauseEnum.valueOf(value.toUpperCase());
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
    }
}
