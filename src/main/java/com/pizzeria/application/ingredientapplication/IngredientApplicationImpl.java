package com.pizzeria.application.ingredientapplication;

import java.util.List; 
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.core.ApplicationBase;
import com.pizzeria.domain.ingredientdomain.Ingredient;
import com.pizzeria.domain.ingredientdomain.IngredientProjection;
import com.pizzeria.domain.ingredientdomain.IngredientRepository;
<<<<<<< HEAD

=======
>>>>>>> origin/JoaquinDev
import com.pizzeria.dtos.ingredientdto.CreateOrUpdateIngredientDTO;
import com.pizzeria.dtos.ingredientdto.IngredientDTO;

@Service
public class IngredientApplicationImpl extends ApplicationBase<Ingredient, UUID> implements IngredientApplication {
	
	private final IngredientRepository ingredientRepository;
	private final Logger log;
	private final ModelMapper modelMapper;
	
	@Autowired
	public IngredientApplicationImpl (final ModelMapper modelMapper, 
									  final Logger log, 
									  final IngredientRepository ingredientRepository ) {
		super((id)-> ingredientRepository.findById(id));
		
		this.ingredientRepository = ingredientRepository;
		this.log = log;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public IngredientDTO add(CreateOrUpdateIngredientDTO dto) {
		Ingredient ingredient = this.modelMapper.map(dto, Ingredient.class);
		ingredient.setId(UUID.randomUUID());
		ingredient.validate("name", ingredient.getName(), (name)->this.ingredientRepository.exists(name));
		this.ingredientRepository.add(ingredient);
		log.info(this.serializeObject(ingredient, " added."));
		return this.modelMapper.map(ingredient,IngredientDTO.class);  
	}
	
	@Override
	public IngredientDTO get(UUID id) {
		Ingredient ingredient = this.findById(id);
		return this.modelMapper.map(ingredient, IngredientDTO.class);
	}
	
	@Override
	public void update(UUID id, CreateOrUpdateIngredientDTO dto) {  
		Ingredient ingredient = modelMapper.map(dto, Ingredient.class);
		ingredient.setId(id);
		if(this.ingredientRepository.exists(ingredient.getName())) {
			ingredient.validate();
		} else {
			ingredient.validate("name", ingredient.getName(), (name)-> this.ingredientRepository.exists(name));
		}
		this.ingredientRepository.update(ingredient);
		log.info(this.serializeObject(ingredient, " updated."));
	}
	
	@Override
	public void delete(UUID id) {
		Ingredient ingredient = this.findById(id);
		this.ingredientRepository.delete(ingredient);
		log.info(this.serializeObject(ingredient, " deleted."));
	}
	
	@Override
	public List<IngredientProjection> getAll(String name, int page, int size) {
		return this.ingredientRepository.getAll(name, page, size);
	}
	
	private String serializeObject(Ingredient ingredient, String message){
        
        return String.format("Ingredient {id: %s, name: %s, price: %s} %s succesfully.",
                            ingredient.getId(), ingredient.getName(),
                            ingredient.getPrice().toString(),
                            message);
    }
	
}
