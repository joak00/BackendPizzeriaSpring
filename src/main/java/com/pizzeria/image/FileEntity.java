package com.example.demo;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RedisHash("File")
@Getter @Setter @NoArgsConstructor
public class FileEntity {
    
    @Id @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false, name = "name", unique = true)
    private String name;

    private String contentType;

    @Column(nullable = false, name = "size")
    private Long size;

    @Column(nullable = false, name = "data")
    private byte[] data;

}
