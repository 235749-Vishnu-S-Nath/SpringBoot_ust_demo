package com.student.ust.exception;

public class InvalidPassword extends BusinessException{
    public InvalidPassword(){
        super("Invalid Password");
    }
}
