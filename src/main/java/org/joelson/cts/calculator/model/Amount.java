package org.joelson.cts.calculator.model;

public record Amount(String currency, double amount) {

    public Amount multiplyBy(double amount) {
        return new Amount(currency, this.amount * amount);
    }

    public Amount divideBy(double amount) {
        return new Amount(currency, this.amount / amount);
    }

    public Amount plus(Amount that) {
        checkCurrency(that);
        return plus(that.amount);
    }

    public Amount plus(double amount) {
        return new Amount(currency, this.amount + amount);
    }

    public String asString() {
        return (amount < 1_000_000) ?
                String.format("%,.0f %s", amount, currency) : String.format("%.2e %s", amount, currency);
    }

    private void checkCurrency(Amount that) {
        if (!currency.equals(that.currency)) {
            throw new IllegalStateException("Trying to mix currencies " + currency + " and " + that.currency + ".");
        }
    }
}
