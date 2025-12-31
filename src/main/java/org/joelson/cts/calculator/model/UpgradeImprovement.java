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
        return (effect.generator().isTimed()) ? getIncreaseTimed() : getIncreaseUntimed();
    }

    private Amount getIncreaseTimed() {
        float effectChange = effect.efficiency() * effect.speed() - 1;
        return effect.generator().getBaseProduction().multiplyBy(generatorState.efficiency())
                .multiplyBy(generatorState.count()).divideBy(effect.generator().getBaseChargeTime())
                .multiplyBy(generatorState.speed()).multiplyBy(effectChange);
    }

    public Amount getIncreaseUntimed() {
        return effect.generator().getBaseProduction().multiplyBy(generatorState.efficiency())
                .multiplyBy(generatorState.count()).multiplyBy(effect.efficiency() - 1);
    }

    public static UpgradeImprovement create(Upgrade upgrade, UpgradeEffect effect, GardenState state) {
        return new UpgradeImprovement(upgrade, effect, state.getGeneratorState(effect.generator()));
    }
}
