package com.microservice.currency_exchange_service.Controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.currency_exchange_service.Repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;
    @Autowired
    private Environment environment;

   // @Autowired
    private Logger logger = LoggerFactory.getLogger(CurrencyExchange.class);
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retriveExchangeValue(@PathVariable String from, @PathVariable String to) {

        logger.info("retriveExchangevalue called with {} to {}", from, to);

    //    CurrencyExchange currencyExchange = new CurrencyExchange(1001L, from, to, new BigDecimal("50.00"));
       CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
       if(currencyExchange == null) {
           throw new RuntimeException("No data found for " + from + " to " + to);
       }
       String port = environment.getProperty("local.server.port");
       currencyExchange.setEnvironment(port);



       return currencyExchange;
    }
}
