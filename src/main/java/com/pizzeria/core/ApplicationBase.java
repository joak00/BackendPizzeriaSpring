package com.pizzeria.core;

import com.pizzeria.core.exceptions.NotFoundException;
import com.pizzeria.core.functionalinterfaces.FindById;

public abstract class ApplicationBase<T, ID> {

    private FindById<T, ID> getById;

    protected T findById(ID id){
        T t = this.getById.findById(id).orElseThrow(()->{
            throw new NotFoundException();
        });
        return t;
    }

    protected ApplicationBase(FindById<T, ID> getById){
        this.getById = getById;
    }
}
