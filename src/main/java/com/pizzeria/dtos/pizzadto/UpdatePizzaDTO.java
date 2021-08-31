package com.pizzeria.dtos.pizzadto;

import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
public @Getter @Setter class UpdatePizzaDTO {
    
    @NotBlank
    public String name;

}
