package com.pizzeria.infraestructure.ingredientinfraestructure;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pizzeria.domain.ingredientdomain.Ingredient;
import com.pizzeria.domain.ingredientdomain.IngredientProjection;

public interface IngredientJPARepository extends JpaRepository<Ingredient, UUID>{
	@Query("SELECT id, name, price FROM Ingredient WHERE (:name is NULL OR name LIKE %:name%)")
	List<IngredientProjection> findByName(@Param("name") String name, Pageable pageable);
}
