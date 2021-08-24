package com.pizzeria.domain.commentdomain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.pizzeria.core.EntityBase;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")

@Getter
@Setter
@NoArgsConstructor
public class Comment extends EntityBase {
    @Id
    @Type(type = "uuid-char")
    public UUID id;

    @NotBlank
    @Column(nullable = false)
    public String text;

    @NotBlank
    @Column(nullable = false)
    public Date date;

    @NotBlank
    @Column(nullable = false)
    public int rating;
}