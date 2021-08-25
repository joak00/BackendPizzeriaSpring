package com.pizzeria.infraestructure.userinfraestructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pizzeria.domain.userdomain.User;
import com.pizzeria.domain.userdomain.UserProjection;
import com.pizzeria.domain.userdomain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryImp implements UserRepository {

    private final UserJPARepository userJPARepository;

    @Autowired
    public UserRepositoryImp(final UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    @Override
    public void add(User user) {
        this.userJPARepository.save(user);
        //controlar excepción, no validar
    }

    @Override
    public Optional<User> findById(UUID id) {
        return this.userJPARepository.findById(id);
    }

    @Override
    public void update(User user) {
        this.userJPARepository.save(user);
    }

    @Override
    public void delete(User user) {
        this.userJPARepository.delete(user);
    }

    @Override
    public List<UserProjection> getAll(String name, int page, int size) {
        return this.userJPARepository.findByCriteria(
            name,
            PageRequest.of(page, size, Sort.by("name").descending())
        );
    }
}
