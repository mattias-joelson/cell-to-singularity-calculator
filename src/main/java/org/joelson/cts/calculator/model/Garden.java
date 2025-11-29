package org.joelson.cts.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Garden {

    private final String name;
    private final Currency currency;
    private final List<Generator> generators;
    private final List<Upgrade> upgrades;

    public Garden(String name, Currency currency) {
        this.name = name;
        this.currency = currency;
        this.generators = new ArrayList<>();
        this.upgrades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Currency getCurrency() {
        return currency;
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
}
