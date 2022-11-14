package com.student.ust.exception;

public class InvalidEmail extends BusinessException{
    public InvalidEmail(){
        super("Invalid E-Mail");
    }
}
