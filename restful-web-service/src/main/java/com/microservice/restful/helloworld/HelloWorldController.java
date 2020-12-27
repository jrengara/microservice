package com.microservice.restful.helloworld;

import com.microservice.restful.helloworld.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.i18n.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    //@GetMapping("/sayHello")
    @RequestMapping(method = RequestMethod.GET, path = "/sayHello")
    public String printHello() {
        return "Hello world1";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello world1");
    }


    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello world1 %s", name));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/printHelloInternationalization")
    public String printHelloInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        System.out.println("hello world");
        return messageSource.getMessage("good.morning.message", null, locale);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/printHelloInternationalization3")
    public String printHelloInternationalized2() {
        System.out.println("hello world3");
        System.out.println("hi");
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}