package org.joelson.cts.calculator.model;

import java.util.ArrayList;
import java.util.List;

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

    public void updateEfficiency() {
        for (Generator generator : generators) {
            generator.updateEfficiency(this);
        }
    }
}
