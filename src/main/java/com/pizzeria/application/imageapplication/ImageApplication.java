package com.pizzeria.application.imageapplication;

import java.util.UUID;

import com.pizzeria.dtos.imagedto.CreateOrUpdateImageDTO;
import com.pizzeria.dtos.imagedto.ImageDTO;
import com.pizzeria.dtos.imagedto.ImageDTOBytes;

public interface ImageApplication{
    
    public ImageDTO save(CreateOrUpdateImageDTO dto);
    public ImageDTOBytes get(UUID id);
}
