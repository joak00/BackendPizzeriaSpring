package com.pizzeria.domain.imagedomain;

import java.util.UUID;

import com.pizzeria.core.functionalinterfaces.ExistsByField;
import com.pizzeria.core.functionalinterfaces.FindById;

public interface ImageRepository extends FindById<Image, UUID>, ExistsByField{
    void save(Image image);
}
