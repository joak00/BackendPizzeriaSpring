package com.pizzeria.application.userapplication;

import java.util.List;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.domain.userdomain.User;
import com.pizzeria.domain.userdomain.UserProjection;
import com.pizzeria.domain.userdomain.UserRepositoryWrite;
import com.pizzeria.dtos.userdto.CreateOrUpdateUserDTO;
import com.pizzeria.dtos.userdto.UserDTO;
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
        user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
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
    public UserDTO update(UUID id, CreateOrUpdateUserDTO dto) {
        User userToUpdate = this.findById(id);
        User userUpdated = modelMapper.map(dto, User.class);
        userUpdated.setId(id);

        if(userToUpdate.getEmail().equals(dto.getEmail())){
            userUpdated.validate();
        }else{
            userUpdated.validate("email", userUpdated.getEmail(), (email) -> this.userWriteRepository.exists(email));
        }

        if (BCrypt.checkpw(dto.getPassword(), userToUpdate.getPassword())){
            userUpdated.setPassword(userToUpdate.getPassword());
        }else {
            userUpdated.setPassword(BCrypt.hashpw(userUpdated.getPassword(), BCrypt.gensalt()));
        }

        this.userRepositoryWrite.update(userUpdated);
        //logger.info(this.serializeObject(userUpdated, "update"));
        return modelMapper.map(userUpdated, UserDTO.class);

        // User user = this.userRepositoryRead.findById(id).orElseThrow();
        // user.setName(dto.getName());
        // user.setLastname(dto.getLastname());
        // user.setEmail(dto.getEmail());
        // user.setPassword(dto.getPassword());
        // this.userRepositoryWrite.update(user);
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