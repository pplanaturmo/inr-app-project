package com.pplanaturmo.inrappproject.dto;

import com.pplanaturmo.inrappproject.model.Role;
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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRole {

    @NotNull(message = "User id is required")
    private Long userId;

    @UserRoleValue(message = "Invalid role value")
    @NotBlank(message = "Role is required")
    private String assignedRole;

    @Constraint(validatedBy = UserRoleValidator.class)
    @Target({FIELD, PARAMETER})
    @Retention(RUNTIME)
    public @interface UserRoleValue {
        String message() default "Invalid role";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public static class UserRoleValidator implements ConstraintValidator<UserRoleValue, String> {
        @Override
        public void initialize(UserRoleValue constraintAnnotation) {}

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null) return false;
            for (Role.UserRole role : Role.UserRole.values()) {
                if (role.name().equalsIgnoreCase(value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
