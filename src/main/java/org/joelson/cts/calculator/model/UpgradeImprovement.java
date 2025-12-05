package org.joelson.cts.calculator.model;

public record UpgradeImprovement(Upgrade upgrade, UpgradeEffect effect) implements Improvement {

    @Override
    public Object getName() {
        return upgrade.getName();
    }

    @Override
    public Amount getCost() {
        return upgrade.getCost();
    }

    @Override
    public Amount getIncrease() {
        return effect.getGenerator().getIncrease().times(effect.getGenerator().getCount())
                .times(effect.getEfficiency() - 1);
    }
}
