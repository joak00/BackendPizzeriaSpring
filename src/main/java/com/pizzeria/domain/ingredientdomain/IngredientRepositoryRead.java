package com.pizzeria.domain.ingredientdomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientRepositoryRead {

	public Optional<Ingredient> findById(UUID id);
	public List<IngredientProjection> getAll (String name, int page, int size);
}
