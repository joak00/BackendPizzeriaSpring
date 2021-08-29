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
import com.pizzeria.domain.userdomain.UserRepositoryWrite;
import com.pizzeria.domain.userdomain.UserRepositoryRead;
import com.pizzeria.dtos.userdto.CreateOrUpdateUserDTO;
import com.pizzeria.dtos.userdto.UserDTO;

@Service
public class UserApplicationImp extends ApplicationBase<User, UUID> implements UserApplication {

    private final UserRepositoryWrite userRepositoryWrite;
    private final UserRepositoryRead userRepositoryRead;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public UserApplicationImp  (final UserRepositoryWrite userRepositoryWrite, 
                                final UserRepositoryRead userRepositoryRead, 
                                final ModelMapper modelMapper,
                                final Logger logger) {

        super((id) -> userRepositoryWrite.findById(id));

        this.userRepositoryWrite = userRepositoryWrite;
        this.userRepositoryRead = userRepositoryRead;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public UserDTO add(CreateOrUpdateUserDTO dto) {

        User user = modelMapper.map(dto, User.class);
        user.setId(UUID.randomUUID());
        user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        user.validate("email", user.getEmail(), (email) -> this.userRepositoryWrite.exists(email));

        this.userRepositoryWrite.add(user);
        logger.info(this.serializeObject(user, "added"));

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO get(UUID id) {

        User user = this.findById(id);
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO update(UUID id, CreateOrUpdateUserDTO dto) {

        User user = this.findById(id);
        User userUpdated = this.modelMapper.map(dto, User.class);
        userUpdated.setId(user.getId());
        userUpdated.setEmail(user.getEmail());
        userUpdated.setRol(user.getRol());

        if (BCrypt.checkpw(userUpdated.getPassword(), user.getPassword())) {
            userUpdated.setPassword(user.getPassword());
        } else {
            userUpdated.setPassword(BCrypt.hashpw(userUpdated.getPassword(), BCrypt.gensalt()));
        }
        userUpdated.validate();

        this.userRepositoryWrite.update(userUpdated);
        logger.info(this.serializeObject(userUpdated, "updated"));

        return modelMapper.map(userUpdated, UserDTO.class);
    }

    @Override
    public void delete(UUID id) {

        User user = this.findById(id);
        this.userRepositoryWrite.delete(user);
        logger.info(this.serializeObject(user, "deleted"));
    }

    @Override
    public List<UserProjection> getAll(String email, int page, int size) {
        return this.userRepositoryRead.getAll(email, page, size);
    }
	
	private String serializeObject(User user, String message){
        
        return String.format("User {id: %s, name: %s, lastname: %s, email: %s, rol: %s} %s succesfully.",
                            user.getId(), user.getName(),
                            user.getLastname(), user.getEmail(),
                            user.getRol().toString(),
                            message);
    }

}