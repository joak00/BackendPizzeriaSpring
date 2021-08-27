package com.pizzeria.infraestructure.ingredientinfraestructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.pizzeria.domain.ingredientdomain.Ingredient;
import com.pizzeria.domain.ingredientdomain.IngredientProjection;
import com.pizzeria.domain.ingredientdomain.IngredientRepository;


@Repository
public class IngredientJPARepositoryImp implements IngredientRepository {

	private final IngredientJPARepository ingredientJPARepository;
	
	@Autowired
    public IngredientJPARepositoryImp(IngredientJPARepository ingredientJPARepository) {
        this.ingredientJPARepository = ingredientJPARepository;
    }
	
	@Override
	public void add(Ingredient ingredient) {
		this.ingredientJPARepository.save(ingredient);
	}

	@Override
	public void update(Ingredient ingredient) {
		this.ingredientJPARepository.save(ingredient);
	}

	@Override
	public void delete(Ingredient ingredient) {
		this.ingredientJPARepository.delete(ingredient);
	}

	@Override
	public Optional<Ingredient> findById(UUID id) {
		return this.ingredientJPARepository.findById(id);
	}

	@Override
	public List<IngredientProjection> getAll(String name, int page, int size) {
		return this.ingredientJPARepository.findByCriteria(name, 
				PageRequest.of(page, size, Sort.by("name").descending()));
	}

	@Override
    public boolean exists(String name) {
        return this.ingredientJPARepository.exists(name);
    }


}
