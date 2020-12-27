package com.example.currencyconversionservice;

//import org.springframework.cloud.netflix.ribbon.*;
//import org.springframework.cloud.netflix.ribbon.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

//@FeignClient(name="currency-exchange-service",url="localhost:8000")
@FeignClient(name="currency-exchange-service")
//@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable String from,@PathVariable String to);


}
