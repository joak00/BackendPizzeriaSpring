package com.pizzeria.domain.pizzadomain;

import java.util.Optional;
import java.util.UUID;

import com.pizzeria.core.functionalinterfaces.ExistsByField;
import com.pizzeria.core.functionalinterfaces.FindById;

public interface PizzaRepositoryWrite extends FindById<Pizza, UUID>, ExistsByField{
   
    public void add(Pizza pizza);

    public void update(Pizza pizza);

	public void delete(Pizza pizza);

    public Optional<Pizza> findById(UUID id);
}