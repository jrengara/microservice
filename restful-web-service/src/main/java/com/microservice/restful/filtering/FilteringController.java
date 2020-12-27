package com.microservice.restful.filtering;

import com.fasterxml.jackson.databind.ser.*;
import com.fasterxml.jackson.databind.ser.impl.*;
import org.hibernate.annotations.*;
import org.springframework.http.converter.json.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class FilteringController {


    @GetMapping("/filtering")
    public MappingJacksonValue retriveSomeBean(){

        SomeBean bean = new SomeBean("val1","val2","val3");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        MappingJacksonValue mappingJacksonValue  = new MappingJacksonValue(bean);
        mappingJacksonValue.setFilters(filters);
        return  mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> retriveSomeBeanList(){
        return  List.of(new SomeBean("val1.1","val1.2","val1.3"),new SomeBean("val2.1","val2.2","val2.3"),
                new SomeBean("val3.1","val3.2","val3.3"));
    }

}
