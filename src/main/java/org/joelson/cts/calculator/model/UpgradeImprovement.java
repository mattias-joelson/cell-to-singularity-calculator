package org.joelson.cts.calculator.model;

public record UpgradeImprovement(Upgrade upgrade, GardenState state)
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
        Amount amount = null;
        for (UpgradeEffect effect : upgrade.getEffects()) {
            if (amount == null) {
                amount = getEffectIncrease(effect);
            } else {
                Amount effectAmount = getEffectIncrease(effect);
                if (!effectAmount.currency().equals(amount.currency())) {
                    throw new IllegalStateException("Different currencies " + amount.currency() + " and " + effectAmount.currency() + ".");
                }
                amount = new Amount(amount.currency(), amount.amount() + effectAmount.amount());
            }
        }
        if (amount == null) {
            throw new NullPointerException("amount is null.");
        }
        return amount;
    }

    private Amount getEffectIncrease(UpgradeEffect effect) {
        GeneratorState generatorState = state.getGeneratorState(effect.generator());
        return (effect.generator().isTimed()) ? getIncreaseTimed(effect, generatorState) : getIncreaseUntimed(effect, generatorState);
    }

    private Amount getIncreaseTimed(UpgradeEffect effect, GeneratorState generatorState) {
        float effectChange = effect.efficiency() * effect.speed() - 1;
        return effect.generator().getBaseProduction().multiplyBy(generatorState.efficiency())
                .multiplyBy(generatorState.count()).divideBy(effect.generator().getBaseChargeTime())
                .multiplyBy(generatorState.speed()).multiplyBy(effectChange);
    }

    public Amount getIncreaseUntimed(UpgradeEffect effect, GeneratorState generatorState) {
        return effect.generator().getBaseProduction().multiplyBy(generatorState.efficiency())
                .multiplyBy(generatorState.count()).multiplyBy(effect.efficiency() - 1);
    }

    public static UpgradeImprovement create(Upgrade upgrade, GardenState state) {
        return new UpgradeImprovement(upgrade, state);
    }
}
