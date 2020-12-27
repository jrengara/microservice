package com.microservice.restful.user;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException {

    private String msg;

    public UserNotFound(String msg) {
        this.msg = msg;
    }

}
