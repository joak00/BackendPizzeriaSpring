package com.pizzeria.domain.commentdomain;

import java.util.UUID;

public interface CommentProjection {

    UUID getId();

    String getText();

    // String getDate();

    String getRating();
    
}
