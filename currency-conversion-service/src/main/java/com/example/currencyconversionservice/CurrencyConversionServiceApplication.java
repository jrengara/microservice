package com.example.currencyconversionservice;

import brave.sampler.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.*;
import org.springframework.cloud.openfeign.*;


@SpringBootApplication
@EnableFeignClients("com.example.currencyconversionservice")
@EnableDiscoveryClient
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

	/*@Bean
    public Sampler defaultSampler(){
	    return  Sampler.ALWAYS_SAMPLE;
    }*/

}
