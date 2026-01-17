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

    public UpgradeBuilder addSpeedUpgrade(String name, Amount cost, int speed) {
        return gardenBuilder.addSpeedUpgrade(generator, name, cost, speed);
    }

    public UpgradeBuilder addAutomatedUpgrade(String name, Amount cost) {
        return gardenBuilder.addAutomatedUpgrade(generator, name, cost);
    }
}
