package com.pizzeria.domain.userdomain;

import java.util.Optional;
import java.util.UUID;

import com.pizzeria.core.functionalinterfaces.ExistsByField;
import com.pizzeria.core.functionalinterfaces.FindById;

public interface UserRepositoryWrite extends FindById<User, UUID>, ExistsByField {
    public void add(User user);

    public void update(User user);

    public void delete(User user);

    public Optional<User> findById(UUID id);
}
