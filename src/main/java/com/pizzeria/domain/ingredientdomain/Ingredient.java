package com.pizzeria.domain.ingredientdomain;


import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Type;


@Entity
public class  Ingredient {
	@Id @Type (type="uuid-char")
    public UUID id;
	
    @Column(nullable = false, name ="name", unique = true)
    public  String name;
    
    @Column (nullable=false, name ="price")
    public Double price;
    
}