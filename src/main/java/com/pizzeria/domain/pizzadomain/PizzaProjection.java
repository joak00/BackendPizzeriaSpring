package com.pizzeria.domain.pizzadomain;

import java.math.BigDecimal;
import java.util.UUID;

import com.pizzeria.domain.imagedomain.Image;



public interface PizzaProjection {
    
    public UUID getId();

	public String getName();

	public BigDecimal getPrice();

    public Image getImage();

}
