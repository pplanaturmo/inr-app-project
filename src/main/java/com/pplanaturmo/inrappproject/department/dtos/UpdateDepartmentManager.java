package com.pplanaturmo.inrappproject.department.dtos;

import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDepartmentManager {

    @NumberFormat
    @NotNull(message = "User id is required")
    @NotBlank(message = "User id is required")
    private Long userId;

    @NumberFormat
    @NotNull(message = "Manager id is required")
    @NotBlank(message = "Manager id is required")
    private Long managerId;

}
