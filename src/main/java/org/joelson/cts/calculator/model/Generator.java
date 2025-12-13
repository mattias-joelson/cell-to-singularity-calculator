package org.joelson.cts.calculator.model;

public record Generator(String name, Amount baseCost, float compoundingCost, Amount baseProduction)
        implements Unlockable {

    public Amount getCost(int level) {
        return new Amount(baseCost.currency(), (float) (baseCost.amount() * Math.pow(compoundingCost, level)));
    }

    public CurrencyMapping getMapping() {
        return new CurrencyMapping(baseCost.currency(), baseProduction.currency());
    }
}
