package com.pizzeria.domain.commentdomain;

import java.util.Optional;
import java.util.UUID;

import com.pizzeria.core.functionalinterfaces.ExistsByField;
import com.pizzeria.core.functionalinterfaces.FindById;

public interface CommentRepositoryWrite extends FindById<Comment, UUID>, ExistsByField{
    public void add(Comment comment);

    public Optional<Comment> findById(UUID id);

    // public void update(Comment comment);

    // public void delete(Comment comment);
}
