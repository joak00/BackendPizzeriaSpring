package com.pizzeria.dto.userdtos;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class UserDTO implements Serializable{
    public UUID id;
    public String name;
    public String lastname;
    public String email;
    public String password;
}
