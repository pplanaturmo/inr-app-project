package com.pplanaturmo.inrappproject.dto;

import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProffesionalDto {

    @NotNull
    @NotBlank
    private Long proffesionalId;

    public ProffesionalDto(@PathVariable Long proffesionalId) {
        this.proffesionalId = proffesionalId;
    }
}
