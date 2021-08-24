package com.pizzeria.dtos.commentdtos;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

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
    @NotBlank
    private Date date;
    @NotBlank
    private int rating;
}