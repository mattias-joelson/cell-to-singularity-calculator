package org.joelson.cts.calculator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Garden {

    private final String name;
    private final List<String> currencies;
    private final List<Generator> generators;
    private final List<Upgrade> upgrades;

    public Garden(String name) {
        this.name = name;
        this.currencies = new ArrayList<>();
        this.generators = new ArrayList<>();
        this.upgrades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCurrency(String currency) {
        currencies.add(currency);
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public void addGenerator(Generator generator) {
        generators.add(generator);
    }

    public List<Generator> getGenerators() {
        return generators;
    }

    public void addUpgrade(Upgrade upgrade) {
        upgrades.add(upgrade);
    }

    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    public void updateEfficiency(GardenState state) {
        Map<String, Float> generatorEfficiencies = new HashMap<>(generators.size());
        for (Generator generator : generators) {
            generatorEfficiencies.put(generator.name(), 1f);
        }
        for (Upgrade upgrade : upgrades) {
            if (state.isUpgradeBought(upgrade)) {
                for (UpgradeEffect effect : upgrade.getEffects()) {
                    generatorEfficiencies.compute(effect.getGenerator().name(),
                            (_, generatorEfficiency) -> generatorEfficiency * effect.getEfficiency());
                }
            }
        }
        for (Generator generator : generators) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            float efficiency = generatorEfficiencies.get(generator.name());
            state.setGeneratorState(generator, new GeneratorState(generatorState.count(), efficiency));
        }
    }
}
