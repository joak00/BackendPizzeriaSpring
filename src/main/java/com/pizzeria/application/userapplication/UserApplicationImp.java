package com.pizzeria.application.userapplication;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.domain.userdomain.User;
import com.pizzeria.domain.userdomain.UserProjection;
import com.pizzeria.domain.userdomain.UserRepositoryWrite;
import com.pizzeria.dtos.userdtos.CreateOrUpdateUserDTO;
import com.pizzeria.dtos.userdtos.UserDTO;
import com.pizzeria.domain.userdomain.UserRepositoryRead;

@Service
public class UserApplicationImp implements UserApplication {

    private final ModelMapper modelMapper = new ModelMapper();
    private final UserRepositoryRead userRepositoryRead;
    private final UserRepositoryWrite userRepositoryWrite;

    @Autowired
    public UserApplicationImp(final UserRepositoryRead userRepositoryRead,
            final UserRepositoryWrite userRepositoryWrite) {
        this.userRepositoryRead = userRepositoryRead;
        this.userRepositoryWrite = userRepositoryWrite;
    }

    @Override
    public UserDTO add(CreateOrUpdateUserDTO dto) {
        User user = this.modelMapper.map(dto, User.class);
        user.setId(UUID.randomUUID());
        // TODO: Validar que la pizza no existe(nombre no duplicado) | select count (*)
        // from useres where name = ?
        // If count > 0 = error.
        user.validate();
        this.userRepositoryWrite.add(user);
        // log OK
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO get(UUID id) {
        User user = this.userRepositoryRead.findById(id).orElseThrow();
        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public void update(UUID id, CreateOrUpdateUserDTO dto) {
        User user = this.userRepositoryRead.findById(id).orElseThrow();
        user.setName(dto.getName());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        this.userRepositoryWrite.update(user);
    }

    @Override
    public void delete(UUID id) {
        User user = this.userRepositoryRead.findById(id).orElseThrow();
        this.userRepositoryWrite.delete(user);
    }

    @Override
    public List<UserProjection> getAll(String name, int page, int size) {
        return this.userRepositoryRead.getAll(name, page, size);
    }

}