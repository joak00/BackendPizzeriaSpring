package com.pizzeria.domain.pizzadomain;

import java.util.Optional;

public class PizzaRepository {
    
    public void add(Pizza pizza);
    public Optional<Pizza> findById(UUID id);
    public void update(Pizza pizza);
    public void delete(Pizza pizza);
    public List<PizzaProjection> getAll(String name, int page, int size);
    public PizzaIngredientProjection getPizzaInfo(UUID id);
}
