package org.joelson.cts.calculator.model;

public record CurrencyMapping(String from, String to) {

    public String asString() {
        return from + " to " + to;
    }
}
