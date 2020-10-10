package com.dexter.labs.digitalfarm.exception;

public class ClientException extends  Exception{

    private String message;

    public ClientException(){}

    public ClientException(String message){
        this.message = message;
    }
}
