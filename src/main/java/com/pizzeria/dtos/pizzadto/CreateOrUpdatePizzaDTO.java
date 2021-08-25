package com.pizzeria.dtos.pizzadto;

import java.util.HashSet;
import java.util.UUID;

public class CreateOrUpdatePizzaDTO {
    
    public String name;
    public HashSet<UUID> ingredients;
   
}
