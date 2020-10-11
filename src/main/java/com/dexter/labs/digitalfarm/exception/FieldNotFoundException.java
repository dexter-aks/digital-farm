package com.dexter.labs.digitalfarm.exception;

public class FieldNotFoundException extends Exception{
    private String message;
    public FieldNotFoundException(String fieldId){
        message= fieldId +" does not exists";
    }
}
