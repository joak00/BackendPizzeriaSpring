package com.pizzeria.dtos.commentdto;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Validated
@Getter @Setter @NoArgsConstructor public class CreateOrUpdateCommentDTO {
 
    @NotBlank
    private String text;
    @NotNull
    private BigDecimal rating;
    @NotNull
    private UUID user;
    @NotNull
    private UUID pizza;
}