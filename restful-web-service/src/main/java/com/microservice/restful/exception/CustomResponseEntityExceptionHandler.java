package com.microservice.restful.exception;

import com.microservice.restful.user.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.mvc.method.annotation.*;
import java.util.*;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));

        return  new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(UserNotFound.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));

        return  new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"ValidationFailed",ex.getBindingResult().toString());

        return  new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
    }




}
