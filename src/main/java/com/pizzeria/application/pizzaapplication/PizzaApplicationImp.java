package com.pizzeria.application.pizzaapplication;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.pizzeria.domain.commentdomain.Comment;
import com.pizzeria.domain.ingredientdomain.Ingredient;
import com.pizzeria.domain.ingredientdomain.IngredientRepositoryRead;
import com.pizzeria.domain.pizzadomain.Pizza;
import com.pizzeria.domain.pizzadomain.PizzaIngredientProjection;
import com.pizzeria.domain.pizzadomain.PizzaProjection;
import com.pizzeria.domain.pizzadomain.PizzaRepository;
import com.pizzeria.dtos.commentdtos.CommentDTO;
import com.pizzeria.dtos.pizzadto.CreateOrUpdatePizzaDTO;
import com.pizzeria.dtos.pizzadto.PizzaDTO;

import org.springframework.beans.factory.annotation.Autowired;

public class PizzaApplicationImp implements PizzaApplication{
    
    private final PizzaRepository pizzaRepository;
    private final IngredientRepositoryRead ingredientRepository;

    @Autowired
    public PizzaApplicationImp(final PizzaRepository pizzaRepository, final IngredientRepository ingredientRepository) {
        this.pizzaRepository = pizzaRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public PizzaDTO add(CreateOrUpdatePizzaDTO pizzadto) {
        Pizza pizza = PizzaService.create(pizzadto);
        for (UUID ingredientId : pizzadto.ingredients) {
            Ingredient ingredient = this.ingredientRepository.findById(ingredientId).orElseThrow();
            pizza.addIngredient(ingredient);
        }
        BigDecimal price = pizza.calculatePrice();
        pizza.setPrice(price);
        this.pizzaRepository.add(pizza);
        return PizzaService.createDTO(pizza);
    }

    @Override
    public PizzaDTO get(UUID id) {
        Pizza pizza = this.pizzaRepository.findById(id).orElseThrow();
        return PizzaService.createDTO(pizza);
    }

    @Override
    public void update(UUID id, CreateOrUpdatePizzaDTO dto) {
        Pizza pizza = this.pizzaRepository.findById(id).orElseThrow();
        pizza.name = dto.name;
        this.pizzaRepository.update(pizza);
    }

    @Override
    public void delete(UUID id) {
        Pizza pizza = this.pizzaRepository.findById(id).orElseThrow();
        this.pizzaRepository.delete(pizza);
    }

    @Override
    public CommentDTO addComment(UUID pizzaId, CreateCommentDTO commentdto) {
        Pizza pizza = this.pizzaRepository.findById(pizzaId).orElseThrow();
        Comment comment = CommentService.create(commentdto);
        pizza.addComment(comment);
        this.pizzaRepository.update(pizza);
        return CommentService.createDTO(comment);
    }

    @Override
    public void removeIngredient(UUID id, UUID ingredientId) {
        Ingredient ingredient = this.ingredientRepository.findById(ingredientId).orElseThrow();
        Pizza pizza = this.pizzaRepository.findById(id).orElseThrow();
        pizza.removeIngredient(ingredient);
        this.pizzaRepository.update(pizza);
    }

    @Override
    public PizzaDTO addIngredient(UUID id, UUID ingredientId) {
        Ingredient ingredient = this.ingredientRepository.findById(ingredientId).orElseThrow();
        Pizza pizza = this.pizzaRepository.findById(id).orElseThrow();
        pizza.addIngredient(ingredient);
        this.pizzaRepository.update(pizza);
        return PizzaService.createDTO(pizza);
    }

    public List<PizzaProjection> getAll(String name, int page, int size) {
        return this.pizzaRepository.getAll(name, page, size);
    }

    @Override
    public PizzaIngredientProjection getPizzaInfo(UUID id) {
        return this.pizzaRepository.getPizzaInfo(id);
    }
}
