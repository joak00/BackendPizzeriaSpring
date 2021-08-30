package com.pizzeria.infraestructure.commentinfraestructure;

import java.util.Optional;
import java.util.UUID;

import com.pizzeria.domain.commentdomain.Comment;
import com.pizzeria.domain.commentdomain.CommentRepositoryWrite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentJPARepositoryImp implements CommentRepositoryWrite{
    private final CommentJPARepository commentJPARepository;

    @Autowired
    public CommentJPARepositoryImp(final CommentJPARepository commentJPARepository) {
        this.commentJPARepository = commentJPARepository;
    }

    @Override
    public void add(Comment comment) {
        this.commentJPARepository.save(comment);
        //controlar excepción, no validar
    }

    @Override
    public Optional<Comment> findById(UUID id) {
        return this.commentJPARepository.findById(id);
    }

    // @Override
    // public void update(Comment comment) {
    //     this.commentJPARepository.save(comment);
    // }

    // @Override
    // public void delete(Comment comment) {
    //     this.commentJPARepository.save(comment);
    // }

    // @Override
    // public List<CommentProjection> getAll(String text, int page, int size) {
    //     return this.commentJPARepository.findByCriteria(
    //         text,
    //         PageRequest.of(page, size, Sort.by("text").descending()));
    // }

    @Override
    public boolean exists(String field) {
        return this.commentJPARepository.exists(field);
    }
}
