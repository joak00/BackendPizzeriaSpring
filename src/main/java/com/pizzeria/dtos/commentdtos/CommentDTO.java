package com.pizzeria.dtos.commentdtos;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pizzeria.domain.userdomain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private UUID id;
    @NotBlank
    private String text;
    @NotNull
    private Date date;
    @NotNull
    private int rating;
    private User user;
}