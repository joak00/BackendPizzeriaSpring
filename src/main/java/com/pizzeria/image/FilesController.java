package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.frontbackend.springboot.model.FileEntity;
//import com.frontbackend.springboot.service.FileService;

@RestController
@RequestMapping("files")
public class FilesController {
    
    private final FileService fileService;

    @Autowired
    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            FileEntity fileEntity = fileService.save(file);

            MultipartFile file2 = file;
            File fileCloudinary = convert(file2);

            Cloudinary cloudinary = new Cloudinary(RedisConfiguration.config);
            cloudinary.uploader().upload(fileCloudinary, ObjectUtils.emptyMap());

            return ResponseEntity.status(HttpStatus.OK)
                                 .body(String.format("Imagen subida correctamente: %s, uuid=%s", file.getOriginalFilename(), fileEntity.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(String.format("No se ha podido subir la imagen: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Optional<FileEntity> fileEntityOptional = fileService.getFile(id);

        if (!fileEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                                 .build();
        }

        FileEntity fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                             .contentType(MediaType.valueOf(fileEntity.getContentType()))
                             .body(fileEntity.getData());
    }
}
