package org.joelson.cts.calculator.model;

public class Garden {

    private final String name;
    private final Currency currency;

    public Garden(String name, Currency currency) {
        this.name = name;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public Currency getCurrency() {
        return currency;
    }
}
