package com.pizzeria.dtos.ingredientdto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor public class CreateOrUpdateIngredientDTO {
	private String name;
	private BigDecimal price;
}
