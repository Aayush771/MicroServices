package com.microservice.currency_conversion_service.Config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.currency_conversion_service.Controller.CurrencyConversion;

//@FeignClient(name="currency-exchange",url="http://localhost:8000")
@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {

     @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retriveExchangeValue(@PathVariable String from, @PathVariable String to);

   
      
}
