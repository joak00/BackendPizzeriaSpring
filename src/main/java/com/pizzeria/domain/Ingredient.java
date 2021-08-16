package com.pizzeria.domain;

import javax.persistence.*;
import lombok.NoArgsConstructor;


@Entity
public @NoArgsConstructor class  Ingredient {

    public @Id @GeneratedValue Long id;
    public @Column(nullable = false) String name;
    public @ManyToMany(mappedBy = "ingredients") Set <Pizza> pizzas = new HashSet<>();

}