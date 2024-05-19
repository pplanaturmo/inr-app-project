package com.pplanaturmo.inrappproject.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.pplanaturmo.inrappproject.role.Role;

import io.swagger.v3.oas.annotations.media.Schema;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Schema(description = "DTO de validación de actualización de roles del usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRole {

    @Schema(description = "ID del usuario a actualizar")
    @NotNull(message = "User id is required")
    private Long userId;

    @Schema(description = "Rol a asignar al usuario")
    @UserRoleValue(message = "Invalid role value")
    @NotBlank(message = "Role is required")
    private String assignedRole;

    @Constraint(validatedBy = UserRoleValidator.class)
    @Target({ FIELD, PARAMETER })
    @Retention(RUNTIME)
    public @interface UserRoleValue {
        String message() default "Invalid role";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    public static class UserRoleValidator implements ConstraintValidator<UserRoleValue, String> {
        @Override
        public void initialize(UserRoleValue constraintAnnotation) {
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null)
                return false;
            for (Role.UserRole role : Role.UserRole.values()) {
                if (role.name().equalsIgnoreCase(value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
