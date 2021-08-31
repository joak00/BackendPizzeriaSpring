package com.pizzeria.domain.pizzadomain;

import java.util.List;

public interface PizzaRepositoryRead  {
    
	public List<PizzaProjection> getAll (String name, int page, int size);
}
