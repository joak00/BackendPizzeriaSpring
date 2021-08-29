package com.pizzeria.dtos.imagedto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor  class ImageDTO{
    
    private UUID id;
    private byte[] data;
}
