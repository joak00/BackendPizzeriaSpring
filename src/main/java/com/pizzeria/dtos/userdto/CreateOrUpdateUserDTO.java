package com.pizzeria.dtos.userdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pizzeria.domain.userdomain.Rol;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Validated
@Getter @Setter @NoArgsConstructor public class CreateOrUpdateUserDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private Rol rol = Rol.ROL_USER;
}
