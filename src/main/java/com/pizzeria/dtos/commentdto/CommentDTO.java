package com.pizzeria.dtos.commentdto;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor public class CommentDTO implements Serializable{
    private UUID id;

    private String text;

    // private Date date;

    private BigDecimal rating;

    private UUID user;

    private UUID pizza;
}