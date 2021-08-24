package com.pizzeria.dtos.userdtos;

import java.io.Serializable;
import java.util.UUID;

import com.pizzeria.domain.userdomain.Rol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class UserDTO implements Serializable{
    public UUID id;
    public String name;
    public String lastname;
    public String email;
    public String password;
    public Rol rol = Rol.ROL_USER;
}
