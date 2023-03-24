package org.example.Exception;

public class TaskException extends RuntimeException{
    public TaskException() {
    }

    public TaskException(String message) {
        super(message);
    }
}
