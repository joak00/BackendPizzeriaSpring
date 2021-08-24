package com.pizzeria.domain.userdomain;

import java.util.UUID;

public interface UserProjection {
    UUID getId();

    String getName();

    String getLastname();

    String getEmail();

    String getPassword();

}
