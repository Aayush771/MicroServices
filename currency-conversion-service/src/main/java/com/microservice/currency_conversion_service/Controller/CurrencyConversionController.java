package com.microservice.currency_conversion_service.Controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.microservice.currency_conversion_service.Config.CurrencyExchangeProxy;


@RestController
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeProxy proxy;

    @Autowired
    private RestTemplate restTemplate;
       private Logger logger = LoggerFactory.getLogger(CurrencyConversion.class);
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
            @PathVariable BigDecimal quantity) {
                logger.info("retriveExchangevalue called with {} to {}", from, to);
                Map<String, String> uriVariables = new HashMap<>();
                uriVariables.put("from", from);
                uriVariables.put("to", to);
            ResponseEntity<CurrencyConversion> responseEntity =   restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class,uriVariables);
        //return new CurrencyConversion(10001L, from, to, quantity,BigDecimal.ONE,BigDecimal.TEN,"");
        CurrencyConversion currencyConversion = responseEntity.getBody();

        return new CurrencyConversion(currencyConversion.getId(), currencyConversion.getFrom(),
                currencyConversion.getTo(), quantity, currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment()+" Rest Templete");
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to,
            @PathVariable BigDecimal quantity) {
             
        //return new CurrencyConversion(10001L, from, to, quantity,BigDecimal.ONE,BigDecimal.TEN,"");
        CurrencyConversion currencyConversion = proxy.retriveExchangeValue(from, to);

        return new CurrencyConversion(currencyConversion.getId(), currencyConversion.getFrom(),
                currencyConversion.getTo(), quantity, currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment()+" Feign");
    }
}
