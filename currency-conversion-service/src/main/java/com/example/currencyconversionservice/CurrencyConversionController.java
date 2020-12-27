package com.example.currencyconversionservice;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

import java.math.*;
import java.util.*;


@RestController
public class CurrencyConversionController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;


    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean currencyConversion(@PathVariable String from,
                                                     @PathVariable String to,
                                                     @PathVariable BigDecimal quantity){

        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        ResponseEntity<CurrencyConversionBean>  responseEntity = new RestTemplate().
                getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionBean.class,
                uriVariables);
        CurrencyConversionBean responseBody = responseEntity.getBody();


       //logger.info("quantity"+responseBody.getQuantity());
        //logger.info("getConversionMultiple"+responseBody.getConversionMultiple());

        return  new CurrencyConversionBean(responseBody.getId(),responseBody.getFrom(),
                responseBody.getTo(),quantity,
                responseBody.getConversionMultiple(),
                quantity.multiply(responseBody.getConversionMultiple()),
                8100);

    }


    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean currencyConversionFeign(@PathVariable String from,
                                                     @PathVariable String to,
                                                     @PathVariable BigDecimal quantity){

        CurrencyConversionBean responseBody= currencyExchangeServiceProxy.retrieveExchangeValue(from,to);

        logger.info("response - {}",responseBody);

        return  new CurrencyConversionBean(responseBody.getId(),responseBody.getFrom(),
                responseBody.getTo(),quantity,
                responseBody.getConversionMultiple(),
                quantity.multiply(responseBody.getConversionMultiple()),
                responseBody.getPort());

    }

}
