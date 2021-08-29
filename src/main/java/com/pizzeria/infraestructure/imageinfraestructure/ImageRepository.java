package com.pizzeria.infraestructure.imageinfraestructure;

import java.util.UUID;

import com.pizzeria.domain.imagedomain.Image;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, UUID>{
    
}
