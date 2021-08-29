package com.pizzeria.dtos.commentdto;

import java.util.Date;
import java.util.UUID;

import com.pizzeria.domain.userdomain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor public class CommentDTO {
    private UUID id;

    private String text;

    private Date date;

    private int rating;

    private User user;
}