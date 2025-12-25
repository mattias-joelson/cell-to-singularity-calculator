package org.joelson.cts.calculator.model;

public class Generator implements Unlockable {

    public static int UNTIMED_CHARGE_TIME = -1;

    private final String name;
    private final Amount baseCost;
    private final float compoundingCost;
    private final Amount baseProduction;
    private final int baseChargeTime;

    public Generator(String name, Amount baseCost, float compoundingCost, Amount baseProduction) {
        this(name, baseCost, compoundingCost, baseProduction, UNTIMED_CHARGE_TIME);
    }

    public Generator(String name, Amount baseCost, float compoundingCost, Amount baseProduction, int baseChargeTime) {
        this.name = name;
        this.baseCost = baseCost;
        this.compoundingCost = compoundingCost;
        this.baseProduction = baseProduction;
        this.baseChargeTime = baseChargeTime;
    }

    @Override
    public String getName() {
        return name;
    }

    public Amount getBaseCost() {
        return baseCost;
    }

    public float getCompoundingCost() {
        return compoundingCost;
    }

    public Amount getBaseProduction() {
        return baseProduction;
    }

    public Amount getCost(int level) {
        return new Amount(baseCost.currency(), baseCost.amount() * Math.pow(compoundingCost, level));
    }

    public CurrencyMapping getMapping() {
        return new CurrencyMapping(baseCost.currency(), baseProduction.currency());
    }
}
