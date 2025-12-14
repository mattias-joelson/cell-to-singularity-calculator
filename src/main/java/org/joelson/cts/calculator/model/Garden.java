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
    private final Map<Unlockable, List<Requirement>> requirements;

    public Garden(String name) {
        this.name = name;
        this.currencies = new ArrayList<>();
        this.generators = new ArrayList<>();
        this.upgrades = new ArrayList<>();
        this.requirements = new HashMap<>();
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

    public List<Generator> getUnlockedGenerators(GardenState state) {
        return getUnlocked(generators, state);
    }

    public void addUpgrade(Upgrade upgrade) {
        upgrades.add(upgrade);
    }

    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    public List<Upgrade> getUnlockedUpgrades(GardenState state) {
        return getUnlocked(upgrades, state);
    }

    public void addRequirement(Unlockable unlockable, Requirement requirement) {
        requirements.computeIfAbsent(unlockable, _ -> new ArrayList<>()).add(requirement);
    }

    private <T extends Unlockable> List<T> getUnlocked(List<T> unlockables, GardenState state) {
        List<T> unlocked = new ArrayList<>();
        for (T unlockable : unlockables) {
            List<Requirement> requirements = this.requirements.get(unlockable);
            if (requirements == null || requirements.isEmpty()) {
                unlocked.add(unlockable);
                continue;
            }
            for (Requirement requirement : requirements) {
                if (!requirement.isFulfilled(state)) {
                    unlockable = null;
                    break;
                }
            }
            if (unlockable != null) {
                unlocked.add(unlockable);
            }
        }
        return unlocked;
    }
}
