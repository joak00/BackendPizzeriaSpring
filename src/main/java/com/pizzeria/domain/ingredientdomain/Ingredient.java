package com.pizzeria.domain.ingredientdomain;

import java.util.UUID;  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter @NoArgsConstructor public class  Ingredient {
	@Id @Type (type="uuid-char")
	private UUID id;
	
    @Column(nullable = false, name ="name", unique = true)
    private  String name;
    
    @Column (nullable=false, name ="price")
    private Double price;
    
    @Override
	public boolean equals(Object obj)	{
		if (!(obj instanceof Ingredient)) {
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