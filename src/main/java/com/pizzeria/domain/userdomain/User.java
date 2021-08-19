package com.pizzeria.domain.userdomain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
@Entity
@Table(name="users")
public class User {
    @Id
    @Type(type = "uuid-char")
    public UUID id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String lastname;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public String password;

    @Override
    public boolean equals(Object obj)    {
        if (!(obj instanceof Entity)) {
            return false;
        }
        User tmp = (User)obj;
        return tmp.id.equals(this.id);
    }
   
    @Override
    public int hashCode() {
        return this.id.toString().hashCode();
    }
}