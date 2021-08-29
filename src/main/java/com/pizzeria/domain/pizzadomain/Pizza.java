package com.pizzeria.domain.pizzadomain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pizzeria.core.EntityBase;
import com.pizzeria.domain.commentdomain.Comment;
import com.pizzeria.domain.ingredientdomain.Ingredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizzas")
@Getter @Setter @NoArgsConstructor public class Pizza extends EntityBase{
    
    @NotBlank
    @Column(nullable = false, name ="name", unique = true)
    private  String name;
    
    
    /*private com.pizzeria.domain.imagedomain.FileEntity image;*/

    @NotNull
    @Column(nullable = false, name ="price")
    private BigDecimal price;

    @ManyToMany
    Set<Ingredient> ingredients = new HashSet<Ingredient>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pizza_id")
    Set<Comment> comments = new HashSet<Comment>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void removeIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
    }

    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    @Column(nullable = false, name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal calculatePrice() {

        BigDecimal total = new BigDecimal(0.0);
        BigDecimal addPrice = new BigDecimal(1.2);
        for (Ingredient ingredient : this.ingredients) {
            total = total.add(ingredient.getPrice());
        }
        total = total.multiply(addPrice);
        return total;
    }
    
}
