package com.pizzeria.application.imageapplication;

import java.util.UUID;

import com.pizzeria.dtos.imagedto.CreateOrUpdateImageDTO;
import com.pizzeria.dtos.imagedto.ImageDTO;

public interface ImageApplication{
    
    public ImageDTO save(CreateOrUpdateImageDTO dto); 
    public ImageDTO get(UUID id); 
}
