package com.pplanaturmo.inrappproject.user.dtos;

import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDepartment {

    @NumberFormat
    @NotNull(message = "User id is required")
    @NotBlank(message = "User id is required")
    private Long userId;

    @NumberFormat
    @NotNull(message = "Departmentid is required")
    @NotBlank(message = "Departmentid is required")
    private Long departmentId;

}
