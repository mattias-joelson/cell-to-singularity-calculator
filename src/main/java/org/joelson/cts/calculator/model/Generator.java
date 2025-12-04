package org.joelson.cts.calculator.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Generator {

    Logger logger = LoggerFactory.getLogger(Generator.class);

    private final String name;
    private final float baseCost;
    private final float incrementCost;
    private final float baseProduction;
    private int count;
    private float efficiency;

    public Generator(
            String name, float baseCost, float incrementCost, float baseProduction, int count, float efficiency) {
        this.name = name;
        this.baseCost = baseCost;
        this.incrementCost = incrementCost;
        this.baseProduction = baseProduction;
        this.count = count;
        this.efficiency = efficiency;
    }

    public Generator(String name, float baseCost, float incrementCost, float baseProduction) {
        this(name, baseCost, incrementCost, baseProduction, 0, 1);
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

    public float getBaseProduction() {
        return baseProduction;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void updateEfficiency(Garden garden) {
        efficiency = 1;
        for (Upgrade upgrade : garden.getUpgrades()) {
            if (upgrade.isBought()) {
                for (UpgradeEffect effect : upgrade.getEffects()) {
                    if (effect.getGenerator() == this) {
                        logger.debug("generator: " + name + ", upgrade " + upgrade.getName() + " gives "
                                + effect.getEfficiency());
                        efficiency *= effect.getEfficiency();
                    }
                }
            }
        }
        logger.debug("generator: " + name + ": baseProduction " + baseProduction + " * efficiency " + efficiency + " = "
                + baseProduction * efficiency);
    }

    public float getEfficiency() {
        return efficiency;
    }

    public float getTotalProduction() {
        return count * baseProduction * efficiency;
    }
}
