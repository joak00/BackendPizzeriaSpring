package com.pizzeria.application.ingredientapplication;

import java.util.List; 
import java.util.UUID;

import org.modelmapper.ModelMapper;
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

	private final  ModelMapper modelMapper = new ModelMapper();
	private final IngredientRepositoryRead ingredientRepositoryRead;
	private final IngredientRepositoryWrite ingredientRepositoryWrite;
	
	@Autowired
	public IngredientApplicationImpl (final IngredientRepositoryRead ingredientRepositoryRead, final IngredientRepositoryWrite ingredientRepositoryWrite ) {
		this.ingredientRepositoryRead = ingredientRepositoryRead;
		this.ingredientRepositoryWrite = ingredientRepositoryWrite;
	}
	
	@Override
	public IngredientDTO add(CreateOrUpdateIngredientDTO dto) {
		IngredientDTO ingredientDTO = this.modelMapper.map(dto, IngredientDTO.class);
		ingredientDTO.setId(UUID.randomUUID());
		return ingredientDTO;
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
