package com.pizzeria.dto.commentdtos;

import javax.validation.constraints.NotBlank;
 
import org.springframework.validation.annotation.Validated;
 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Validated
@Getter @Setter @NoArgsConstructor public class CreateOrUpdateCommentDTO {
 
    @NotBlank
    private String text;
    @NotBlank
    private int rating;
}