package com.pizzeria.domain.pizzadomain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import com.pizzeria.core.EntityBase;
import com.pizzeria.domain.imagedomain.Image;
import com.pizzeria.domain.ingredientdomain.Ingredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizzas")
@Getter @Setter @NoArgsConstructor public class Pizza extends EntityBase {
    
    @NotBlank
    @Column(nullable = false, name ="name", unique = true)
    private  String name;
    
    /*@Embedded
    @Column(name = "image")
    private Image image;*/

    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, name ="price")
    private BigDecimal price;

    @ManyToMany @JoinTable(name ="pizza_ingredients", 
    joinColumns = @JoinColumn(name="pizza_id"), 
    inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private final Set<Ingredient> ingredients = new HashSet<Ingredient>();
 
 
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
    }
    
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, name = "price")
    public BigDecimal getPrice() {
        return price;
    }

   
    @DecimalMin(value = "0.0", inclusive = false)
    public BigDecimal calculatePrice() {
        BigDecimal total = new BigDecimal(0.0);
        BigDecimal addPrice = new BigDecimal(1.2);
        for (Ingredient ingredient : this.ingredients) {
            total = total.add(ingredient.getPrice());
        }
        total = total.multiply(addPrice);        
        return total.setScale(2, RoundingMode.HALF_EVEN);
    }
    
}
