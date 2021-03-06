package com.pizzeria.dtos.userdto;

import java.io.Serializable;
import java.util.UUID;

import com.pizzeria.domain.userdomain.Rol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor public class UserDTO implements Serializable {
    private UUID id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Rol rol = Rol.ROL_USER;
}
