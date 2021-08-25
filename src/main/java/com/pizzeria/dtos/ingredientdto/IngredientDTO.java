package com.pizzeria.dtos.ingredientdto;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor public class IngredientDTO {
	private UUID id;
	@NotBlank
	private String name;
	@NotNull
	private BigDecimal price;
	
}
