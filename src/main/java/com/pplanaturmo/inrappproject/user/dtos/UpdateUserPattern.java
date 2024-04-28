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
public class UpdateUserPattern {

     @NumberFormat
    @NotNull(message = "User id is required")
    @NotBlank(message = "User id is required")
    private Long userId;

    @NumberFormat
    @NotNull(message = "Pattern Id is required")
    @NotBlank(message = "Pattern Id is required")
    private Long patternId;

}
