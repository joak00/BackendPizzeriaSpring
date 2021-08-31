package com.pizzeria.domain.imagedomain;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import com.pizzeria.core.EntityBase;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@RedisHash("Image")
public @Getter @Setter @NoArgsConstructor class Image extends EntityBase{

    @Size(min = 1) //te valida automaticamente
    private byte[] data;

    @TimeToLive
    int timeout= 60;
}
