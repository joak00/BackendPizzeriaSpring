package com.pizzeria.domain.imagedomain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import com.frontbackend.springboot.model.FileEntity;

@Repository
public interface FileRepository extends CrudRepository<FileEntity, String> {
    
}

