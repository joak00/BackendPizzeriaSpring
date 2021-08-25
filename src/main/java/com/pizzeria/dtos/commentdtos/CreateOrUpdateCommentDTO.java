package com.pizzeria.dtos.commentdtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pizzeria.domain.userdomain.User;

import org.springframework.validation.annotation.Validated;
 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Validated
@Getter @Setter @NoArgsConstructor public class CreateOrUpdateCommentDTO {
 
    @NotBlank
    private String text;
    @NotNull
    private int rating;
    
    private User user;
}