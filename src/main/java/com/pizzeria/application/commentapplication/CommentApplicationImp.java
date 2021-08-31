package com.pizzeria.application.commentapplication;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.core.ApplicationBase;
import com.pizzeria.domain.commentdomain.Comment;
import com.pizzeria.domain.commentdomain.CommentRepositoryWrite;
import com.pizzeria.dtos.commentdto.CreateOrUpdateCommentDTO;
import com.pizzeria.dtos.commentdto.CommentDTO;

@Service
public class CommentApplicationImp extends ApplicationBase<Comment, UUID> implements CommentApplication{
    private final CommentRepositoryWrite commentRepositoryWrite;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public CommentApplicationImp   (final CommentRepositoryWrite commentRepositoryWrite,
                                    final ModelMapper modelMapper,
                                    final Logger logger) {

        super((id) -> commentRepositoryWrite.findById(id));

        this.commentRepositoryWrite = commentRepositoryWrite;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public CommentDTO add(CreateOrUpdateCommentDTO dto) {

        Comment comment = modelMapper.map(dto, Comment.class);
        comment.setId(UUID.randomUUID());
        comment.setPizzaId(dto.getPizza());
        comment.setUserId(dto.getUser());
        comment.validate();

        this.commentRepositoryWrite.add(comment);
        logger.info(this.serializeObject(comment, "added"));

        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public CommentDTO get(UUID id) {

        Comment comment = this.findById(id);
        return this.modelMapper.map(comment, CommentDTO.class);
    }

    private String serializeObject(Comment comment, String message){
        
        return String.format("Comment {id: %s, text: %s, rating: %s, user: %s, pizza: %s} %s succesfully.",
                            comment.getId(), comment.getText(),
                            // comment.getDate().toString(), 
                            comment.getRating().toString(),
                            comment.getPizzaId().toString(), comment.getUserId().toString(),
                            message);
    }


}
