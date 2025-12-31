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
        return effect.generator().baseProduction().times(generatorState.efficiency())
                .times(generatorState.count()).times(effect.efficiency() - 1);
    }

    public static UpgradeImprovement create(Upgrade upgrade, UpgradeEffect effect, GardenState state) {
        return new UpgradeImprovement(upgrade, effect, state.getGeneratorState(effect.generator()));
    }
}
