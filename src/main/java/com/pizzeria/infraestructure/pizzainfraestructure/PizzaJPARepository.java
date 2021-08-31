package com.pizzeria.infraestructure.pizzainfraestructure;

import java.util.List;
import java.util.UUID;

import com.pizzeria.domain.pizzadomain.Pizza;
import com.pizzeria.domain.pizzadomain.PizzaIngredientProjection;
import com.pizzeria.domain.pizzadomain.PizzaProjection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PizzaJPARepository extends JpaRepository<Pizza, UUID> {
    @Query("""
    SELECT p.id as id, p.name as name, p.price as price 
    FROM Pizza p 
    WHERE (:name is NULL OR name LIKE %:name%)""")
    List<PizzaProjection>  findByCriteria(@Param("name") String name, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(p)>0 THEN true ELSE false END FROM Pizza p WHERE p.name = :name")
    boolean exists(@Param("name") String name);

    @Query("SELECT p FROM Pizza p WHERE id = :id")
    PizzaIngredientProjection findByPizzaId(
        @Param("id") UUID id
    );
}