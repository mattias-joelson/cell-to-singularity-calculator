package org.joelson.cts.calculator.model;

public class Generator {

    private final String name;
    private final Amount baseCost;
    private final float incrementCost;
    private final Amount baseProduction;

    public Generator(
            String name, Amount baseCost, float incrementCost, Amount baseProduction) {
        this.name = name;
        this.baseCost = baseCost;
        this.incrementCost = incrementCost;
        this.baseProduction = baseProduction;
    }

    public String getName() {
        return name;
    }

    public Amount getBaseCost() {
        return baseCost;
    }

    public float getIncrementCost() {
        return incrementCost;
    }

    public Amount getCost(int level) {
        return new Amount(baseCost.currency(), (float) (baseCost.amount() * Math.pow(incrementCost, level)));
    }

    public Amount getBaseProduction() {
        return baseProduction;
    }

    public CurrencyMapping getMapping() {
        return new CurrencyMapping(baseCost.currency(), baseProduction.currency());
    }
}
