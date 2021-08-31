package com.pizzeria.infraestructure.pizzainfraestructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pizzeria.domain.ingredientdomain.Ingredient;
import com.pizzeria.domain.pizzadomain.Pizza;
import com.pizzeria.domain.pizzadomain.PizzaProjection;
import com.pizzeria.domain.pizzadomain.PizzaRepository;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class PizzaJPARepositoryImp implements PizzaRepository {

    private PizzaJPARepository pizzaJPARepository;

    @Autowired
    public PizzaJPARepositoryImp(final PizzaJPARepository pizzaJPARepository){
        this.pizzaJPARepository = pizzaJPARepository;
    }

    @Override
    public void add(Pizza pizza) {
        this.pizzaJPARepository.save(pizza);
    }

    @Override
    public void update(Pizza pizza) {
        this.pizzaJPARepository.save(pizza);
    }

    @Override
    public Optional<Pizza> findById(UUID id) {
        return this.pizzaJPARepository.findById(id);
    }

    @Override
    public List<PizzaProjection> getAll(String name, int page, int size) {
        return this.pizzaJPARepository.findByCriteria(name, 
				PageRequest.of(page, size, Sort.by("name").descending()));
    }
 
    @Override
    public boolean exists(String name) {
        return this.pizzaJPARepository.exists(name);
    }

    @Override
    public void delete(Pizza pizza) {
        this.pizzaJPARepository.delete(pizza);
    }

}