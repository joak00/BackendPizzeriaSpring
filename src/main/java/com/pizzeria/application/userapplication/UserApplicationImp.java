package com.pizzeria.application.userapplication;

import java.util.List;
import java.util.UUID;

import com.pizzeria.domain.userdomain.UserProjection;
import com.pizzeria.dto.userdtos.CreateOrUpdateUserDTO;
import com.pizzeria.dto.userdtos.UserDTO;

public class UserApplicationImp implements UserApplication {

    @Override
    public UserDTO add(CreateOrUpdateUserDTO dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDTO get(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(UUID id, CreateOrUpdateUserDTO dtos) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(UUID id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<UserProjection> getAll(String name, int page, int size) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
