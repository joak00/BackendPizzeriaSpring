package com.pizzeria.dtos.userdtos;

import com.pizzeria.domain.userdomain.Rol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class CreateOrUpdateUserDTO {
    public String name;
    public String lastname;
    public String email;
    public String password;
    public Rol rol = Rol.ROL_USER;
}
