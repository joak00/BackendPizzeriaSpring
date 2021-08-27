package com.pizzeria.core.exceptions;

public class InternalServerErrorException extends HttpException{

    private final InternalServerErrorEnum internalServerErrorEnum;

    public InternalServerErrorException(InternalServerErrorEnum internalServerErrorEnum){
        super(500, "Internal Server Error");
        this.internalServerErrorEnum = internalServerErrorEnum;
    }

    public final InternalServerErrorEnum getInternalServerErrorEnum(){
        return this.internalServerErrorEnum;
    }
}