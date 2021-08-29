package com.pizzeria.application.userapplication;

import java.util.List;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.core.ApplicationBase;
import com.pizzeria.domain.userdomain.User;
import com.pizzeria.domain.userdomain.UserProjection;
import com.pizzeria.domain.userdomain.UserRepository;
import com.pizzeria.dtos.userdto.CreateOrUpdateUserDTO;
import com.pizzeria.dtos.userdto.UserDTO;

@Service
public class UserApplicationImp extends ApplicationBase<User, UUID> implements UserApplication {

    private final UserRepository userRepository;
    private final Logger log;
    private final ModelMapper modelMapper;

    @Autowired
    public UserApplicationImp  (final ModelMapper modelMapper, 
                                final Logger log, 
                                final UserRepository userRepository) {

        super((id) -> userRepository.findById(id));

        this.userRepository = userRepository;
        this.log = log;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO add(CreateOrUpdateUserDTO dto) {
        User user = this.modelMapper.map(dto, User.class);
        user.setId(UUID.randomUUID());
        user.validate("name", user.getName(), (name) -> this.userRepository.exists(name));
        this.userRepository.add(user);
        log.info(this.serializeObject(user, "added"));
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO get(UUID id) {
        User user = this.findById(id);
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO update(UUID id, CreateOrUpdateUserDTO dto) {
        User userToUpdate = this.findById(id);
        User userUpdated = modelMapper.map(dto, User.class);
        userUpdated.setId(id);

        if (userToUpdate.getEmail().equals(dto.getEmail())) {
            userUpdated.validate();
        } else {
            userUpdated.validate("email", userUpdated.getEmail(), (email) -> this.userRepository.exists(email));
        }

        if (BCrypt.checkpw(dto.getPassword(), userToUpdate.getPassword())) {
            userUpdated.setPassword(userToUpdate.getPassword());
        } else {
            userUpdated.setPassword(BCrypt.hashpw(userUpdated.getPassword(), BCrypt.gensalt()));
        }

        this.userRepository.update(userUpdated);
        log.info(this.serializeObject(userUpdated, "update"));
        return modelMapper.map(userUpdated, UserDTO.class);

    }

    @Override
    public void delete(UUID id) {
        User user = this.findById(id);
        this.userRepository.delete(user);
        log.info(this.serializeObject(user, "deleted"));
    }

    @Override
    public List<UserProjection> getAll(String name, int page, int size) {
        return this.userRepository.getAll(name, page, size);
    }

    private String serializeObject(User user, String message) {

        return String.format("User {id: %s, name: %s, lastname: %s, email: %s, password: %s, rol: %s} %s succesfully.", 
                            user.getId(), user.getName(),
                            user.getLastname(), user.getEmail(), 
                            user.getPassword(), user.getRol().toString(), 
                            message);
    }

}