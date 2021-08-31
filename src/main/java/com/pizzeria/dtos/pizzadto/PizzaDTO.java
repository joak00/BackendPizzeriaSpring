package com.pizzeria.dtos.pizzadto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.pizzeria.domain.imagedomain.Image;
import com.pizzeria.domain.ingredientdomain.Ingredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class PizzaDTO {

    private UUID id;
    
    private String name;

    private Image image;

    private BigDecimal price;

    private Set<Ingredient> ingredients = new HashSet<Ingredient>();
}