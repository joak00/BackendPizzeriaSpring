package com.pizzeria.dto.userdtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class CreateOrUpdateUserDTO {
    public String name;
    public String lastname;
    public String email;
    public String password;
}
