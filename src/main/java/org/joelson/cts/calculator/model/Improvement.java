package org.joelson.cts.calculator.model;

public interface Improvement {

    String getName();

    Amount getCost();

    Amount getIncrease();

    default CurrencyMapping getMapping() {
        return new CurrencyMapping(getCost().currency(), getIncrease().currency());
    }

    default double getRatio() {
        return getIncrease().amount() / getCost().amount();
    }
}
