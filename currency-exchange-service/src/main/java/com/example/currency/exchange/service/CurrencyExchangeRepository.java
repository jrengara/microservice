package com.example.currency.exchange.service;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<ExchangeValue,Long> {

    ExchangeValue findByFromAndTo(String from,String to);

}
