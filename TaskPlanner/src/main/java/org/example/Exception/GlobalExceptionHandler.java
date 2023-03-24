package org.example.Exception;

import lombok.extern.java.Log;
import org.example.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(TaskException.class)
    public ResponseEntity<MyErrorDetails> taskExceptionHandler(TaskException exc, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<MyErrorDetails> loginExceptionHandler(LoginException exc, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<MyErrorDetails> invalidTokenExceptionHandler(InvalidTokenException exc, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<MyErrorDetails> userExceptionHandler(UserException exc, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(SprintException.class)
    public ResponseEntity<MyErrorDetails> sprintExceptionHandler(SprintException exc, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<MyErrorDetails> sqlExceptionHandler(SQLException exc, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> genericExceptionHandler(Exception exc, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setMessage(exc.getMessage());
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorDetails> methodArgumentExceptionHandler(MethodArgumentNotValidException me, WebRequest req)  {

        MyErrorDetails err=new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setDescription(req.getDescription(false));
        err.setMessage(me.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyErrorDetails> noHandlerExceptionHandler(NoHandlerFoundException me, WebRequest req)  {

        MyErrorDetails err=new MyErrorDetails();
        err.setDateAndTime(LocalDateTime.now());
        err.setDescription(req.getDescription(false));
        err.setMessage(me.getMessage());

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }

}

