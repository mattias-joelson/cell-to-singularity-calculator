import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorImprovement;
import org.joelson.cts.calculator.model.Improvement;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.model.UpgradeImprovement;

private record UpgradeBuilder(Generator generator) {

    private UpgradeBuilder addUpgrade(String name, float cost, float efficiency, boolean unlocked, boolean bought) {
        addGeneratorUpgrade(generator, name, cost, efficiency, unlocked, bought);
        return this;
    }
}

private static final Garden GARDEN = new Garden("Fungus Among Us");
private static final GardenState STATE = new GardenState();
private static final String CURRENCY = "Toadstools";

private static Amount fungus(float amount) {
    return new Amount(CURRENCY, amount);
}

private static UpgradeBuilder createGenerator(String name, float baseCost, float incrementCost, float baseProduction) {
    Generator generator = new Generator(name, fungus(baseCost), incrementCost, fungus(baseProduction));
    GARDEN.addGenerator(generator);
    return new UpgradeBuilder(generator);
}

private static void addGeneratorUpgrade(
        Generator generator, String name, float cost, float efficiency, boolean unlocked, boolean bought) {
    if (unlocked) {
        Upgrade upgrade = new Upgrade(name, fungus(cost));
        upgrade.addEffect(new UpgradeEffect(generator, efficiency));
        GARDEN.addUpgrade(upgrade);
        STATE.setUpgradeBought(upgrade, bought);
    }
}

private void setGeneratorCount(Generator generator, int count) {
    STATE.setGeneratorCount(generator, count);
}

void main() {

    GARDEN.addCurrency(CURRENCY);

    Generator fungi = createGenerator("Fungi", 20, 1.15f, 1)
            .addUpgrade("Mycology", 600, 1.5f, true, true) // Fungi
            .addUpgrade("Mushrooms", 5_000, 11, true, true) // Fungi
            .addUpgrade("Yeast", 2e15f, 2e9f, true, true) // Tasty Fungi / Fungi
            .addUpgrade("Mold", 2e18f, 251, true, true) // Blue Cheese / Fungi
            .generator();

    Generator fungualLiving = createGenerator("Fungal Living", 700, 1.15f, 5) // Mycology
            .addUpgrade("Diet", 1_250, 2, true, true)  // Fungal Living
            .addUpgrade("Reproduction", 2_000, 2, true, true) // Fungal Living
            .addUpgrade("Hydrolytic Enzymes", 50_000, 2, true, true) // Diet
            .addUpgrade("Spores", 150_000, 3, true, true) // Reproduction
            .addUpgrade("Symbiosis", 5e7f, 51, true, true) // Fungal Living
            .addUpgrade("Growth", 1e9f, 11, true, true) // Reproduction
            .generator();

    Generator fungualCleaners = createGenerator("Fungual Cleaners", 600_000, 1.15f, 400) // Hydrolytic Enzymes
            .addUpgrade("Biomediation", 2e6f, 3, true, true) // cleander
            .addUpgrade("Radiotrophic Fungi", 1.5e7f, 4, true, true) // biomedi
            .addUpgrade("Decompostion", 1.5e10f, 16, true, true) // cleander
            .addUpgrade("Fungal Burial", 1.5e11f, 26, true, true) // decompositrion
            .generator();

    Generator fungiOfTheForest = createGenerator("Fungi of the Forest", 3e8f, 1.15f, 50_000) // Symbiosis
            .addUpgrade("Mycorrhiza", 7e8f, 2, true, true) // fungi of the forrest
            .addUpgrade("Mycelial Network", 9e9f, 2, true, true) // gtrowth/ fungi of the forest
            .addUpgrade("Ghost Orchid", 4e10f, 2, true, true) // decompositrion / fungi of the forest
            .addUpgrade("Defense Alert", 8e10f, 2, true, true) // mycelial network
            .generator();

    Generator domesticatedFungi = createGenerator("Domesticated Fungi", 1.5e11f, 1.15f, 6e6f) // fungal burial
            .addUpgrade("Building Material", 6e11f, 4, true, true) // dom fun
            .addUpgrade("Pesticides", 1.5e12f, 11, true, true) // dom fun
            .addUpgrade("Medicine", 2e13f, 3, true, true) // dom fun
            .addUpgrade("Antibiotics", 4e20f, 3e6f, true, true) // medicine, mold
            .generator();

    Generator tastyFungi = createGenerator("Tasty Fungi", 3.5e13f, 1.15f, 5e9f) // medicine
            .addUpgrade("Edible Mushrooms", 1.2e14f, 3, true, true) // tasty fungi
            .addUpgrade("Truffles", 8e14f, 2.5f, true, true) // edible
            .addUpgrade("Bread", 4e16f, 5, true, true) // Truffles
            .addUpgrade("Fermentation", 9e16f, 2, true, true) // Truffles
            .addUpgrade("Cheese Ripening", 2.5e17f, 5, true, true) // Fermentation
            .addUpgrade("Blue Cheese", 7.5e17f, 2.5f, true, true) // Cheese Ripening
            .addUpgrade("Alcohol", 9e22f, 75_001, true, true) // Fermentation
            .generator();

    Generator unwelcomeFungi = createGenerator("Unwelcome Fungi", 1.2e19f, 1.15f, 2.5e14f) // Mold
            .addUpgrade("Moldy Food", 1.5e19f, 9, true, true) // Unwelcome Fungi
            .addUpgrade("Fungal Infections", 6e19f, 2.5f, true, true) // Unwelcome Fungi
            .addUpgrade("Plant Blight", 5e21f, 6, true, true) // Fungal Infection
            .addUpgrade("Black Mold", 1.2e22f, 4, true, true) // Moldy food
            .addUpgrade("Human Illness", 4e22f, 2, true, true) // Black Mol, Fungal infectios
            .addUpgrade("Poisonous Mushrooms", 3e24f, 41, true, true) // Unwelcome Fungi
            .addUpgrade("Amanita", 1e25f, 5, true, true) // Poisonous Mushrooms
            .addUpgrade("Parasitic Fungi", 1e26f, 16, true, true) // Unwelcome Fungi
            .generator();

    Generator mindAlteringFungi = createGenerator("Mind-Altering Fungi", 1e23f, 1.15f, 5e18f) // alcohol
            .addUpgrade("Scent & Taste", 4e23f, 7, true, true) // Mind-Altering
            .addUpgrade("Psychedelic Mushrooms", 4e25f, 16, true, true) // Mind-Altering, poisonous
            .addUpgrade("Scent & Taste", 1e27f, 2, false, false) // Mind-Altering
            .generator();

    GARDEN.updateEfficiency(STATE);
    STATE.setBoost(4);

    setGeneratorCount(mindAlteringFungi, 35);
    setGeneratorCount(unwelcomeFungi, 103);
    setGeneratorCount(tastyFungi, 138);
    setGeneratorCount(domesticatedFungi, 141);
    setGeneratorCount(fungiOfTheForest, 32);
    setGeneratorCount(fungualCleaners, 82);
    setGeneratorCount(fungualLiving, 106);
    setGeneratorCount(fungi, 263);

    GardenState state = STATE.copy();
    List<String> actions = new ArrayList<>();
    for (int i = 0; i < 10; i += 1) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Improvement improvement = ImprovementCalculator.calculateImprovement(GARDEN, state);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println();
        if (improvement instanceof GeneratorImprovement generatorImprovement) {
            Generator generator = generatorImprovement.generator();
            int count = generatorImprovement.state().count();
            actions.add(String.format("Generator %s: %d -> %d", generator.name(), count, count + 1));
            state.setGeneratorCount(generator, count + 1);
        } else if (improvement instanceof UpgradeImprovement upgradeImprovement) {
            Upgrade upgrade = upgradeImprovement.upgrade();
            UpgradeEffect effect = upgrade.getEffects().getFirst();
            actions.add(String.format("Upgrade %s (%s)", upgrade.getName(), effect.getGenerator().name()));
            state.setUpgradeBought(upgrade);
        } else {
            throw new NullPointerException();
        }
        GARDEN.updateEfficiency(state);
    }

    actions.stream().forEach(System.out::println);
}

