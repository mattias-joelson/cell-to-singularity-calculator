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

    public UpgradeBuilder addUpgrade(String name, Amount cost, float efficiency) {
        return gardenBuilder.addGeneratorUpgrade(generator, name, cost, efficiency);
    }

    public UpgradeBuilder addUpgrade(String name, Amount cost, float efficiency, int speed) {
        return gardenBuilder.addGeneratorUpgrade(generator, name, cost, efficiency, speed);
    }

    public UpgradeBuilder addAutomatedUpgrade(String name, Amount cost) {
        return gardenBuilder.addGeneratorAutomationUpgrade(generator, name, cost);
    }
}
