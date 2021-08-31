package com.pizzeria.controller.imagecontroller;

import java.io.IOException;
import java.util.UUID;

import javax.validation.Valid;

import com.pizzeria.application.imageapplication.ImageApplication;
import com.pizzeria.dtos.imagedto.CreateOrUpdateImageDTO;
import com.pizzeria.dtos.imagedto.ImageDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private final ImageApplication imageApplication;
 
    @Autowired
    public ImageController(ImageApplication imageApplication) {
        this.imageApplication = imageApplication;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestParam("image") MultipartFile file) throws IOException {

        // Cloudinary cloudinary=new Cloudinary();
        // File fileCloudinary=imageApplicationImp.convert(file);
        // Map result= cloudinary.uploader().upload(fileCloudinary, ObjectUtils.emptyMap());
        // String format="jpg";
        // Transformation transformation= new Transformation().crop("fill");

        CreateOrUpdateImageDTO dto = new CreateOrUpdateImageDTO();
        dto.setData(file.getBytes());
        // dto.setCloudId((String) result.get("public_id"));
        // String cloudUrl= cloudinary.url().secure(true).format(format)
        // .transformation(transformation).publicId(dto.getCloudId()).generate();
        // dto.setCloudUrl(cloudUrl);

        ImageDTO imageDto = imageApplication.save(dto);

        return ResponseEntity.status(201).body(imageDto);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<?> get(@Valid @PathVariable UUID id) {

        ImageDTO imageDto = this.imageApplication.get(id);
        return ResponseEntity.status(HttpStatus.OK)
        .body(imageDto);
    }
}