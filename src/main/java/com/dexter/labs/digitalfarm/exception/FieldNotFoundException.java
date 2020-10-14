package com.dexter.labs.digitalfarm.exception;

public class FieldNotFoundException extends Exception{
    public FieldNotFoundException(String fieldId){
        String message = fieldId + " does not exists";
    }
}
