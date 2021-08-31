package com.pizzeria.application.commentapplication;

import java.util.UUID;

import com.pizzeria.dtos.commentdto.CommentDTO;
import com.pizzeria.dtos.commentdto.CreateOrUpdateCommentDTO;

public interface CommentApplication {
    public CommentDTO add(CreateOrUpdateCommentDTO dto);

    public CommentDTO get(UUID id);

    // public CommentDTO update(UUID id, CreateOrUpdateCommentDTO dto);

    // public void delete(UUID id);

    // public List<CommentProjection> getAll(String text,  int page, int size);
}
