package com.pizzeria.application.ingredientapplication;

import java.util.List; 
import java.util.UUID;

import com.pizzeria.domain.ingredientdomain.IngredientProjection;
import com.pizzeria.dtos.ingredientdto.CreateOrUpdateIngredientDTO;
import com.pizzeria.dtos.ingredientdto.IngredientDTO;

public interface IngredientApplication {
	
	public IngredientDTO add(CreateOrUpdateIngredientDTO dto);
	public IngredientDTO get (UUID id);
	public void update (UUID id, CreateOrUpdateIngredientDTO dto);
	public void delete (UUID id);
	public List<IngredientProjection> getAll(String name, int page, int size);
	
}
