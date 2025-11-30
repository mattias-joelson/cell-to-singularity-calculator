package org.joelson.cts.calculator.model;

public class Generator {

    private final String name;
    private final float baseCost;
    private final float incrementCost;
    private int count = 0;

    public Generator(String name, float baseCost, float incrementCost, int count) {
        this.name = name;
        this.baseCost = baseCost;
        this.incrementCost = incrementCost;
        this.count = 0;
    }

    public Generator(String name, float baseCost, float incrementCost) {
        this(name, baseCost, incrementCost, 0);
    }

    public String getName() {
        return name;
    }

    public float getBaseCost() {
        return baseCost;
    }

    public float getIncrementCost() {
        return incrementCost;
    }

    public float getCost(int level) {
        return (float) (baseCost * Math.pow(incrementCost, level));
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
