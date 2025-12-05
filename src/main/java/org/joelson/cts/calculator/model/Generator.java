package org.joelson.cts.calculator.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Generator implements Improvement {

    static Logger logger = LoggerFactory.getLogger(Generator.class);

    private final String name;
    private final Amount baseCost;
    private final float incrementCost;
    private final Amount baseProduction;
    private int count;
    private float efficiency;

    public Generator(
            String name, Amount baseCost, float incrementCost, Amount baseProduction, int count, float efficiency) {
        this.name = name;
        this.baseCost = baseCost;
        this.incrementCost = incrementCost;
        this.baseProduction = baseProduction;
        this.count = count;
        this.efficiency = efficiency;
    }

    public Generator(String name, Amount baseCost, float incrementCost, Amount baseProduction) {
        this(name, baseCost, incrementCost, baseProduction, 0, 1);
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
                        logger.debug("generator: {}, upgrade {} gives {}", name, upgrade.getName(),
                                effect.getEfficiency());
                        efficiency *= effect.getEfficiency();
                    }
                }
            }
        }
        logger.debug("generator: {}: baseProduction {} * efficiency {} = {} {}", name, baseProduction.amount(),
                efficiency, baseProduction.amount() * efficiency, baseProduction.currency());
    }

    public float getEfficiency() {
        return efficiency;
    }

    public Amount getTotalProduction() {
        return new Amount(baseProduction.currency(), count * baseProduction.amount() * efficiency);
    }

    public Amount getCost() {
        return getCost(getCount());
    }

    @Override
    public Amount getIncrease() {
        return baseProduction.times(efficiency);
    }
}
