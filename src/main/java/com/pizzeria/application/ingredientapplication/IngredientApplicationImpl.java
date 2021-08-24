package com.pizzeria.application.ingredientapplication;

import java.util.List; 
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.domain.ingredientdomain.Ingredient;
import com.pizzeria.domain.ingredientdomain.IngredientProjection;
import com.pizzeria.domain.ingredientdomain.IngredientRepositoryRead;
import com.pizzeria.domain.ingredientdomain.IngredientRepositoryWrite;
import com.pizzeria.dtos.ingredientdto.CreateOrUpdateIngredientDTO;
import com.pizzeria.dtos.ingredientdto.IngredientDTO;

@Service
public class IngredientApplicationImpl  implements IngredientApplication {

	private final ModelMapper modelMapper = new ModelMapper();
	private final IngredientRepositoryRead ingredientRepositoryRead;
	private final IngredientRepositoryWrite ingredientRepositoryWrite;
	private final Logger log;
	
	@Autowired
	public IngredientApplicationImpl (final Logger log, final IngredientRepositoryRead ingredientRepositoryRead, final IngredientRepositoryWrite ingredientRepositoryWrite ) {
		this.ingredientRepositoryRead = ingredientRepositoryRead;
		this.ingredientRepositoryWrite = ingredientRepositoryWrite;
		this.log = log;
	}
	
	@Override
    public IngredientDTO add(CreateOrUpdateIngredientDTO dto) {
        Ingredient  ingredient = this.modelMapper.map(dto, Ingredient.class);
        ingredient.setId(UUID.randomUUID());
		//TODO: Validar que la pizza no existe(nombre no duplicado) | select count (*) from ingredientes where name = ?
		//If count > 0 = error. 
        ingredient.validate();
		this.ingredientRepositoryWrite.add(ingredient);
		//log OK
		log.info("Ingrediente: "+ingredient.getName() +" añadido. ");
        return this.modelMapper.map(ingredient,IngredientDTO.class);  
    }

	@Override
	public IngredientDTO get(UUID id) {
		Ingredient ingredient = this.ingredientRepositoryRead.findById(id).orElseThrow();
		IngredientDTO ingredientDTO = this.modelMapper.map(ingredient, IngredientDTO.class);
		return ingredientDTO;
	}

	@Override
	public void update(UUID id, CreateOrUpdateIngredientDTO dto) {
		Ingredient ingredient = this.ingredientRepositoryRead.findById(id).orElseThrow();
		ingredient.setName(dto.getName());
		ingredient.setPrice(dto.getPrice());
		ingredient.validate(); //No estoy seguro
		this.ingredientRepositoryWrite.update(ingredient);
	}


	@Override
	public void delete(UUID id) {
		Ingredient ingredient = this.ingredientRepositoryRead.findById(id).orElseThrow();
		this.ingredientRepositoryWrite.delete(ingredient);
	}

	@Override
	public List<IngredientProjection> getAll(String name, int page, int size) {
		return this.ingredientRepositoryRead.getAll(name, page, size);
	}
	

}
