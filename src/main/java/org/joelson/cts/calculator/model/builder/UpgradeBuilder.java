package org.joelson.cts.calculator.model.builder;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.Upgrade;

public record UpgradeBuilder(GardenBuilder gardenBuilder, Generator generator, Upgrade upgrade) {

    public UpgradeBuilder addGeneratorRequirement(String requiredGeneratorName) {
        return addGeneratorRequirement(requiredGeneratorName, 1);
    }

    public UpgradeBuilder addGeneratorRequirement(String requiredGeneratorName, int count) {
        gardenBuilder.addUnresolvedRequirement(upgrade, requiredGeneratorName, count);
        return this;
    }

    public UpgradeBuilder addUpgradeRequirement(String requiredUpgradeName) {
        gardenBuilder.addUnresolvedRequirement(upgrade, requiredUpgradeName);
        return this;
    }

    public UpgradeBuilder addUpgrade(String name, Amount cost, float efficiency) {
        return gardenBuilder.addGeneratorUpgrade(generator, name, cost, efficiency);
    }

    public UpgradeBuilder addUpgrade(String name, Amount cost, float efficiency, float speed) {
        return gardenBuilder.addGeneratorUpgrade(generator, name, cost, efficiency, speed);
    }
}
