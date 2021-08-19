package com.pizzeria.domain.userdomain;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryWrite {
    public void add(User user);

    public void update(User user);

    public void delete(User user);

    public Optional<User> findById(UUID id);
}
