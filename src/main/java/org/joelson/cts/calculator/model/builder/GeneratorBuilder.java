package org.joelson.cts.calculator.model.builder;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Generator;

public record GeneratorBuilder(GardenBuilder gardenBuilder, Generator generator) {

    public GeneratorBuilder addGeneratorRequirement(String requiredGeneratorName) {
        return addGeneratorRequirement(requiredGeneratorName, 1);
    }

    public GeneratorBuilder addGeneratorRequirement(String requiredGeneratorName, int count) {
        gardenBuilder.addUnresolvedRequirement(generator, requiredGeneratorName, count);
        return this;
    }

    public GeneratorBuilder addUpgradeRequirement(String requiredUpgradeName) {
        gardenBuilder.addUnresolvedRequirement(generator, requiredUpgradeName);
        return this;
    }

    public UpgradeBuilder addEfficiencyUpgrade(String name, Amount cost, float efficiency) {
        return gardenBuilder.addEfficiencyUpgrade(generator, name, cost, efficiency);
    }

    public UpgradeBuilder addEfficiencyAutomatedUpgrade(String name, Amount cost, float efficiency) {
        return gardenBuilder.addUpgrade(generator, name, cost, efficiency, 1, true);
    }

    public UpgradeBuilder addSpeedUpgrade(String name, Amount cost, int speed) {
        return gardenBuilder.addSpeedUpgrade(generator, name, cost, speed);
    }

    public UpgradeBuilder addSpeedAutomatedUpgrade(String name, Amount cost, int speed) {
        return gardenBuilder.addUpgrade(generator, name, cost, 1, speed, true);
    }

    public UpgradeBuilder addAutomatedUpgrade(String name, Amount cost) {
        return gardenBuilder.addAutomatedUpgrade(generator, name, cost);
    }
}
