package com.example.starling.app.controller;

import com.example.starling.app.service.Starling;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StarlingController {

    private final Starling starling;

    public StarlingController(Starling starling) {
        this.starling = starling;
    }

    @GetMapping(value = "/api/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStarlingAccount() {
        return starling.getAccount();
    }

    @GetMapping(value = "/api/{*path}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(@PathVariable String path) {
        return starling.get(path);
    }

    @GetMapping(value = "/api/accountUid", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getStarlingAccountUid() {
        return starling.getAccountUid();
    }

    @GetMapping(value = "/api/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMonthTransactions() {
        return starling.getMonthTransactions();
    }

    @GetMapping(value = "/api/netspending", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getNetTotal() {
        return starling.getNetSpending();
    }





}
