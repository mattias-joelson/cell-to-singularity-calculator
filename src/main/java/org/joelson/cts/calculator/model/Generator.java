package org.joelson.cts.calculator.model;

public class Generator {

    private final String name;
    private final Amount baseCost;
    private final float costIncrease;
    private final Amount baseProduction;

    public Generator(String name, Amount baseCost, float costIncrease, Amount baseProduction) {
        this.name = name;
        this.baseCost = baseCost;
        this.costIncrease = costIncrease;
        this.baseProduction = baseProduction;
    }

    public String getName() {
        return name;
    }

    public Amount getBaseCost() {
        return baseCost;
    }

    public float getCostIncrease() {
        return costIncrease;
    }

    public Amount getCost(int level) {
        return new Amount(baseCost.currency(), (float) (baseCost.amount() * Math.pow(costIncrease, level)));
    }

    public Amount getBaseProduction() {
        return baseProduction;
    }

    public CurrencyMapping getMapping() {
        return new CurrencyMapping(baseCost.currency(), baseProduction.currency());
    }
}
