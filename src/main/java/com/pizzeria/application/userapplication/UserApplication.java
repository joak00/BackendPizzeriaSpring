package com.pizzeria.application.userapplication;

import java.util.List;
import java.util.UUID;

import com.pizzeria.domain.userdomain.UserProjection;
import com.pizzeria.dto.userdtos.CreateOrUpdateUserDTO;
import com.pizzeria.dto.userdtos.UserDTO;

public interface UserApplication {
    public UserDTO add(CreateOrUpdateUserDTO dto);

    public UserDTO get(UUID id);

    public void update(UUID id, CreateOrUpdateUserDTO dto);

    public void delete(UUID id);

    public List<UserProjection> getAll(String name,  int page, int size);
}
