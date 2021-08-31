package com.pizzeria.application.pizzaapplication;

import java.util.List;
import java.util.UUID;

import com.pizzeria.domain.pizzadomain.PizzaProjection;
import com.pizzeria.dtos.pizzadto.CreatePizzaDTO;
import com.pizzeria.dtos.pizzadto.PizzaDTO;
import com.pizzeria.dtos.pizzadto.UpdatePizzaDTO;

public interface PizzaApplication {
    public PizzaDTO add(CreatePizzaDTO dto);
    public PizzaDTO get(UUID id);
    public PizzaDTO update (UUID id, UpdatePizzaDTO dto);
	public void delete (UUID id);
    public List<PizzaProjection> getAll(String name, int page, int size);

}
