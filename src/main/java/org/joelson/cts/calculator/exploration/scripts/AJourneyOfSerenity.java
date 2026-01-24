import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.CurrencyMapping;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorImprovement;
import org.joelson.cts.calculator.model.GeneratorState;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.ImprovementDescription;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.model.UpgradeImprovement;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

private static final Garden GARDEN = new Garden("A Journey of Serenity");
private static final GardenState STATE = new GardenState();
private static final String LEAVES_CURRENCY = "Tea Leaves";
private static final String CUPS_CURRENCY = "Tea Cups";

private static Amount leaves(double amount) {
    return new Amount(LEAVES_CURRENCY, amount);
}

private static Amount cups(double amount) {
    return new Amount(CUPS_CURRENCY, amount);
}

private void setGeneratorCount(Generator generator, int count) {
    STATE.setGeneratorCount(generator, count);
}

void main() {

    GARDEN.addCurrency(LEAVES_CURRENCY);
    GARDEN.addCurrency(CUPS_CURRENCY);

    GardenBuilder builder = new GardenBuilder(GARDEN);

    Generator wildTeaPlant = builder.createGenerator("Wild Tea Plant", leaves(15), 1.13f, leaves(1))

            .addEfficiencyUpgrade("Cultivation", leaves(150), 2.25f)
            .addGeneratorRequirement("Wild Tea Plant", 1)

            .addEfficiencyUpgrade("Health Benefits", leaves(1_000), 2)
            .addUpgradeRequirement("Cultivation")

            .addEfficiencyUpgrade("Defense Response", leaves(2_500), 2)
            .addUpgradeRequirement("Health Benefits")

            .addEfficiencyUpgrade("Tea Meals", leaves(8_000), 2.25f)
            .addUpgradeRequirement("Health Benefits")

            .addEfficiencyUpgrade("Chagayu", leaves(200_000), 2)
            .addUpgradeRequirement("Tea Meals")
            .addGeneratorRequirement("Domesticated Tea Plant", 1)

            .addEfficiencyUpgrade("Herbal Medicine", leaves(400_000), 3)
            .addUpgradeRequirement("Chagayu")

            .addEfficiencyUpgrade("Ochazuke", leaves(2.5e12), 100_001)
            .addUpgradeRequirement("Tea Meals")
            .addUpgradeRequirement("Anti-inflammatory")
            .generator();

    Generator domesticatedTeaPlant = builder.createGenerator("Domesticated Tea Plant", leaves(75_000), 1.15f,
                    leaves(500))
            .addUpgradeRequirement("Cultivation")

            .addEfficiencyUpgrade("Origin Myth", leaves(2e6), 2)
            .addGeneratorRequirement("Domesticated Tea Plant", 1)

            .addEfficiencyUpgrade("Calm Body and Mind", leaves(1e7), 3)
            .addUpgradeRequirement("Herbal Medicine")

            .addEfficiencyUpgrade("Digestion", leaves(4e7), 2.5f)
            .addUpgradeRequirement("Calm Body and Mind")

            .addEfficiencyUpgrade("Anti-inflammatory", leaves(7e8), 21)
            .addUpgradeRequirement("Calm Body and Mind")

            .addEfficiencyUpgrade("Weight Management", leaves(2e13), 1001)
            .addUpgradeRequirement("Cultivation")
            .addUpgradeRequirement("Anti-inflammatory")
            .generator();

    Generator teaPlantation = builder.createGenerator("Tea Plantation", leaves(5e7), 1.15f, leaves(100_000))
            .addUpgradeRequirement("Origin Myth")

            .addEfficiencyUpgrade("Pruning", leaves(9e9), 2.25f)
            .addGeneratorRequirement("Tea Plantation", 1)

            .addEfficiencyUpgrade("Harvesting", leaves(1.5e11), 2.5f)
            .addUpgradeRequirement("Pruning")

            .addEfficiencyUpgrade("Scissors", leaves(4e11), 2)
            .addUpgradeRequirement("Harvesting")

            .addEfficiencyUpgrade("Harvesting Machinery", leaves(6e12), 2)
            .addUpgradeRequirement("Scissors")

            .addEfficiencyUpgrade("Soil Acidity", leaves(1e13), 5)
            .addUpgradeRequirement("Harvesting Machinery")

            .addEfficiencyUpgrade("Fertilizer", leaves(9e13), 2)
            .addUpgradeRequirement("Soil Acidity")

            .addEfficiencyUpgrade("Pest and Disease Control", leaves(9e14), 4)
            .addUpgradeRequirement("Fertilizer")

            .addEfficiencyUpgrade("Vertical Farming", leaves(6e15), 3.5f)
            .addUpgradeRequirement("Pest and Disease Control")

            .addEfficiencyUpgrade("Irrigation System", leaves(1.5e16), 4)
            .addUpgradeRequirement("Harvesting Machinery")
            .addUpgradeRequirement("Vertical Farming")

            .addEfficiencyUpgrade("Mechanical Plucking", leaves(2e17), 8.5f)
            .addUpgradeRequirement("Irrigation System")

            .addEfficiencyUpgrade("Drone Technology", leaves(8e17), 11)
            .addUpgradeRequirement("Mechanical Plucking")

            .addEfficiencyUpgrade("Monitoring System", leaves(2e19), 6)
            .addUpgradeRequirement("Drone Technology") // ? Drone Tech

            .addEfficiencyUpgrade("Storing", leaves(3e8), 2.5f)
            .addGeneratorRequirement("Tea Plantation", 1)

            .addEfficiencyUpgrade("Drying", leaves(3e10), 2)
            .addUpgradeRequirement("Storing")

            .addEfficiencyUpgrade("Roasting", leaves(8e11), 2.25f)
            .addUpgradeRequirement("Drying")

            .addEfficiencyUpgrade("Fermentation", leaves(3e14), 3.5f)
            .addUpgradeRequirement("Grinding")
            .generator();

    Generator teaEvolution = builder.createGenerator("Tea Evolution", leaves(2e6), 1.13f, cups(3))
            .addUpgradeRequirement("Origin Myth")

            .addEfficiencyUpgrade("Tea Contest", cups(1_000), 5)
            .addGeneratorRequirement("Tea Evolution", 1)

            .addEfficiencyUpgrade("Silk Road Trade", cups(1e8), 16)
            .addGeneratorRequirement("Matcha", 1)

            .addEfficiencyUpgrade("Arabic Shai", cups(2.5e8), 3.5f)
            .addUpgradeRequirement("Silk Road Trade")

            .addEfficiencyUpgrade("Moroccan Atai", cups(4e8), 3f)
            .addUpgradeRequirement("Silk Road Trade")

            .addEfficiencyUpgrade("AI Automation", leaves(9e19), 20_000_000_000_001f)
            .addUpgradeRequirement("Monitoring System")
            .generator();

    Generator matcha = builder.createGenerator("Matcha", cups(1e6), 1.15f, cups(200))
            .addGeneratorRequirement("Tea Evolution", 1)

            .addEfficiencyUpgrade("Whisking", cups(1.5e7), 2f)
            .addGeneratorRequirement("Matcha", 1)

            .addEfficiencyUpgrade("Foam Art", cups(5e11), 10_001)
            .addUpgradeRequirement("Whisking")
            .addUpgradeRequirement("Trade to Europe")

            .addEfficiencyUpgrade("Chanoyu", cups(5e12), 16)
            .addUpgradeRequirement("Foam Art")

            .addEfficiencyUpgrade("Grinding", leaves(4e13), 2.5f)
            .addGeneratorRequirement("Matcha", 1)
            .addUpgradeRequirement("Roasting")
            .generator();

    Generator looseLeafTea = builder.createGenerator("Loose-Leaf Tea", cups(3e8), 1.15f, cups(25_000))
            .addUpgradeRequirement("Arabic Shai")
            .addUpgradeRequirement("Moroccan Atai")

            .addEfficiencyUpgrade("Steeping", cups(2.5e9), 3)
            .addGeneratorRequirement("Loose-Leaf Tea", 1)

            .addEfficiencyUpgrade("Darye", cups(3e10), 3.5f)
            .addUpgradeRequirement("Steeping")

            .addEfficiencyUpgrade("Trade to Europe", cups(8e10), 4)
            .addGeneratorRequirement("Loose-Leaf Tea", 1)

            .addEfficiencyUpgrade("Yunnan Pu-erh Tea", leaves(5e15), 5)
            .addGeneratorRequirement("Loose-Leaf Tea", 1)

            .addEfficiencyUpgrade("Tea Brick", leaves(5e16), 51)
            .addUpgradeRequirement("Yunnan Pu-erh Tea")
            .addUpgradeRequirement("Fermentation")
            .generator();

    Generator infusedTea = builder.createGenerator("Infused Tea", cups(4e13), 1.15f, cups(2e9))
            .addUpgradeRequirement("Trade to Europe")

            .addEfficiencyUpgrade("British Tea", cups(1e14), 9)
            .addGeneratorRequirement("Infused Tea", 1)

            .addEfficiencyUpgrade("Masala Chai", cups(1e15), 2)
            .addGeneratorRequirement("Infused Tea", 1)

            .addEfficiencyUpgrade("Boiling", cups(7.5e15), 4)
            .addUpgradeRequirement("Masala Chai")

            .addEfficiencyUpgrade("High Tea", cups(1.2e17), 8.5f)
            .addUpgradeRequirement("British Tea")

            .addEfficiencyUpgrade("Tea House", cups(8e21), 2_001)
            .addUpgradeRequirement("High Tea")
            .addGeneratorRequirement("Unconventional Tea", 1)

            .addEfficiencyUpgrade("Assam Tea", leaves(4e17), 2)
            .addUpgradeRequirement("Masala Chai")

            .addEfficiencyUpgrade("Storage Jar", leaves(6e17), 6)
            .addUpgradeRequirement("Assam Tea")
            .addUpgradeRequirement("Tea Brick")
            .generator();

    Generator unconventionalTea = builder.createGenerator("Unconventional Tea", cups(5e18), 1.15f, cups(1e15))
            .addUpgradeRequirement("Masala Chai")

            .addEfficiencyUpgrade("Iced Tea", cups(4e20), 2)
            .addGeneratorRequirement("Unconventional Tea", 1)

            .addEfficiencyUpgrade("Cold Brew", cups(3e21), 2.5f)
            .addUpgradeRequirement("Iced Tea")

            .addEfficiencyUpgrade("Herbal Tea", cups(7e22), 6)
            .addUpgradeRequirement("Iced Tea")

            .addEfficiencyUpgrade("Tea Latte", cups(2e23), 5)
            .addUpgradeRequirement("Herbal Tea")

            .addEfficiencyUpgrade("Tea Cocktail", cups(1.5e24), 201f)
            .addUpgradeRequirement("Herbal Tea")

            .addEfficiencyUpgrade("Bubble Tea", cups(3e25), 6)
            .addUpgradeRequirement("Tea Latte")

            .addEfficiencyUpgrade("Tea Bag", leaves(6e18), 2.5f)
            .addGeneratorRequirement("Unconventional Tea", 1)
            .addUpgradeRequirement("Storage Jar")

            .addEfficiencyUpgrade("Vacuum Sealer", leaves(3e19), 2.5f)
            .addUpgradeRequirement("Tea Bag")
            .generator();

    Generator virtualTea = builder.createGenerator("Virtual Tea", cups(5e22), 1.15f, cups(4e18))
            .addUpgradeRequirement("AI Automation")

            .addEfficiencyUpgrade("Tea Simulator", cups(5e23), 4f)
            .addGeneratorRequirement("Virtual Tea", 1)

            .addEfficiencyUpgrade("Online Tea Ceremony", cups(1e25), 6f)
            .addGeneratorRequirement("Virtual Tea", 1)

            .addEfficiencyUpgrade("Shared Serenity", cups(1e26), 2)
            .addUpgradeRequirement("Tea Simulator")
            .addUpgradeRequirement("Online Tea Ceremony")
            .generator();

    builder.resolveRequirements();
    STATE.updateGeneratorStates(GARDEN);
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
        Map<CurrencyMapping, ImprovementDescription> improvementMap =
                ImprovementCalculator.calculateImprovement(GARDEN, state);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println();
        for (Map.Entry<CurrencyMapping, ImprovementDescription> entry : improvementMap.entrySet()) {
            if (improvementMap.size() > 1) {
                actions.add(String.format(">>> %s <<<", entry.getKey()));
            }
            ImprovementDescription improvementDescription = entry.getValue();
            if (improvementDescription.improvement() instanceof GeneratorImprovement(Generator generator,
                    GeneratorState generatorState)) {
                int count = generatorState.count();
                actions.add(String.format("(%d) Generator %s: %d -> %d : %s",
                        i + 1, generator.getName(), count, count + 1, improvementDescription.description()));
                state.setGeneratorCount(generator, count + 1);
                if (count == 0) {
                    printUnlocked(GARDEN, state, actions);
                }
            } else if (improvementDescription.improvement() instanceof UpgradeImprovement upgradeImprovement) {
                Upgrade upgrade = upgradeImprovement.upgrade();
                UpgradeEffect effect = upgrade.getEffects().getFirst();
                actions.add(String.format("(%d) Upgrade %s (%s) : %s",
                        i + 1, upgrade.getName(), effect.generator().getName(), improvementDescription.description()));
                state.setUpgradeBought(upgrade);
                state.updateGeneratorStates(GARDEN);
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
            actions.add(String.format(" *** unlocked generator %s", generator.getName()));
        }
    }
    for (Upgrade upgrade : garden.getUnlockedUpgrades(state)) {
        if (!state.isUpgradeBought(upgrade)) {
            actions.add(String.format(" *** unlocked upgrade %s", upgrade.getName()));
        }
    }
}
