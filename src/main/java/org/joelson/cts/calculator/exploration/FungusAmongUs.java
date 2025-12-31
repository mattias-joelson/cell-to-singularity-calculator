import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.CurrencyMapping;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorImprovement;
import org.joelson.cts.calculator.model.GeneratorState;
import org.joelson.cts.calculator.model.Improvement;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.ImprovementDescription;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.model.UpgradeImprovement;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

private static final Garden GARDEN = new Garden("Fungus Among Us");
private static final GardenState STATE = new GardenState();
private static final String CURRENCY = "Toadstools";

private static Amount fungus(float amount) {
    return new Amount(CURRENCY, amount);
}

private void setGeneratorCount(Generator generator, int count) {
    STATE.setGeneratorCount(generator, count);
}

void main() {

    GARDEN.addCurrency(CURRENCY);
    GardenBuilder builder = new GardenBuilder(GARDEN, STATE);

    Generator fungi = builder.createGenerator("Fungi", fungus(20), 1.15f, fungus(1))

            .addUpgrade("Mycology", fungus(600), 1.5f, false)
            .addGeneratorRequirement("Fungi", 1)

            .addUpgrade("Mushrooms", fungus(5_000), 11, false)
            .addGeneratorRequirement("Fungi", 1)

            .addUpgrade("Yeast", fungus(2e15f), 2e9f, false)
            .addGeneratorRequirement("Fungi", 1)
            .addGeneratorRequirement("Tasty Fungi", 1)

            .addUpgrade("Mold", fungus(2e18f), 251, false)
            .addGeneratorRequirement("Fungi", 1)
            .addUpgradeRequirement("Blue Cheese")
            .generator();

    Generator fungalLiving = builder.createGenerator("Fungal Living", fungus(700), 1.15f, fungus(5))
            .addUpgradeRequirement("Mycology")

            .addUpgrade("Diet", fungus(1_250), 2, false)
            .addGeneratorRequirement("Fungal Living", 1)

            .addUpgrade("Reproduction", fungus(2_000), 2, false)
            .addGeneratorRequirement("Fungal Living", 1)

            .addUpgrade("Hydrolytic Enzymes", fungus(50_000), 2, false)
            .addUpgradeRequirement("Diet")

            .addUpgrade("Spores", fungus(150_000), 3, false)
            .addUpgradeRequirement("Reproduction")

            .addUpgrade("Symbiosis", fungus(5e7f), 51, false)
            .addGeneratorRequirement("Fungal Living", 1)

            .addUpgrade("Growth", fungus(1e9f), 11, false)
            .addUpgradeRequirement("Reproduction")
            .generator();

    Generator fungalCleaners = builder.createGenerator("Fungal Cleaners", fungus(600_000), 1.15f, fungus(400))
            .addUpgradeRequirement("Hydrolytic Enzymes")

            .addUpgrade("Bioremediation", fungus(2e6f), 3, false)
            .addGeneratorRequirement("Fungal Cleaners", 1)

            .addUpgrade("Radiotrophic Fungi", fungus(1.5e7f), 4, false)
            .addUpgradeRequirement("Bioremediation")

            .addUpgrade("Decomposition", fungus(1.5e10f), 16, false)
            .addGeneratorRequirement("Fungal Cleaners", 1)

            .addUpgrade("Fungal Burial", fungus(1.5e11f), 26, false)
            .addUpgradeRequirement("Decomposition")
            .generator();

    Generator fungiOfTheForest = builder.createGenerator("Fungi of the Forest", fungus(3e8f), 1.15f, fungus(50_000))
            .addUpgradeRequirement("Symbiosis")

            .addUpgrade("Mycorrhiza", fungus(7e8f), 2, false)
            .addGeneratorRequirement("Fungi of the Forest", 1)

            .addUpgrade("Mycelial Network", fungus(9e9f), 2, false)
            .addGeneratorRequirement("Fungi of the Forest", 1)
            .addUpgradeRequirement("Growth")

            .addUpgrade("Ghost Orchid", fungus(4e10f), 2, false)
            .addGeneratorRequirement("Fungi of the Forest", 1)
            .addUpgradeRequirement("Decomposition")

            .addUpgrade("Defense Alert", fungus(8e10f), 2, false)
            .addUpgradeRequirement("Mycelial Network")
            .generator();

    Generator domesticatedFungi = builder.createGenerator("Domesticated Fungi", fungus(1.5e11f), 1.15f, fungus(6e6f))
            .addUpgradeRequirement("Fungal Burial")

            .addUpgrade("Building Material", fungus(6e11f), 4, false)
            .addGeneratorRequirement("Domesticated Fungi", 1)

            .addUpgrade("Pesticides", fungus(1.5e12f), 11, false)
            .addGeneratorRequirement("Domesticated Fungi", 1)

            .addUpgrade("Medicine", fungus(2e13f), 3, false)
            .addGeneratorRequirement("Domesticated Fungi", 1)

            .addUpgrade("Antibiotics", fungus(4e20f), 3e6f, false)
            .addUpgradeRequirement("Medicine")
            .addUpgradeRequirement("Mold")
            .generator();

    Generator tastyFungi = builder.createGenerator("Tasty Fungi", fungus(3.5e13f), 1.15f, fungus(5e9f))
            .addUpgradeRequirement("Medicine")

            .addUpgrade("Edible Mushrooms", fungus(1.2e14f), 3, false)
            .addGeneratorRequirement("Tasty Fungi", 1)

            .addUpgrade("Truffles", fungus(8e14f), 2.5f, false)
            .addUpgradeRequirement("Edible Mushrooms")

            .addUpgrade("Bread", fungus(4e16f), 5, false)
            .addUpgradeRequirement("Truffles")

            .addUpgrade("Fermentation", fungus(9e16f), 2, false)
            .addUpgradeRequirement("Truffles")

            .addUpgrade("Cheese Ripening", fungus(2.5e17f), 5, false)
            .addUpgradeRequirement("Fermentation")

            .addUpgrade("Blue Cheese", fungus(7.5e17f), 2.5f, false)
            .addUpgradeRequirement("Cheese Ripening")

            .addUpgrade("Alcohol", fungus(9e22f), 75_001, false)
            .addUpgradeRequirement("Fermentation")
            .generator();

    Generator unwelcomeFungi = builder.createGenerator("Unwelcome Fungi", fungus(1.2e19f), 1.15f, fungus(2.5e14f))
            .addUpgradeRequirement("Mold")

            .addUpgrade("Moldy Food", fungus(1.5e19f), 9, false)
            .addGeneratorRequirement("Unwelcome Fungi", 1)

            .addUpgrade("Fungal Infections", fungus(6e19f), 2.5f, false)
            .addGeneratorRequirement("Unwelcome Fungi", 1)

            .addUpgrade("Plant Blight", fungus(5e21f), 6, false)
            .addUpgradeRequirement("Fungal Infections")

            .addUpgrade("Black Mold", fungus(1.2e22f), 4, false)
            .addUpgradeRequirement("Moldy Food")

            .addUpgrade("Human Illness", fungus(4e22f), 2, false)
            .addUpgradeRequirement("Black Mold")
            .addUpgradeRequirement("Fungal Infections")

            .addUpgrade("Poisonous Mushrooms", fungus(3e24f), 41, false)
            .addGeneratorRequirement("Unwelcome Fungi", 1)

            .addUpgrade("Amanita", fungus(1e25f), 5, false)
            .addUpgradeRequirement("Poisonous Mushrooms")

            .addUpgrade("Parasitic Fungi", fungus(1e26f), 16, false)
            .addGeneratorRequirement("Unwelcome Fungi", 1)
            .generator();

    Generator mindAlteringFungi = builder.createGenerator("Mind-Altering Fungi", fungus(1e23f), 1.15f, fungus(5e18f))
            .addUpgradeRequirement("Alcohol")

            .addUpgrade("Scent & Taste", fungus(4e23f), 7, false)
            .addGeneratorRequirement("Mind-Altering Fungi", 1)

            .addUpgrade("Psychedelic Mushrooms", fungus(4e25f), 16, false)
            .addGeneratorRequirement("Mind-Altering Fungi", 1)
            .addUpgradeRequirement("Poisonous Mushrooms")

            .addUpgrade("Cordyceps", fungus(1e27f), 2, false)
            .addUpgradeRequirement("Parasitic Fungi")
            .generator();

    builder.resolveRequirements();
    STATE.updateEfficiency(GARDEN);
    //STATE.setBoost(2);

    setGeneratorCount(mindAlteringFungi, 0);
    setGeneratorCount(unwelcomeFungi, 0);
    setGeneratorCount(tastyFungi, 0);
    setGeneratorCount(domesticatedFungi, 0);
    setGeneratorCount(fungiOfTheForest, 0);
    setGeneratorCount(fungalCleaners, 0);
    setGeneratorCount(fungalLiving, 0);
    setGeneratorCount(fungi, 1);

    GardenState state = STATE.copy();
    CurrencyMapping mapping = new CurrencyMapping(CURRENCY, CURRENCY);
    List<String> actions = new ArrayList<>();
    printUnlocked(GARDEN, state, actions);
    for (int i = 0; i < 20; i += 1) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        ImprovementDescription improvementDescription =
                ImprovementCalculator.calculateImprovement(GARDEN, state).get(mapping);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println();
        Improvement improvement = improvementDescription.improvement();
        if (improvement instanceof GeneratorImprovement(Generator generator, GeneratorState generatorState)) {
            int count = generatorState.count();
            actions.add(String.format("(%d) Generator %s: %d -> %d : %s",
                    i + 1, generator.name(), count, count + 1, improvementDescription.description()));
            state.setGeneratorCount(generator, count + 1);
            if (count == 0) {
                printUnlocked(GARDEN, state, actions);
            }
        } else if (improvement instanceof UpgradeImprovement upgradeImprovement) {
            Upgrade upgrade = upgradeImprovement.upgrade();
            UpgradeEffect effect = upgrade.getEffects().getFirst();
            actions.add(String.format("(%d) Upgrade %s (%s) : %s",
                    i + 1, upgrade.getName(), effect.generator().name(), improvementDescription.description()));
            state.setUpgradeBought(upgrade);
            state.updateEfficiency(GARDEN);
            printUnlocked(GARDEN, state, actions);
        } else {
            throw new NullPointerException();
        }
    }

    actions.forEach(System.out::println);
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
