package com.pizzeria.domain.userdomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pizzeria.core.EntityBase;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
public @Getter @Setter @NoArgsConstructor class User extends EntityBase {
  
    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    @Column
    private String lastname;

    @NotNull
    @Email
    @Column
    private String email;

    @NotNull
    @Column
    private String password;

    @NotNull
    @Column
    private Rol rol = Rol.ROL_USER;
}