package com.example.disney.exception;


public class ParamNotFound extends RuntimeException {
    public ParamNotFound(String error) { super(error); }
}
