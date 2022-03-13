package com.nanos.irctc.exception;

public class SeatUnAvailableException extends RuntimeException{
    public SeatUnAvailableException(String message) {
        super(message);
    }
}
