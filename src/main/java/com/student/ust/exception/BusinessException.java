package com.student.ust.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String exceptionMessage){
        super(exceptionMessage);
    }

}
