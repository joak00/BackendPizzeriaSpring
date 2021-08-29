package com.pizzeria.dtos.ingredientdto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;



import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Validated
@Getter @Setter @NoArgsConstructor public class CreateOrUpdateIngredientDTO  {
	@NotBlank
	private String name;
	@NotNull
	private BigDecimal price;
}
