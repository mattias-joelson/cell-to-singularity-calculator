import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.CurrencyMapping;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorImprovement;
import org.joelson.cts.calculator.model.GeneratorState;
import org.joelson.cts.calculator.model.Improvement;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.model.UpgradeImprovement;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

private static final Garden GARDEN = new Garden("A Journey of Serenity");
private static final GardenState STATE = new GardenState();
private static final String LEAVES_CURRENCY = "Tea Leaves";
private static final String CUPS_CURRENCY = "Tea Cups";

private static Amount leaves(float amount) {
    return new Amount(LEAVES_CURRENCY, amount);
}

private static Amount cups(float amount) {
    return new Amount(CUPS_CURRENCY, amount);
}

private void setGeneratorCount(Generator generator, int count) {
    STATE.setGeneratorCount(generator, count);
}

void main() {

    GARDEN.addCurrency(LEAVES_CURRENCY);
    GARDEN.addCurrency(CUPS_CURRENCY);

    GardenBuilder builder = new GardenBuilder(GARDEN, STATE);

    Generator wildTeaPlant = builder.createGenerator("Wild Tea Plant", leaves(15), 1.13f, leaves(1))

            .addUpgrade("Cultivation", leaves(150), 2.25f, false)
            .addGeneratorRequirement("Wild Tea Plant", 1)

            .addUpgrade("Health Benefits", leaves(1_000), 2, false)
            .addUpgradeRequirement("Cultivation")

            .addUpgrade("Defense Response", leaves(2_500), 2, false)
            .addUpgradeRequirement("Health Benefits")

            .addUpgrade("Tea Meals", leaves(8_000), 2.25f, false)
            .addUpgradeRequirement("Health Benefits")

            .addUpgrade("Chagayu", leaves(200_000), 2, false)
            .addUpgradeRequirement("Tea Meals")
            .addGeneratorRequirement("Domesticated Tea Plant", 1)

            .addUpgrade("Herbal Medicine", leaves(400_000), 3, false)
            .addUpgradeRequirement("Chagayu")

            .addUpgrade("Ochazuke", leaves(2.5e12f), 100_001, false)
            .addUpgradeRequirement("Tea Meals")
            .addUpgradeRequirement("Anti-inflammatory")
            .generator();

    Generator domesticatedTeaPlant = builder.createGenerator("Domesticated Tea Plant", leaves(75_000), 1.15f, leaves(500))
            .addUpgradeRequirement("Cultivation")

            .addUpgrade("Origin Myth", leaves(2e6f), 2, false)
            .addGeneratorRequirement("Domesticated Tea Plant", 1)

            .addUpgrade("Calm Body and Mind", leaves(1e7f), 3, false)
            .addUpgradeRequirement("Herbal Medicine")

            .addUpgrade("Digestion", leaves(4e7f), 2.5f, false)
            .addUpgradeRequirement("Calm Body and Mind")

            .addUpgrade("Anti-inflammatory", leaves(7e8f), 21, false)
            .addUpgradeRequirement("Calm Body and Mind")

            .addUpgrade("Weight Management", leaves(2e13f), 1001, false)
            .addUpgradeRequirement("Cultivation")
            .addUpgradeRequirement("Anti-inflammatory")
            .generator();

    Generator teaPlantation = builder.createGenerator("Tea Plantation", leaves(5e7f), 1.15f, leaves(100_000))
            .addUpgradeRequirement("Origin Myth")

            .addUpgrade("Pruning", leaves(9e9f), 2.25f, false)
            .addGeneratorRequirement("Tea Plantation", 1)

            .addUpgrade("Harvesting", leaves(1.5e11f), 2.5f, false)
            .addUpgradeRequirement("Pruning")

            .addUpgrade("Scissors", leaves(4e11f), 2, false)
            .addUpgradeRequirement("Harvesting")

            .addUpgrade("Harvesting Machinery", leaves(6e12f), 2, false)
            .addUpgradeRequirement("Scissors")

            .addUpgrade("Soil Acidity", leaves(1e13f), 5, false)
            .addUpgradeRequirement("Harvesting Machinery")

            .addUpgrade("Fertilizer", leaves(9e13f), 2, false)
            .addUpgradeRequirement("Soil Acidity")

            .addUpgrade("Pest and Disease Control", leaves(9e14f), 4, false)
            .addUpgradeRequirement("Fertilizer")

            .addUpgrade("Vertical Farming", leaves(6e15f), 3.5f, false)
            .addUpgradeRequirement("Pest and Disease Control")

            .addUpgrade("Irrigation System", leaves(1.5e16f), 4, false)
            .addUpgradeRequirement("Harvesting Machinery")
            .addUpgradeRequirement("Vertical Farming")

            .addUpgrade("Mechanical Plucking", leaves(2e17f), 8.5f, false)
            .addUpgradeRequirement("Irrigation System")

            .addUpgrade("Drone Technology", leaves(8e17f), 11, false)
            .addUpgradeRequirement("Mechanical Plucking")

            .addUpgrade("Monitoring System", leaves(2e19f), 6, false)
            .addUpgradeRequirement("Harvesting Machinery") // ? Drone Tech

            .addUpgrade("Storing", leaves(3e8f), 2.5f, false)
            .addGeneratorRequirement("Tea Plantation", 1)

            .addUpgrade("Drying", leaves(3e10f), 2, false)
            .addUpgradeRequirement("Storing")

            .addUpgrade("Roasting", leaves(8e11f), 2.25f, false)
            .addUpgradeRequirement("Drying")

            .addUpgrade("Fermentation", leaves(3e14f), 3.5f, false)
            .addUpgradeRequirement("Grinding")
            .generator();

    Generator teaEvolution = builder.createGenerator("Tea Evolution", leaves(2e6f), 1.13f, cups(3f))
            .addUpgradeRequirement("Origin Myth")

            .addUpgrade("Tea Contest", cups(1_000), 5, false)
            .addGeneratorRequirement("Tea Evolution", 1)

            .addUpgrade("Silk Road Trade", cups(1e8f), 16, false)
            .addGeneratorRequirement("Matcha", 1)

            .addUpgrade("Arabic Shai", cups(2.5e8f), 3.5f, false)
            .addUpgradeRequirement("Silk Road Trade")

            .addUpgrade("Moroccan Atai", cups(4e8f), 3f, false)
            .addUpgradeRequirement("Silk Road Trade")

            .addUpgrade("AI Automation", leaves(9e19f), 20_000_000_000_001f, false)
            .addUpgradeRequirement("Monitoring System")
            .generator();

    Generator matcha = builder.createGenerator("Matcha", cups(1e6f), 1.15f, cups(200))
            .addGeneratorRequirement("Tea Evolution", 1)

            .addUpgrade("Whisking", cups(1.5e7f), 2f, false)
            .addGeneratorRequirement("Matcha", 1)

            .addUpgrade("Foam Art", cups(5e11f), 10_001, false)
            .addUpgradeRequirement("Whisking")
            .addUpgradeRequirement("Trade to Europe")

            .addUpgrade("Chanoyu", cups(5e12f), 16, false)
            .addUpgradeRequirement("Foam Art")

            .addUpgrade("Grinding", leaves(4e13f), 2.5f, false)
            .addGeneratorRequirement("Matcha", 1)
            .addUpgradeRequirement("Roasting")
            .generator();

    Generator looseLeafTea = builder.createGenerator("Loose-Leaf Tea", cups(3e8f), 1.15f, cups(25_000))
            .addUpgradeRequirement("Arabic Shai")
            .addUpgradeRequirement("Moroccan Atai")

            .addUpgrade("Steeping", cups(2.5e9f), 3, false)
            .addGeneratorRequirement("Loose-Leaf Tea", 1)

            .addUpgrade("Darye", cups(3e10f), 3.5f, false)
            .addUpgradeRequirement("Steeping")

            .addUpgrade("Trade to Europe", cups(8e10f), 4, false)
            .addGeneratorRequirement("Loose-Leaf Tea", 1)

            .addUpgrade("Yunnan Pu-erh Tea", leaves(5e15f), 5, false)
            .addGeneratorRequirement("Loose-Leaf Tea", 1)

            .addUpgrade("Tea Brick", leaves(5e16f), 51, false)
            .addUpgradeRequirement("Yunnan Pu-erh Tea")
            .addUpgradeRequirement("Fermentation")
            .generator();

    Generator infusedTea = builder.createGenerator("Infused Tea", cups(4e13f), 1.15f, cups(2e9f))
            .addUpgradeRequirement("Trade to Europe")

            .addUpgrade("British Tea", cups(1e14f), 9, false)
            .addGeneratorRequirement("Infused Tea", 1)

            .addUpgrade("Masala Chai", cups(1e15f), 2, false)
            .addGeneratorRequirement("Infused Tea", 1)

            .addUpgrade("Boiling", cups(7.5e15f), 4, false)
            .addUpgradeRequirement("Masala Chai")

            .addUpgrade("High Tea", cups(1.2e17f), 8.5f, false)
            .addUpgradeRequirement("British Tea")

            .addUpgrade("Tea House", cups(8e21f), 2_001, false)
            .addUpgradeRequirement("High Tea")
            .addGeneratorRequirement("Unconventional Tea", 1)

            .addUpgrade("Assam Tea", leaves(4e17f), 2, false)
            .addUpgradeRequirement("Masala Chai")

            .addUpgrade("Storage Jar", leaves(6e17f), 6, false)
            .addUpgradeRequirement("Assam Tea")
            .addUpgradeRequirement("Tea Brick")
            .generator();

    Generator unconventionalTea = builder.createGenerator("Unconventional Tea", cups(5e18f), 1.15f, cups(1e15f))
            .addUpgradeRequirement("Masala Chai")

            .addUpgrade("Iced Tea", cups(4e20f), 2, false)
            .addGeneratorRequirement("Unconventional Tea", 1)

            .addUpgrade("Cold Brew", cups(3e21f), 2.5f, false)
            .addUpgradeRequirement("Iced Tea")

            .addUpgrade("Herbal Tea", cups(7e22f), 6, false)
            .addUpgradeRequirement("Iced Tea")

            .addUpgrade("Tea Latte", cups(2e23f), 5, false)
            .addUpgradeRequirement("Herbal Tea")

            .addUpgrade("Tea Cocktail", cups(1.5e24f), 201f, false)
            .addUpgradeRequirement("Herbal Tea")

            .addUpgrade("Bubble Tea", cups(3e25f), 6, false)
            .addUpgradeRequirement("Tea Latte")

            .addUpgrade("Tea Bag", leaves(6e18f), 2.5f, false)
            .addGeneratorRequirement("Unconventional Tea", 1)
            .addUpgradeRequirement("Storage Jar")

            .addUpgrade("Vacuum Sealer", leaves(3e19f), 2.5f, false)
            .addUpgradeRequirement("Tea Bag")
            .generator();

    Generator virtualTea = builder.createGenerator("Virtual Tea", cups(5e22f), 1.15f, cups(4e18f))
            .addUpgradeRequirement("AI Automation")

            .addUpgrade("Tea Simulator", cups(5e23f), 4f, false)
            .addGeneratorRequirement("Virtual Tea", 1)

            .addUpgrade("Online Tea Cermony", cups(1e25f), 6f, false)
            .addGeneratorRequirement("Virtual Tea", 1)

            .addUpgrade("Shared Serenity", cups(1e26f), 2, false)
            .addUpgradeRequirement("Tea Simulator")
            .addUpgradeRequirement("Online Tea Cermony")
            .generator();

    builder.resolveRequirements();
    STATE.updateEfficiency(GARDEN);
    //STATE.setBoost(2);

    setGeneratorCount(virtualTea, 0);
    setGeneratorCount(unconventionalTea, 0);
    setGeneratorCount(infusedTea, 0);
    setGeneratorCount(looseLeafTea, 0);
    setGeneratorCount(matcha, 0);
    setGeneratorCount(teaEvolution, 0);
    setGeneratorCount(teaPlantation, 0);
    setGeneratorCount(domesticatedTeaPlant, 0);
    setGeneratorCount(wildTeaPlant, 1);

    GardenState state = STATE.copy();
    List<String> actions = new ArrayList<>();
    printUnlocked(GARDEN, state, actions);
    for (int i = 0; i < 200; i += 1) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Map<CurrencyMapping, Improvement> improvementMap = ImprovementCalculator.calculateImprovement(GARDEN, state);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println();
        for (Map.Entry<CurrencyMapping, Improvement> entry : improvementMap.entrySet()) {
            if (improvementMap.size() > 1) {
                actions.add(String.format(">>> %s <<<", entry.getKey()));
            }
            if (entry.getValue() instanceof GeneratorImprovement(Generator generator, GeneratorState generatorState)) {
                int count = generatorState.count();
                actions.add(String.format("(%d) Generator %s: %d -> %d", i + 1, generator.name(), count, count + 1));
                state.setGeneratorCount(generator, count + 1);
                if (count == 0) {
                    printUnlocked(GARDEN, state, actions);
                }
            } else if (entry.getValue() instanceof UpgradeImprovement upgradeImprovement) {
                Upgrade upgrade = upgradeImprovement.upgrade();
                UpgradeEffect effect = upgrade.getEffects().getFirst();
                actions.add(String.format("(%d) Upgrade %s (%s)", i + 1, upgrade.getName(), effect.generator().name()));
                state.setUpgradeBought(upgrade);
                state.updateEfficiency(GARDEN);
                printUnlocked(GARDEN, state, actions);
            } else {
                throw new NullPointerException();
            }
        }
        if (improvementMap.size() > 1) {
            break;
        }
    }

    actions.forEach(System.out::println);
}

private static void printUnlocked(Garden garden, GardenState state, List<String> actions) {
    for (Generator generator : garden.getUnlockedGenerators(state).reversed()) {
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
