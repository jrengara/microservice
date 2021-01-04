package com.example.currency.exchange.service;

import javax.persistence.*;
import java.math.*;

@Entity(name = "exchange_value")
public class ExchangeValue {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "fromval")
    private  String from;

    @Column(name="toval")
    private  String to;

    @Column(name = "conversion_multiple")
    private BigDecimal conversionMultiple;

    private Integer port=8000;

    public ExchangeValue() {

    }

    public ExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
