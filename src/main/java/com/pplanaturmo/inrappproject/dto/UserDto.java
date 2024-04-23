package com.pplanaturmo.inrappproject.dto;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    @NotNull
    @NumberFormat
    private Long userId;

    public UserDto(@PathVariable Long userId) {
        this.userId = userId;
    }
}
