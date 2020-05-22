package com.dq.zoo.helpers;

public class CancellingOperationException extends RuntimeException {

    public CancellingOperationException(String message) {
        super(message);
    }
}
