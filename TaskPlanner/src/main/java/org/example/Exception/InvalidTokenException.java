package org.example.Exception;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
