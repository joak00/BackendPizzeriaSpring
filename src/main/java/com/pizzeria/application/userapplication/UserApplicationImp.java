package com.pizzeria.application.userapplication;

import java.util.List;
import java.util.UUID;

import com.pizzeria.domain.userdomain.User;
import com.pizzeria.domain.userdomain.UserProjection;
import com.pizzeria.domain.userdomain.UserRepository;
import com.pizzeria.dto.userdtos.CreateOrUpdateUserDTO;
import com.pizzeria.dto.userdtos.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;

public class UserApplicationImp implements UserApplication {

    private final UserRepository userRepository;

    @Autowired
    public UserApplicationImp(final UserRepository userRepository) {
        this.userRepository = userRepository;
        //log
    }

    @Override
    public UserDTO add(CreateOrUpdateUserDTO dto) {
        User user = UserService.create(dto);
        //mapper
        this.userRepository.add(user);
        return UserService.createDTO(user);
        //Validar que el usuario no existe select count (*) from user where name = ?
        //Validar que no está el nombre duplicado
        //log
        //mapper
    }

    @Override
    public UserDTO get(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow();
        //mapper
        return UserService.createDTO(user);
    }

    @Override
    public void update(UUID id, CreateOrUpdateUserDTO dto) {
        User user = this.userRepository.findById(id).orElseThrow();
        user.name = dto.name;
        user.lastname = dto.lastname;
        user.email = dto.email;
        user.password = dto.password;
        this.userRepository.update(user);
    }

    @Override
    public void delete(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow();
        this.userRepository.delete(user);

    }

    @Override
    public List<UserProjection> getAll(String name, int page, int size) {
        return this.userRepository.getAll(name, page, size);
    }

}
