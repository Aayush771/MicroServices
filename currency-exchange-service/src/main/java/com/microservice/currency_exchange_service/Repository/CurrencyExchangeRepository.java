package com.microservice.currency_exchange_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.currency_exchange_service.Controller.CurrencyExchange;


public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findByFromAndTo(String from, String to);
}
