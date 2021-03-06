package com.pizzeria.domain.ingredientdomain;

import java.math.BigDecimal;
import java.util.UUID;

public interface IngredientProjection {

	public UUID getId();

	public String getName();

	public BigDecimal getPrice();

}
