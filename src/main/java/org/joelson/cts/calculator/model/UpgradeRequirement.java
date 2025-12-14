package org.joelson.cts.calculator.model;

public record UpgradeRequirement(Upgrade upgrade) implements Requirement {

    @Override
    public boolean isFulfilled(GardenState state) {
        return state.isUpgradeBought(upgrade);
    }
}
