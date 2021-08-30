package com.pizzeria.application.pizzaapplication;

import java.util.List;
import java.util.UUID;

import com.pizzeria.application.ingredientapplication.IngredientApplicationImpl;
import com.pizzeria.core.ApplicationBase;
import com.pizzeria.domain.ingredientdomain.Ingredient;
import com.pizzeria.domain.pizzadomain.Pizza;
import com.pizzeria.domain.pizzadomain.PizzaProjection;
import com.pizzeria.domain.pizzadomain.PizzaRepository;
import com.pizzeria.dtos.pizzadto.CreateOrUpdatePizzaDTO;
import com.pizzeria.dtos.pizzadto.PizzaDTO;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaApplicationImp extends ApplicationBase<Pizza, UUID> implements PizzaApplication {
    
    private final PizzaRepository pizzaRepository;
    private final IngredientApplicationImpl ingredientApplicationImpl;
    private final ModelMapper modelMapper;
    private final Logger logger;
    
    @Autowired
    public PizzaApplicationImp(final PizzaRepository pizzaRepository,
                                final IngredientApplicationImpl ingredientApplicationImpl, 
                                final ModelMapper modelMapper,
                                final Logger logger) {
        
        super((id) -> pizzaRepository.findById(id));
        
        this.modelMapper = modelMapper;
        this.pizzaRepository = pizzaRepository;
        this.ingredientApplicationImpl = ingredientApplicationImpl;
        this.logger = logger;
    }
    
    @Override
    public PizzaDTO add(CreateOrUpdatePizzaDTO dto) {
        Pizza pizza = this.modelMapper.map(dto, Pizza.class);
        pizza.setId(UUID.randomUUID());
        pizza.validate("name", pizza.getName(), (name)-> this.pizzaRepository.exists(name));
        for (UUID ingredientId : dto.getIngredients()) {
            Ingredient ingredient = this.modelMapper.map(ingredientApplicationImpl.get(ingredientId), Ingredient.class);
            pizza.addIngredient(ingredient);
        }
        pizza.setPrice(pizza.calculatePrice());
        this.pizzaRepository.add(pizza);
        this.logger.info(this.serializeObject(pizza, " added"));
        return this.modelMapper.map(pizza, PizzaDTO.class);
    }

    @Override
    public PizzaDTO get(UUID id) {
        Pizza pizza = this.findById(id);
        return this.modelMapper.map(pizza, PizzaDTO.class);
    }

    @Override
	public List<PizzaProjection> getAll(String name, int page, int size) {
		return this.pizzaRepository.getAll(name, page, size);
	}

    @Override
    public void update(UUID id, CreateOrUpdatePizzaDTO dto) {
        Pizza pizza = modelMapper.map(dto, Pizza.class);
		pizza.setId(id);
		if(this.pizzaRepository.exists(pizza.getName())) {
			pizza.validate();
		} else {
			pizza.validate("name", pizza.getName(), (name)-> this.pizzaRepository.exists(name));
		}
        for (UUID ingredientId : dto.getIngredients()) {
            Ingredient ingredient = this.modelMapper.map(ingredientApplicationImpl.get(ingredientId), Ingredient.class);
            pizza.addIngredient(ingredient);
        }
        pizza.setPrice(pizza.calculatePrice());
		this.pizzaRepository.update(pizza);
		logger.info(this.serializeObject(pizza, " updated"));
    }

    @Override
    public void delete(UUID id) {
        Pizza pizza = this.findById(id);
		this.pizzaRepository.delete(pizza);
		logger.info(this.serializeObject(pizza, " deleted"));
    }
    
    private String serializeObject(Pizza pizza, String message){
        return String.format("Pizza {id: %s, name: %s, price: %s} %s succesfully.",
                            pizza.getId(), pizza.getName(),
                            pizza.getPrice().toString(),
                            message);
    }

}