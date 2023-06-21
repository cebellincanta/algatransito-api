package com.cebellincanta.transito.domain.exception;

public class NegocioExpection extends RuntimeException{
    public NegocioExpection(String message) {
        super(message);
    }
}
