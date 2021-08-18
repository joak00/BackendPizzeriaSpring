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
    
    @Override
	public boolean equals(Object obj)	{
		if (!(obj instanceof Entity)) {
			return false;
		}
		Ingredient tmp = (Ingredient)obj;
		return tmp.id.equals(this.id);
	}
	
	@Override
	public int hashCode() {
		return this.id.toString().hashCode();
	}
    
}