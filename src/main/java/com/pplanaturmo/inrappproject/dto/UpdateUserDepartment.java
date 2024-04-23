package com.pplanaturmo.inrappproject.dto;

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
    @NotNull
    @NotBlank(message = "User id is required")
    private Long userId;

    @NumberFormat
    @NotNull
    @NotBlank(message = "Departmentid is required")
    private Long departmentId;

}
