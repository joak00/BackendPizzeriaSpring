package com.pizzeria.dtos.userdto;

import com.pizzeria.domain.userdomain.Rol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class CreateOrUpdateUserDTO {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Rol rol = Rol.ROL_USER;
}
