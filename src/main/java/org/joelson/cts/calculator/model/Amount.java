package org.joelson.cts.calculator.model;

public record Amount(String currency, float amount) {

    public Amount multiplyBy(float f) {
        return new Amount(currency, amount * f);
    }

    public Amount divideBy(float f) {
        return new Amount(currency, amount / f);
    }

    public String asString() {
        return (amount < 1_000_000) ?
                String.format("%,.0f %s", amount, currency) : String.format("%.2e %s", amount, currency);
    }
}
