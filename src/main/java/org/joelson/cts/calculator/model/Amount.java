package org.joelson.cts.calculator.model;

public record Amount(String currency, double amount) {

    public Amount multiplyBy(double f) {
        return new Amount(currency, amount * f);
    }

    public Amount divideBy(double f) {
        return new Amount(currency, amount / f);
    }

    public String asString() {
        return (amount < 1_000_000) ?
                String.format("%,.0f %s", amount, currency) : String.format("%.2e %s", amount, currency);
    }
}
