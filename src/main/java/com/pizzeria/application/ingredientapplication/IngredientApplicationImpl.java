package com.pizzeria.application.ingredientapplication;

import java.util.List; 
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.domain.ingredientdomain.Ingredient;
import com.pizzeria.domain.ingredientdomain.IngredientProjection;
import com.pizzeria.domain.ingredientdomain.IngredientRepository;

import com.pizzeria.dtos.ingredientdto.CreateOrUpdateIngredientDTO;
import com.pizzeria.dtos.ingredientdto.IngredientDTO;

@Service
public class IngredientApplicationImpl  implements IngredientApplication {

	private final ModelMapper modelMapper = new ModelMapper();
	private final IngredientRepository ingredientRepository;
	private final Logger log;
	
	@Autowired
	public IngredientApplicationImpl (final Logger log, final IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
		this.log = log;
	}
	
	@Override
    public IngredientDTO add(CreateOrUpdateIngredientDTO dto) {
        Ingredient  ingredient = this.modelMapper.map(dto, Ingredient.class);
        ingredient.setId(UUID.randomUUID());
		//TODO: Validar que la pizza no existe(nombre no duplicado) | select count (*) from ingredientes where name = ?
		//If count > 0 = error. 
        ingredient.validate();
		this.ingredientRepository.add(ingredient);
		//log OK
		log.info("Ingrediente: "+ingredient.getName() +" añadido. ");
        return this.modelMapper.map(ingredient,IngredientDTO.class);  
    }

	@Override
	public IngredientDTO get(UUID id) {
		Ingredient ingredient = this.ingredientRepository.findById(id).orElseThrow();
		IngredientDTO ingredientDTO = this.modelMapper.map(ingredient, IngredientDTO.class);
		return ingredientDTO;
	}

	@Override
	public void update(UUID id, CreateOrUpdateIngredientDTO dto) {  
		Ingredient ingredient = this.modelMapper.map(dto, Ingredient.class);
		ingredient.setId(id);
		ingredient.validate();
		this.ingredientRepository.update(ingredient);
	}


	@Override
	public void delete(UUID id) {
		Ingredient ingredient = this.ingredientRepository.findById(id).orElseThrow();
		this.ingredientRepository.delete(ingredient);
	}

	@Override
	public List<IngredientProjection> getAll(String name, int page, int size) {
		return this.ingredientRepository.getAll(name, page, size);
	}
	

}
