package com.pizzeria.domain.ingredientdomain;

import java.util.Optional;
import java.util.UUID;

import com.pizzeria.core.functionalinterfaces.ExistsByField;
import com.pizzeria.core.functionalinterfaces.FindById;

public interface IngredientRepositoryWrite extends FindById<Ingredient, UUID>, ExistsByField {

	public void add(Ingredient ingredient);

	public void update(Ingredient ingredient);

	public void delete(Ingredient ingredient);

	public Optional<Ingredient> findById(UUID id);

}
