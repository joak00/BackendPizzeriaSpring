package com.pizzeria.domain.imagedomain;

import javax.persistence.Entity;

import com.pizzeria.core.EntityBase;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@RedisHash("Image")
public @Getter @Setter @NoArgsConstructor class Image extends EntityBase{

    private byte[] data;

    @TimeToLive
    int timeout= 60;
}
