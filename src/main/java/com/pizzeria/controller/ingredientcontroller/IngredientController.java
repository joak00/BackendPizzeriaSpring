package com.pizzeria.controller.ingredientcontroller;

import com.pizzeria.application.ingredientapplication.IngredientApplication;
import com.pizzeria.dtos.ingredientdto.CreateOrUpdateIngredientDTO;
import com.pizzeria.dtos.ingredientdto.IngredientDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.pizzeria.application.ingredientapplication.IngredientApplication;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/ingredients")
public class IngredientController {
	
	private final IngredientApplication ingredientApplication;
	
	@Autowired
	public IngredientController(final IngredientApplication ingredientApplication) {
		this.ingredientApplication = ingredientApplication;
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody final CreateOrUpdateIngredientDTO dto) {
        IngredientDTO ingredientDTO = this.ingredientApplication.add(dto);
        return ResponseEntity.status(201).body(ingredientDTO);
    }
	
}
