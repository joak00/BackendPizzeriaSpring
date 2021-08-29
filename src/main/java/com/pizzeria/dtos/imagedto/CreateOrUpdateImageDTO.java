package com.pizzeria.dtos.imagedto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class CreateOrUpdateImageDTO {
    
    @NotNull
    public byte[] data;
}
