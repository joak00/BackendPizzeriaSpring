package com.pizzeria.domain.commentdomain;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pizzeria.core.EntityBase;
import com.pizzeria.domain.pizzadomain.Pizza;
import com.pizzeria.domain.userdomain.User;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter @Setter @NoArgsConstructor public class Comment extends EntityBase {

    @NotBlank
    @Column(nullable = false, name = "text")
    private String text;

    // @NotNull
    // @Column(nullable = false, name = "date")
    // private Date date;

    @NotNull
    @Column(nullable = false, name = "rating")
    private BigDecimal rating;

   
    @Type(type = "uuid-char")
    @Column(name="user_id")
    private UUID userId;

  
    @NotNull
    @Type(type = "uuid-char")
    @Column(name="pizza_id", nullable = false)
    private UUID pizzaId;

    private @ManyToOne @JoinColumn (name ="user_id", insertable = false, updatable=false) User user;

    private @ManyToOne @JoinColumn (name="pizza_id", insertable = false, updatable=false) Pizza pizza;

}