package com.example.starling.app.service;

import com.example.starling.app.service.authorization.AuthorizationProvider;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Starling {

    private static final String MONTH_START_TIMESTAMP = YearMonth.now().atDay(1).format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T00:00:00Z'"));
    private static final String MONTH_END_TIMESTAMP = YearMonth.now().atEndOfMonth().format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T23:59:00Z'"));

    private final StarlingClient client;
    private String accountUid = null;
    private String defaultCategory = null;

    public Starling(AuthorizationProvider authorizationProvider) {
        client = new StarlingClient(authorizationProvider);
        getAccountUid();
    }

    public String getAccount() {
        return client.get("/accounts");
    }

    public String getMonthTransactions() {
        return client.get(String.format("/feed/account/%s/category/%s/transactions-between?minTransactionTimestamp=%s&maxTransactionTimestamp=%s",
                accountUid, defaultCategory, MONTH_START_TIMESTAMP, MONTH_END_TIMESTAMP));
    }

    public String getNetSpending() {
        JSONArray transactions = new JSONObject(getMonthTransactions()).getJSONArray("feedItems");
        double netTotal = 0.0;
        for (int i = 0; i < transactions.length(); i++) {
            JSONObject transaction = transactions.getJSONObject(i);
            double amount = transaction.getJSONObject("amount").getDouble("minorUnits");
            netTotal = transaction.getString("direction").equals("OUT")
                    && !transaction.getString("source").equals("INTERNAL_TRANSFER") ?
                    (netTotal - amount) : (netTotal + amount);
        }
        return String.valueOf(netTotal / 100);
    }

    public String getAccountUid() {
        JSONObject account = new JSONObject(getAccount()).getJSONArray("accounts").getJSONObject(0);
        accountUid = account.getString("accountUid");
        defaultCategory = account.getString("defaultCategory");
        return accountUid;
    }

    public String get(String path) {
        return client.get(path);
    }

}
