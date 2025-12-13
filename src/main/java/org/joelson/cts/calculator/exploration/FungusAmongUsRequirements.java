import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorImprovement;
import org.joelson.cts.calculator.model.GeneratorRequirement;
import org.joelson.cts.calculator.model.Improvement;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.Requirement;
import org.joelson.cts.calculator.model.Unlockable;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.model.UpgradeImprovement;
import org.joelson.cts.calculator.model.UpgradeRequirement;

private interface FutureRequirement {

}

private record FutureGeneratorRequirement(Unlockable unlockable, String generatorName, int count)
        implements FutureRequirement {

}

private record FutureUpgradeRequirement(Unlockable unlockable, String upgradeName) implements FutureRequirement {

}

private record GeneratorBuilder(Generator generator) {

    private GeneratorBuilder addGeneratorRequirement(String requiredGeneratorName, int count) {
        addFutureRequirement(generator, requiredGeneratorName, count);
        return this;
    }

    private GeneratorBuilder addUpgradeRequirement(String requiredUpgradeName) {
        addFutureRequirement(generator, requiredUpgradeName);
        return this;
    }

    private UpgradeBuilder addUpgrade(String name, float cost, float efficiency, boolean bought) {
        return addGeneratorUpgrade(generator, name, cost, efficiency, bought);
    }
}

private record UpgradeBuilder(Generator generator, Upgrade upgrade) {

    private UpgradeBuilder addGeneratorRequirement(String requiredGeneratorName, int count) {
        addFutureRequirement(upgrade, requiredGeneratorName, count);
        return this;
    }

    private UpgradeBuilder addUpgradeRequirement(String requiredUpgradeName) {
        addFutureRequirement(upgrade, requiredUpgradeName);
        return this;
    }

    private UpgradeBuilder addUpgrade(String name, float cost, float efficiency, boolean bought) {
        return addGeneratorUpgrade(generator, name, cost, efficiency, bought);
    }
}

private static final Garden GARDEN = new Garden("Fungus Among Us");
private static final GardenState STATE = new GardenState();
private static final String CURRENCY = "Toadstools";
private static final Map<String, List<FutureRequirement>> FUTURE_REQUIREMENTS = new HashMap<>();

private static Amount fungus(float amount) {
    return new Amount(CURRENCY, amount);
}

private static GeneratorBuilder createGenerator(
        String name, float baseCost, float compoundingCost, float baseProduction) {
    Generator generator = new Generator(name, fungus(baseCost), compoundingCost, fungus(baseProduction));
    GARDEN.addGenerator(generator);
    return new GeneratorBuilder(generator);
}

private static UpgradeBuilder addGeneratorUpgrade(
        Generator generator, String name, float cost, float efficiency, boolean bought) {
    Upgrade upgrade = new Upgrade(name, fungus(cost));
    upgrade.addEffect(new UpgradeEffect(generator, efficiency));
    GARDEN.addUpgrade(upgrade);
    STATE.setUpgradeBought(upgrade, bought);
    return new UpgradeBuilder(generator, upgrade);
}

private static void addFutureRequirement(Unlockable unlockable, String generatorName, int count) {
    FUTURE_REQUIREMENTS.computeIfAbsent(generatorName, _ -> new ArrayList<>()).add(
            new FutureGeneratorRequirement(unlockable, generatorName, count));
}

private static void addFutureRequirement(Unlockable unlockable, String upgradeName) {
    FUTURE_REQUIREMENTS.computeIfAbsent(upgradeName, _ -> new ArrayList<>()).add(
            new FutureUpgradeRequirement(unlockable, upgradeName));
}

private static void resolveRequirements() {
    Map<String, Unlockable> unlockableMap = new HashMap<>();
    for (Generator generator : GARDEN.getGenerators()) {
        if (unlockableMap.containsKey(generator.name())) {
            throw new IllegalStateException("unlockableMap already contains \"" + generator.name() + "\"");
        }
        unlockableMap.put(generator.name(), generator);
    }
    for (Upgrade upgrade : GARDEN.getUpgrades()) {
        if (unlockableMap.containsKey(upgrade.getName())) {
            throw new IllegalStateException("unlockableMap already contains \"" + upgrade.getName() + "\"");
        }
        unlockableMap.put(upgrade.getName(), upgrade);
    }
    for (Map.Entry<String, List<FutureRequirement>> entry : FUTURE_REQUIREMENTS.entrySet()) {
        FutureRequirement first = entry.getValue().getFirst();
        if (first instanceof FutureGeneratorRequirement) {
            for (FutureRequirement futureRequirement : entry.getValue()) {
                if (futureRequirement instanceof FutureGeneratorRequirement(Unlockable unlockable, String generatorName,
                        int count)) {
                    Generator generator = (Generator) unlockableMap.get(generatorName);
                    if (generator == null) {
                        throw new IllegalStateException("No generator \"" + generatorName + "\" present for \"" + unlockable + ".");
                    }
                    GARDEN.addRequirement(unlockable,
                            new GeneratorRequirement(generator, count));
                } else {
                    throw new IllegalStateException(
                            "futureRequirement is of type " + futureRequirement.getClass().getName());
                }
            }
        } else if (first instanceof FutureUpgradeRequirement) {
            for (FutureRequirement futureRequirement : entry.getValue()) {
                if (futureRequirement instanceof FutureUpgradeRequirement(Unlockable unlockable, String upgradeName)) {
                    Upgrade upgrade = (Upgrade) unlockableMap.get(upgradeName);
                    if (upgrade == null) {
                        throw new IllegalStateException("No upgrade \"" + upgradeName + "\" present for \"" + unlockable + ".");
                    }
                    GARDEN.addRequirement(unlockable, new UpgradeRequirement(upgrade));
                } else {
                    throw new IllegalStateException(
                            "futureRequirement is of type " + futureRequirement.getClass().getName());
                }
            }
        }
    }
}

private static void addRequirement(Unlockable unlockable, String requiredGeneratorName, int count) {
    addRequirement(unlockable, createGeneratorRequirement(requiredGeneratorName, count));
}

private static void addRequirement(Unlockable unlockable, String requiredUpgradeName) {
    addRequirement(unlockable, createUpgradeRequirement(requiredUpgradeName));
}

private static void addRequirement(Unlockable unlockable, Requirement requirement) {
    GARDEN.addRequirement(unlockable, requirement);
}

private static void addRequirementOnUpgrade(Upgrade upgrade, String requiredGeneratorName, int count) {

}

private static void addRequirementOnUpgrade(Upgrade upgrade, String requiredUpgradeName) {
}

private static GeneratorRequirement createGeneratorRequirement(String requiredGeneratorName, int count) {
    for (Generator generator : GARDEN.getGenerators()) {
        if (generator.name().equals(requiredGeneratorName)) {
            return new GeneratorRequirement(generator, count);
        }
    }
    throw new IllegalArgumentException("Unknown generator \"" + requiredGeneratorName + "\"");
}

private static UpgradeRequirement createUpgradeRequirement(String requiredUpgradeName) {
    for (Upgrade upgrade : GARDEN.getUpgrades()) {
        if (upgrade.getName().equals(requiredUpgradeName)) {
            return new UpgradeRequirement(upgrade);
        }
    }
    throw new IllegalArgumentException("Unknown upgrade \"" + requiredUpgradeName + "\"");
}

private void setGeneratorCount(Generator generator, int count) {
    STATE.setGeneratorCount(generator, count);
}

void main() {

    GARDEN.addCurrency(CURRENCY);

    Generator fungi = createGenerator("Fungi", 20, 1.15f, 1)

            .addUpgrade("Mycology", 600, 1.5f, false) // Fungi
            .addGeneratorRequirement("Fungi", 1)

            .addUpgrade("Mushrooms", 5_000, 11, false) // Fungi
            .addGeneratorRequirement("Fungi", 1)

            .addUpgrade("Yeast", 2e15f, 2e9f, false) // Tasty Fungi / Fungi
            .addGeneratorRequirement("Fungi", 1)
            .addGeneratorRequirement("Tasty Fungi", 1)

            .addUpgrade("Mold", 2e18f, 251, false) // Blue Cheese / Fungi
            .addGeneratorRequirement("Fungi", 1)
            .addUpgradeRequirement("Blue Cheese")
            .generator();

    Generator fungualLiving = createGenerator("Fungal Living", 700, 1.15f, 5) // Mycology
            .addUpgradeRequirement("Mycology")

            .addUpgrade("Diet", 1_250, 2, false)  // Fungal Living
            .addGeneratorRequirement("Fungal Living", 1)

            .addUpgrade("Reproduction", 2_000, 2, false) // Fungal Living
            .addGeneratorRequirement("Fungal Living", 1)

            .addUpgrade("Hydrolytic Enzymes", 50_000, 2, false) // Diet
            .addUpgradeRequirement("Diet")

            .addUpgrade("Spores", 150_000, 3, false) // Reproduction
            .addUpgradeRequirement("Reproduction")

            .addUpgrade("Symbiosis", 5e7f, 51, false) // Fungal Living
            .addGeneratorRequirement("Fungal Living", 1)

            .addUpgrade("Growth", 1e9f, 11, false) // Reproduction
            .addUpgradeRequirement("Reproduction")
            .generator();

    Generator fungalCleaners = createGenerator("Fungal Cleaners", 600_000, 1.15f, 400) // Hydrolytic Enzymes
            .addUpgradeRequirement("Hydrolytic Enzymes")

            .addUpgrade("Bioremediation", 2e6f, 3, false) // cleaners
            .addGeneratorRequirement("Fungal Cleaners", 1)

            .addUpgrade("Radiotrophic Fungi", 1.5e7f, 4, false) // bioremediation
            .addUpgradeRequirement("Bioremediation")

            .addUpgrade("Decomposition", 1.5e10f, 16, false) // cleaners
            .addGeneratorRequirement("Fungal Cleaners", 1)

            .addUpgrade("Fungal Burial", 1.5e11f, 26, false) // decomposition
            .addUpgradeRequirement("Decomposition")
            .generator();

    Generator fungiOfTheForest = createGenerator("Fungi of the Forest", 3e8f, 1.15f, 50_000) // Symbiosis
            .addUpgradeRequirement("Symbiosis")

            .addUpgrade("Mycorrhiza", 7e8f, 2, false) // fungi of the forest
            .addGeneratorRequirement("Fungi of the Forest", 1)

            .addUpgrade("Mycelial Network", 9e9f, 2, false) // growth / fungi of the forest
            .addGeneratorRequirement("Fungi of the Forest", 1)
            .addUpgradeRequirement("Growth")

            .addUpgrade("Ghost Orchid", 4e10f, 2, false) // decomposition / fungi of the forest
            .addGeneratorRequirement("Fungi of the Forest", 1)
            .addUpgradeRequirement("Decomposition")

            .addUpgrade("Defense Alert", 8e10f, 2, false) // mycelial network
            .addUpgradeRequirement("Mycelial Network")
            .generator();

    Generator domesticatedFungi = createGenerator("Domesticated Fungi", 1.5e11f, 1.15f, 6e6f) // fungal burial
            .addUpgradeRequirement("Fungal Burial")

            .addUpgrade("Building Material", 6e11f, 4, false) // dom fun
            .addGeneratorRequirement("Domesticated Fungi", 1)

            .addUpgrade("Pesticides", 1.5e12f, 11, false) // dom fun
            .addGeneratorRequirement("Domesticated Fungi", 1)

            .addUpgrade("Medicine", 2e13f, 3, false) // dom fun
            .addGeneratorRequirement("Domesticated Fungi", 1)

            .addUpgrade("Antibiotics", 4e20f, 3e6f, false) // medicine, mold
            .addUpgradeRequirement("Medicine")
            .addUpgradeRequirement("Mold")
            .generator();

    Generator tastyFungi = createGenerator("Tasty Fungi", 3.5e13f, 1.15f, 5e9f) // medicine
            .addUpgradeRequirement("Medicine")

            .addUpgrade("Edible Mushrooms", 1.2e14f, 3, false) // tasty fungi
            .addGeneratorRequirement("Tasty Fungi", 1)

            .addUpgrade("Truffles", 8e14f, 2.5f, false) // edible
            .addUpgradeRequirement("Edible Mushrooms")

            .addUpgrade("Bread", 4e16f, 5, false) // Truffles
            .addUpgradeRequirement("Truffles")

            .addUpgrade("Fermentation", 9e16f, 2, false) // Truffles
            .addUpgradeRequirement("Truffles")

            .addUpgrade("Cheese Ripening", 2.5e17f, 5, false) // Fermentation
            .addUpgradeRequirement("Fermentation")

            .addUpgrade("Blue Cheese", 7.5e17f, 2.5f, false) // Cheese Ripening
            .addUpgradeRequirement("Cheese Ripening")

            .addUpgrade("Alcohol", 9e22f, 75_001, false) // Fermentation
            .addUpgradeRequirement("Fermentation")
            .generator();

    Generator unwelcomeFungi = createGenerator("Unwelcome Fungi", 1.2e19f, 1.15f, 2.5e14f) // Mold
            .addUpgradeRequirement("Mold")

            .addUpgrade("Moldy Food", 1.5e19f, 9, false) // Unwelcome Fungi
            .addGeneratorRequirement("Unwelcome Fungi", 1)

            .addUpgrade("Fungal Infections", 6e19f, 2.5f, false) // Unwelcome Fungi
            .addGeneratorRequirement("Unwelcome Fungi", 1)

            .addUpgrade("Plant Blight", 5e21f, 6, false) // Fungal Infection
            .addUpgradeRequirement("Fungal Infections")

            .addUpgrade("Black Mold", 1.2e22f, 4, false) // Moldy food
            .addUpgradeRequirement("Moldy Food")

            .addUpgrade("Human Illness", 4e22f, 2, false) // Black Mol, Fungal infectios
            .addUpgradeRequirement("Black Mold")
            .addUpgradeRequirement("Fungal Infections")

            .addUpgrade("Poisonous Mushrooms", 3e24f, 41, false) // Unwelcome Fungi
            .addGeneratorRequirement("Unwelcome Fungi", 1)

            .addUpgrade("Amanita", 1e25f, 5, false) // Poisonous Mushrooms
            .addUpgradeRequirement("Poisonous Mushrooms")

            .addUpgrade("Parasitic Fungi", 1e26f, 16, false) // Unwelcome Fungi
            .addGeneratorRequirement("Unwelcome Fungi", 1)
            .generator();

    Generator mindAlteringFungi = createGenerator("Mind-Altering Fungi", 1e23f, 1.15f, 5e18f) // alcohol
            .addUpgradeRequirement("Alcohol")

            .addUpgrade("Scent & Taste", 4e23f, 7, false) // Mind-Altering
            .addGeneratorRequirement("Mind-Altering Fungi", 1)

            .addUpgrade("Psychedelic Mushrooms", 4e25f, 16, false) // Mind-Altering, poisonous
            .addGeneratorRequirement("Mind-Altering Fungi", 1)
            .addUpgradeRequirement("Poisonous Mushrooms")

            .addUpgrade("Cordyceps", 1e27f, 2, false) // parasitic fungi
            .addUpgradeRequirement("Parasitic Fungi")
            .generator();

    resolveRequirements();
    STATE.updateEfficiency(GARDEN);
    STATE.setBoost(2);

    for (Generator generator : GARDEN.getGenerators()) {
        System.out.printf("Generator %s%n", generator.name());
    }
    System.out.println();
    for (Generator generator : GARDEN.getUnlockedGenerators(STATE)) {
        System.out.printf("Unlocked generator %s%n", generator.name());
    }
    System.out.println();

    for (Upgrade upgrade : GARDEN.getUpgrades()) {
        System.out.printf("Upgrade %s%n", upgrade.getName());
    }
    System.out.println();
    for (Upgrade upgrade : GARDEN.getUnlockedUpgrades(STATE)) {
        System.out.printf("Unlocked upgrade %s%n", upgrade.getName());
    }
    System.out.println();

    setGeneratorCount(mindAlteringFungi, 0);
    setGeneratorCount(unwelcomeFungi, 0);
    setGeneratorCount(tastyFungi, 0);
    setGeneratorCount(domesticatedFungi, 0);
    setGeneratorCount(fungiOfTheForest, 0);
    setGeneratorCount(fungalCleaners, 0);
    setGeneratorCount(fungualLiving, 0);
    setGeneratorCount(fungi, 1);

    GardenState state = STATE.copy();
    List<String> actions = new ArrayList<>();
    for (int i = 0; i < 20; i += 1) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Improvement improvement = ImprovementCalculator.calculateImprovement(GARDEN, state);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println();
        if (improvement instanceof GeneratorImprovement generatorImprovement) {
            Generator generator = generatorImprovement.generator();
            int count = generatorImprovement.state().count();
            actions.add(String.format("(%d) Generator %s: %d -> %d", i + 1, generator.name(), count, count + 1));
            state.setGeneratorCount(generator, count + 1);
            if (count == 0) {
                printUnlocked(GARDEN, state, actions);
            }
        } else if (improvement instanceof UpgradeImprovement upgradeImprovement) {
            Upgrade upgrade = upgradeImprovement.upgrade();
            UpgradeEffect effect = upgrade.getEffects().getFirst();
            actions.add(String.format("(%d) Upgrade %s (%s)", i + 1, upgrade.getName(), effect.generator().name()));
            state.setUpgradeBought(upgrade);
            STATE.updateEfficiency(GARDEN);
            printUnlocked(GARDEN, state, actions);
        } else {
            throw new NullPointerException();
        }
    }

    actions.stream().forEach(System.out::println);
}

private static void printUnlocked(Garden garden, GardenState state, List<String> actions) {
    for(Generator generator : garden.getUnlockedGenerators(state).reversed()) {
        if (state.getGeneratorState(generator).count() == 0) {
            actions.add(String.format(" *** unlocked generator %s", generator.name()));
        }
    }
    for (Upgrade upgrade : garden.getUnlockedUpgrades(state)) {
        if (!state.isUpgradeBought(upgrade)) {
            actions.add(String.format(" *** unlocked upgrade %s", upgrade.getName()));
        }
    }
}