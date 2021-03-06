package com.pizzeria.domain.ingredientdomain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pizzeria.core.EntityBase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "ingredients")
@Getter @Setter @NoArgsConstructor public class  Ingredient extends EntityBase {
	
	
    @NotBlank
    @Column(nullable = false, name ="name", unique = true)
    private  String name;
    
    @NotNull @Digits(integer = 3, fraction = 2) @DecimalMin(value = "0.0", inclusive = false)
    @Column (nullable=false, name ="price")
    private BigDecimal price;


    
}