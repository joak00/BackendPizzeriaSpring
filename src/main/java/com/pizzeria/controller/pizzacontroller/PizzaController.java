/*package com.pizzeria.controller.pizzacontroller;

import java.util.UUID;

import com.pizzeria.application.pizzaapplication.PizzaApplication;
import com.pizzeria.dtos.commentdtos.CommentDTO;
import com.pizzeria.dtos.commentdtos.CreateOrUpdateCommentDTO;
import com.pizzeria.dtos.pizzadto.CreateOrUpdatePizzaDTO;
import com.pizzeria.dtos.pizzadto.PizzaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/pizzas")
public class PizzaController {
    
    private final PizzaApplication pizzaApplication;

    @Autowired
    public PizzaController(final PizzaApplication pizzaApplication) {
        this.pizzaApplication = pizzaApplication;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody final CreateOrUpdatePizzaDTO dto) {
        PizzaDTO pizzaDTO = this.pizzaApplication.add(dto);
        
        return ResponseEntity.status(201).body(pizzaDTO);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{id}/comments")
    public ResponseEntity<?> addComment(@PathVariable UUID id, @RequestBody CreateOrUpdateCommentDTO createCommentDTO) {
        CommentDTO commentDTO = this.pizzaApplication.addComment(id, createCommentDTO);

        return ResponseEntity.status(201).body(commentDTO);
    }
}
*/