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
	
	
	

}
