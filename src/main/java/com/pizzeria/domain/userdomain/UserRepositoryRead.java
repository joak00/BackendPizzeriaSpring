package com.pizzeria.domain.userdomain;

import java.util.List;

public interface UserRepositoryRead {

    public List<UserProjection> getAll(String name, int page, int size);
}
