package com.pizzeria.domain.userdomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryRead {

    public Optional<User> findById(UUID id);

    public List<UserProjection> getAll(String name, String lastname, String email, String password);
}
