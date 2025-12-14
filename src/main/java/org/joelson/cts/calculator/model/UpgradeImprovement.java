package org.joelson.cts.calculator.model;

public record UpgradeImprovement(Upgrade upgrade, UpgradeEffect effect, GeneratorState generatorState)
        implements Improvement {

    @Override
    public String getName() {
        return upgrade.getName();
    }

    @Override
    public Amount getCost() {
        return upgrade.getCost();
    }

    @Override
    public Amount getIncrease() {
        return effect.getGenerator().getBaseProduction().times(generatorState.efficiency())
                .times(generatorState.count()).times(effect.getEfficiency() - 1);
    }

    public static UpgradeImprovement create(Upgrade upgrade, UpgradeEffect effect, GardenState state) {
        return new UpgradeImprovement(upgrade, effect, state.getGeneratorState(effect.getGenerator()));
    }
}
