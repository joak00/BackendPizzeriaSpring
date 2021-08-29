package com.pizzeria.dtos.ingredientdto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor public class IngredientDTO {
	private UUID id;

	private String name;

	private BigDecimal price;
	
}
