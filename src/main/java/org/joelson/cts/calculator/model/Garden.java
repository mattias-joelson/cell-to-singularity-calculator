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

    public static void generateGeneratorUpgradesNames(Garden garden) {
        for (Generator generator : garden.getGenerators()) {
            for (Upgrade upgrade : garden.getUpgrades()) {
                for (UpgradeEffect effect : upgrade.getEffects()) {
                    if (effect.generator() == generator) {
                        String currency =
                                (generator.getBaseProduction().currency().equals(upgrade.getCost().currency()))
                                        ? ""
                                        : String.format("// %s ", upgrade.getCost().currency().toLowerCase());
                        System.out.printf("\"%s\", %s// check%n", upgrade.getName(), currency);
                    }
                }
            }
            System.out.println();
        }
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
        checkExistingNames(generator.getName());
        generators.add(generator);
    }

    public List<Generator> getGenerators() {
        return generators;
    }

    public Generator getGenerator(String name) {
        for (Generator generator : generators) {
            if (generator.getName().equals(name)) {
                return generator;
            }
        }
        return null;
    }

    public List<Generator> getUnlockedGenerators(GardenState state) {
        return getUnlocked(generators, state);
    }

    public void addUpgrade(Upgrade upgrade) {
        checkExistingNames(upgrade.getName());
        upgrades.add(upgrade);
    }

    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    public Upgrade getUpgrade(String name) {
        for (Upgrade upgrade : upgrades) {
            if (upgrade.getName().equals(name)) {
                return upgrade;
            }
        }
        return null;
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

    private void checkExistingNames(String name) {
        for (Generator generator : generators) {
            if (generator.getName().equals(name)) {
                throw new IllegalStateException("There already exists a generator named " + name + ".");
            }
        }
        for (Upgrade upgrade : upgrades) {
            if (upgrade.getName().equals(name)) {
                throw new IllegalStateException("There already exists an upgrade named " + name + ".");
            }
        }
    }

    public void swapGenerators(Generator oldGenerator, Generator newGenerator) {
        List<Generator> generators = getGenerators();
        for (int i = 0; i < generators.size(); i += 1) {
            if (generators.get(i).equals(oldGenerator)) {
                generators.set(i, newGenerator);
                for (Upgrade upgrade : getUpgrades()) {
                    List<UpgradeEffect> effects = upgrade.getEffects();
                    for (UpgradeEffect effect : effects) {
                        if (effect.generator().equals(oldGenerator)) {
                            effects.remove(effect);
                            effects.add(new UpgradeEffect(newGenerator, effect.efficiency(), effect.speed(),
                                    effect.automated()));
                            break;
                        }
                    }
                }
                return;
            }
        }
        throw new IllegalStateException("No old generator found: " + oldGenerator.getName());
    }
}
