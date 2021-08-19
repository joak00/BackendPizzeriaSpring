package com.pizzeria.infraestructure.userinfraestructure;

import java.util.*;

import com.pizzeria.domain.userdomain.User;
import com.pizzeria.domain.userdomain.UserProjection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJPARepository extends JpaRepository<User, UUID> {
        @Query("SELECT p.id as id, p.name as name, p.lastname as lastname, p.email as email, p.password as password FROM User p WHERE (:name is NULL OR name LIKE %:name%)")
        List<UserProjection> findByCriteria(
            @Param("name") String name,
            Pageable pageable
        );
}
