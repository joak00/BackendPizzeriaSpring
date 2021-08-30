package com.pizzeria.domain.pizzadomain;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface PizzaIngredientProjection {
    public UUID getId();

    public String getName();

    public BigDecimal getPrice();

    public List<Ingredient> getIngredients();

    public Image getImage();

    public interface Image {
        public String getPublic_id();
    };

    public interface Ingredient {

        public UUID getId();

        public String getName();
    }

}