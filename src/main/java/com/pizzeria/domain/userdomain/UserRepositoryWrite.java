package com.pizzeria.domain.userdomain;

public interface UserRepositoryWrite {
    public void add(User user);

    public void update(User user);

    public void delete(User user);
}
