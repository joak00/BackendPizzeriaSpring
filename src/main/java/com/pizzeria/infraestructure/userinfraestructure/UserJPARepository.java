package com.pizzeria.infraestructure.userinfraestructure;

import java.util.*;

import com.pizzeria.domain.userdomain.User;
import com.pizzeria.domain.userdomain.UserProjection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<User, UUID> {
        @Query("""
        SELECT p.id as id, p.name as name, p.lastname as lastname, p.email as email, p.password as password, p.rol as 
        FROM User p 
        WHERE (:email is NULL OR email LIKE %:email%)""")
        List<UserProjection> findByCriteria(@Param("email") String email, Pageable pageable);

	    @Query("SELECT CASE WHEN COUNT(i)>0 THEN true ELSE false END FROM User i WHERE i.email = :email")
	    boolean exists(@Param("email")String email);
}
