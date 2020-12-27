package com.example.currency.exchange.service;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.env.*;
import org.springframework.web.bind.annotation.*;

import java.math.*;

@RestController
public class CurrencyExchangeController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from,@PathVariable String to){

         //ExchangeValue exchangeValue =  new ExchangeValue(100L,from,to, BigDecimal.valueOf(65));
         ExchangeValue exchangeValue = currencyExchangeRepository.findByFromAndTo(from,to);
        logger.info("{}",exchangeValue);
         exchangeValue.setPort(
                   Integer.valueOf(environment.getProperty("local.server.port")));
         return exchangeValue;
    }


}
