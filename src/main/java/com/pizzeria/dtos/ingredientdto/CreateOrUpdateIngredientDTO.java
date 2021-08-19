package com.pizzeria.dtos.ingredientdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor public class CreateOrUpdateIngredientDTO {
	private String name;
	private Double price;
}
