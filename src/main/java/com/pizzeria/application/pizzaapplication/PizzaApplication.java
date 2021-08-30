package com.pizzeria.application.pizzaapplication;

import java.util.List;
import java.util.UUID;

import com.pizzeria.domain.pizzadomain.PizzaProjection;
import com.pizzeria.dtos.pizzadto.CreateOrUpdatePizzaDTO;
import com.pizzeria.dtos.pizzadto.PizzaDTO;

public interface PizzaApplication {
    public PizzaDTO add(CreateOrUpdatePizzaDTO dto);
    public PizzaDTO get(UUID id);
    public void update (UUID id, CreateOrUpdatePizzaDTO dto);
	public void delete (UUID id);
    public List<PizzaProjection> getAll(String name, int page, int size);

}
