package com.pizzeria.controller.usercontroller;

import java.util.UUID;

import com.pizzeria.application.userapplication.UserApplication;
import com.pizzeria.dtos.userdtos.CreateOrUpdateUserDTO;
import com.pizzeria.dtos.userdtos.UserDTO;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/users")
public class UserController {
    private final UserApplication userApplication;

    @Autowired
    public UserController(final UserApplication userApplication) {
        this.userApplication = userApplication;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody final CreateOrUpdateUserDTO dto) {
        dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        UserDTO userDTO = this.userApplication.add(dto);
        return ResponseEntity.status(201).body(userDTO);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody CreateOrUpdateUserDTO dto) {
        this.userApplication.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<?> get(@PathVariable UUID id) {
        UserDTO userDTO = this.userApplication.get(id);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable UUID id) {
        this.userApplication.delete(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(@RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(200).body(this.userApplication.getAll(name, page, size));
    }
}