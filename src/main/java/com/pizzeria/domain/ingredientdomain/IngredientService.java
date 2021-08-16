package com.pizzeria.domain.ingredientdomain;

import java.util.UUID;

import dtos.ingredientdto.CreateOrUpdateIngredientDTO;
import dtos.ingredientdto.IngredientDTO;

public class IngredientService {

	public static Ingredient create(CreateOrUpdateIngredientDTO dto) {
		Ingredient ingredient = new Ingredient();
		ingredient.id = UUID.randomUUID();
		ingredient.name = dto.name;
		ingredient.price = dto.price;
		return ingredient;
	}
	
	public static IngredientDTO createDTO(Ingredient ingredient) {
		IngredientDTO ingredientDTO = new IngredientDTO();
		ingredientDTO.id = ingredient.id;
		ingredientDTO.name = ingredient.name;
		ingredientDTO.price = ingredient.price;
		return ingredientDTO;
	}
}
