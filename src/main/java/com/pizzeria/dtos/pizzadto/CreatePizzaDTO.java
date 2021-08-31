package com.pizzeria.dtos.pizzadto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
public @Getter @Setter class CreatePizzaDTO {

    @NotBlank
    public String name;

    @NotEmpty
    public Set<UUID> ingredients;


}