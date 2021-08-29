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
	@Query("""
	SELECT i.id as id, i.name as name, i.price as price 
	FROM Ingredient i 
	WHERE (:name is NULL OR name LIKE %:name%)""")
	List<IngredientProjection> findByCriteria(@Param("name") String name, Pageable pageable);

	@Query("SELECT CASE WHEN COUNT(i)>0 THEN true ELSE false END FROM Ingredient i WHERE i.name = :name")
	boolean exists(@Param("name")String name);
}
