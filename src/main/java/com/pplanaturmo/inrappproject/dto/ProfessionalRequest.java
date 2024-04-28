package com.pplanaturmo.inrappproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.pplanaturmo.inrappproject.professional.Professional.TypeEnum;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalRequest {

    @NotNull(message = "Register number is required")
    @NotBlank(message = "Register number is required")
    private String registerNumber;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Type is required")
    @TypeEnumConstraint(message = "Invalid type")
    private String type;

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Constraint(validatedBy = TypeEnumValidator.class)
    public @interface TypeEnumConstraint {
        String message() default "Invalid type";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public class TypeEnumValidator implements ConstraintValidator<TypeEnumConstraint, String> {

        @Override
        public void initialize(TypeEnumConstraint constraintAnnotation) {
        }
    
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null) {
                return false;
            }
            try {
                TypeEnum.valueOf(value.toUpperCase());
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
    }

}