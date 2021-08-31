package com.pizzeria.application.imageapplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import com.pizzeria.core.ApplicationBase;
import com.pizzeria.domain.imagedomain.Image;
import com.pizzeria.domain.imagedomain.ImageRepository;
import com.pizzeria.dtos.imagedto.CreateOrUpdateImageDTO;
import com.pizzeria.dtos.imagedto.ImageDTO;
import com.pizzeria.dtos.imagedto.ImageDTOBytes;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageApplicationImp extends ApplicationBase<Image, UUID> implements ImageApplication{

    private final RedisTemplate<String, byte[]> template;
    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public ImageApplicationImp(final ModelMapper modelMapper, final Logger logger,
                                final ImageRepository imageRepository,
                                final RedisTemplate<String, byte[]> template){
            super((id) -> imageRepository.findById(id));
            this.imageRepository=imageRepository;
            this.logger=logger;
            this.modelMapper=modelMapper;
            this.template=template;
    }

    @Override
    public ImageDTO save(CreateOrUpdateImageDTO dto) {

        Image image = modelMapper.map(dto, Image.class);
        image.setId(UUID.randomUUID());
        image.validate();
        // image.validate("data", image.getData().toString(), (data)-> this.imageRepository.exists(data));
        this.imageRepository.save(image);
        logger.info(serializeObject(image, "added"));
        return modelMapper.map(image, ImageDTO.class);
    } 
    
    @Override
    public ImageDTOBytes get(UUID id) {
        
        Image image= this.findById(id);
        image.validate();
        return this.modelMapper.map(image, ImageDTOBytes.class);
    }


    private String serializeObject(Image image, String msg){
        return String.format("Image {id: %s, data: %s} has been %s succesfully.",
                            image.getId(), image.getData(),
                            msg);
    }

    public File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
