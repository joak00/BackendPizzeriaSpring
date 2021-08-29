/*package com.pizzeria.infraestructure.pizzainfraestructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pizzeria.domain.pizzadomain.Pizza;
import com.pizzeria.domain.pizzadomain.PizzaIngredientProjection;
import com.pizzeria.domain.pizzadomain.PizzaProjection;
import com.pizzeria.domain.pizzadomain.PizzaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PizzaJPARepositoryImp implements PizzaRepository{

    private final PizzaJPARepository pizzaJPARepository;
   
   @Autowired
   public PizzaJPARepositoryImp(final PizzaJPARepository pizzaJPARepository) {
       this.pizzaJPARepository = pizzaJPARepository;
   }

   @Override
   public void add(Pizza pizza) {
       this.pizzaJPARepository.save(pizza);
   }

   @Override
   public Optional<Pizza> findById(UUID id) {
       return this.pizzaJPARepository.findById(id);
   }

   @Override
   public void update(Pizza pizza) {
       this.pizzaJPARepository.save(pizza);
   }

   @Override
   public void delete(Pizza pizza) {
       this.pizzaJPARepository.delete(pizza);
   }

   @Override
    public List<PizzaProjection> getAll(String name, int page, int size) {
       return this.pizzaJPARepository.findByCriteria(
           name,
           PageRequest.of(page, size, Sort.by("name").descending())
       );
    }

    @Override
    public PizzaIngredientProjection getPizzaInfo(UUID id) {
        return this.pizzaJPARepository.findByPizzaId(
           id
       );
    }
    
}*/
