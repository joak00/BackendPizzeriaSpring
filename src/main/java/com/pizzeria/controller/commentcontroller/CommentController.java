package com.pizzeria.controller.commentcontroller;

import java.util.UUID;

import javax.validation.Valid;

import com.pizzeria.application.commentapplication.CommentApplication;
import com.pizzeria.dtos.commentdto.CreateOrUpdateCommentDTO;
import com.pizzeria.dtos.commentdto.CommentDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    private final CommentApplication commentApplication;

    @Autowired
    public CommentController(final CommentApplication commentApplication){
        this.commentApplication = commentApplication;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody CreateOrUpdateCommentDTO dto){
        CommentDTO commentDTO = this.commentApplication.add(dto);

        return ResponseEntity.status(201).body(commentDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,  path = "/{id}")
    public ResponseEntity<?> get(@Valid @PathVariable UUID id) {
        CommentDTO commentDTO = this.commentApplication.get(id);
        return ResponseEntity.ok(commentDTO);
    }
}