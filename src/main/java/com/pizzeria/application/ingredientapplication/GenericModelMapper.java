package com.pizzeria.application.ingredientapplication;

import org.modelmapper.ModelMapper;

import com.pizzeria.domain.ingredientdomain.Ingredient;
import com.pizzeria.dtos.ingredientdto.IngredientDTO;

public class GenericModelMapper {
	
	private final ModelMapper mapper = new ModelMapper();
	private static GenericModelMapper instance;
	
	private GenericModelMapper() {}
	
	public static GenericModelMapper singleInstance() {
		if (instance == null) {
			instance = new GenericModelMapper();
		}
		return instance;
	}
	
	public IngredientDTO mapToIngredientDTO(Ingredient ingredient) {
		return mapper.map(ingredient, IngredientDTO.class);
	}
}
