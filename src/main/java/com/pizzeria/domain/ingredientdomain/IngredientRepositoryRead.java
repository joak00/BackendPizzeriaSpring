package com.pizzeria.domain.ingredientdomain;

import java.util.List;

public interface IngredientRepositoryRead {

	public List<IngredientProjection> getAll (String name, int page, int size);
	
}
