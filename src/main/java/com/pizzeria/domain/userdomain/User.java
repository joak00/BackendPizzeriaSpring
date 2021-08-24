package com.pizzeria.domain.userdomain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pizzeria.core.EntityBase;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
public @Getter @Setter @NoArgsConstructor class User extends EntityBase {
    @Id
    @Type(type = "uuid-char")
    public UUID id;

    @NotBlank
    @Column(unique = true)
    public String name;

    @NotBlank
    @Column
    public String lastname;

    @NotNull
    @Email
    @Column
    public String email;

    @NotNull
    @Column
    public String password;

    @NotNull
    @Column
    public Rol rol = Rol.ROL_USER;
}