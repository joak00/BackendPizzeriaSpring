package com.pizzeria.application.userapplication;

import java.util.UUID;

public class CreateOrUpdateUser {
    public UUID id;
    public String name;
    public String lastname;
    public String email;
    public String password;

 
    public UUID getUUID(){
        return this.id;
    }
 
    public String getName(){
        return this.name;
    }
 
    public String getLastName(){
        return this.lastname;
    }
 
    public String getEmail(){
        return this.email;
    }
 
    public void setUUID(UUID id){
        this.id = id;
    }
 
    public void setName(String name){
        this.name = name;
    }
 
    public void setLastName(String lastname){
        this.lastname = lastname;
    }
 
    public void setEmail(String email){
        this.email = email;
    }
}
