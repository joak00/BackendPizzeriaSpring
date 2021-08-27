package com.pizzeria.domain.userdomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryRead {

    public List<UserProjection> getAll(String name, int page, int size);
    public Optional<User> findById(UUID id);
}
