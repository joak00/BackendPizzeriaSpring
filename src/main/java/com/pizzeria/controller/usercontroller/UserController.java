package com.pizzeria.controller.usercontroller;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.pizzeria.application.userapplication.UserApplication;
import com.pizzeria.dtos.userdto.CreateOrUpdateUserDTO;
import com.pizzeria.dtos.userdto.UserDTO;

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

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.annotation.Validated;

@Validated
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/users")
public class UserController {

    private final UserApplication userApplication;

    @Autowired
    public UserController(final UserApplication userApplication){
        this.userApplication = userApplication;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody CreateOrUpdateUserDTO dto){
        UserDTO userDTO = this.userApplication.add(dto);

        return ResponseEntity.status(201).body(getJWTToken(userDTO.getName()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,  path = "/{id}")
    public ResponseEntity<?> get(@Valid @PathVariable UUID id) {
        UserDTO userDTO = this.userApplication.get(id);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable UUID id, @Valid @RequestBody CreateOrUpdateUserDTO dto) {
        UserDTO userDTO = this.userApplication.update(id, dto);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping(path = "/{id}")
    void delete(@Valid @PathVariable UUID id) {
        this.userApplication.delete(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(
        @RequestParam(required = false) String email,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.status(200).body(this.userApplication.getAll(email, page, size));
    }

    private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}