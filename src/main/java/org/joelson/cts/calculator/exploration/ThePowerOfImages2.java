import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorState;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;

static final String CURRENCY = "Creativity";

static Amount amount(float amount) {
    return new Amount(CURRENCY, amount);
}

void main() {

    Garden garden = new Garden("The Power of Images");
    GardenState state = new GardenState();
    garden.addCurrency(CURRENCY);

    Generator line = new Generator("Line", amount(40), 1.15f, amount(1));
    garden.addGenerator(line);
    createUpgrade(garden, state, line, "Imagination", 20, 2);
    createUpgrade(garden, state, line, "Drawing", 100, 3);
    createUpgrade(garden, state, line, "Body Paint", 400, 2.5f);

    Generator symbol = new Generator("Symbol", amount(7500), 1.15f, amount(50));
    garden.addGenerator(symbol);
    createUpgrade(garden, state, symbol, "Tattoo", 30_000, 2.5f);
    createUpgrade(garden, state, symbol, "Glyphs", 150_000, 2);
    createUpgrade(garden, state, symbol, "Brush Strokes", 800_000, 2);

    Generator composition = new Generator("Composition", amount(2_500_000), 1.15f, amount(2000));
    garden.addGenerator(composition);
    createUpgrade(garden, state, composition, "Ancient Art", 3.2e6f, 2);
    createUpgrade(garden, state, composition, "Folk Art", 1.2e7f, 2);
    createUpgrade(garden, state, composition, "Greece and Rome", 5e7f, 2);
    createUpgrade(garden, state, composition, "Church as King", 2e8f, 2);
    createUpgrade(garden, state, composition, "The First Projection", 8e8f, 2);

    Generator renaissance = new Generator("Renaissance", amount(625_000_000), 1.15f, amount(500_000));
    garden.addGenerator(renaissance);
    createUpgrade(garden, state, renaissance, "Artisans", 2e9f, 2);
    createUpgrade(garden, state, renaissance, "Patrons", 8e9f, 2);
    createUpgrade(garden, state, renaissance, "Impressionism", 5e11f, 2);
    createUpgrade(garden, state, renaissance, "Romanticism", 3.25e10f, 2);
    createUpgrade(garden, state, renaissance, "Realism", 1.25e11f, 2);
    createUpgrade(garden, state, renaissance, "Post Impressionism", 2e12f, 5);

    Generator photography = new Generator("Photography", amount(150_000_000_000f), 1.15f, amount(50_000_000));
    garden.addGenerator(photography);
    createUpgrade(garden, state, photography, "Memory as an Object", 1e12f, 11);
    createUpgrade(garden, state, photography, "Tricking the Eye", 1.5e16f, 1001);

    Generator modernism = new Generator("Modernism", amount(40_000_000_000_000f), 1.15f, amount(5_000_000_000f));
    garden.addGenerator(modernism);
    createUpgrade(garden, state, modernism, "Expressionism", 5e13f, 2);
    createUpgrade(garden, state, modernism, "Artist Entrepreneur", 2e14f, 2.25f);
    createUpgrade(garden, state, modernism, "Cubism", 8e14f, 2);
    createUpgrade(garden, state, modernism, "Museum", 3e15f, 2);
    createUpgrade(garden, state, modernism, "Abstract Expressionism", 6e17f, 21);

    Generator artCelebrity = new Generator("Art Celebrity", amount(1e16f), 1.15f, amount(3e12f));
    garden.addGenerator(artCelebrity);
    createUpgrade(garden, state, artCelebrity, "Critic", 5e16f, 3.5f);
    createUpgrade(garden, state, artCelebrity, "Dealer", 8e19f, 151);

    Generator postModernism = new Generator("Post Modernism", amount(5e17f), 1.15f, amount(9e13f));
    garden.addGenerator(postModernism);
    createUpgrade(garden, state, postModernism, "Dadaism", 2e18f, 3.5f);
    createUpgrade(garden, state, postModernism, "Surrealism", 1.5e19f, 3);
    createUpgrade(garden, state, postModernism, "Pop Art", 5e20f, 21);
    createUpgrade(garden, state, postModernism, "Feminist Art", 1.5e23f, 201, false);

    Generator movingImages = new Generator("Moving Images", amount(1.5e20f), 1.15f, amount(8e15f));
    garden.addGenerator(movingImages);
    createUpgrade(garden, state, movingImages, "Hand-Drawn Animation", 3e21f, 4);
    createUpgrade(garden, state, movingImages, "Silent to Talkie", 8e21f, 6);
    createUpgrade(garden, state, movingImages, "Grayscale to Color", 4e22f, 4, false);
    createUpgrade(garden, state, movingImages, "Computer Animation", 1e26f, 2.5f, false);

    garden.updateEfficiency(state);
    state.setGeneratorCount(movingImages, 18);
    state.setGeneratorCount(postModernism, 51);
    state.setGeneratorCount(artCelebrity, 61);
    state.setGeneratorCount(modernism, 53);
    state.setGeneratorCount(photography, 84);
    state.setGeneratorCount(renaissance, 60);
    state.setGeneratorCount(composition, 48);
    state.setGeneratorCount(symbol, 120);
    state.setGeneratorCount(line, 68);

    float totalProduction = 0;
    for (Generator generator : garden.getGenerators().reversed()) {
        GeneratorState generatorState = state.getGeneratorState(generator);
        int count = generatorState.count();
        float baseProduction = generator.getBaseProduction().amount();
        float efficiency = generatorState.efficiency();
        float production = baseProduction * efficiency * count;
        System.out.printf("Generator %s:\tcount %d (next %.2e), base %.2e, each %.2e, total %.3e%n",
                generator.getName(), count, generator.getCost(count).amount(), baseProduction,
                baseProduction * efficiency,
                production);
        totalProduction += production;
    }
    System.out.printf("Total production: %.2e%n", totalProduction);
    System.out.println();

    float maxRatio = 0;
    String which = "";
    for (Generator generator : garden.getGenerators().reversed()) {
        GeneratorState generatorState = state.getGeneratorState(generator);
        float cost = generator.getCost(generatorState.count()).amount();
        float increase = generator.getBaseProduction().amount() * generatorState.efficiency();
        float ratio = increase / cost;
        if (ratio > maxRatio) {
            maxRatio = ratio;
            which = generator.getName();
        }
        float time = cost / totalProduction;
        Duration duration = Duration.of(Math.round(time), ChronoUnit.SECONDS);
        System.out.printf("Generator %s: cost %.2e, increase %.2e, ratio %.7f, time %s%n",
                generator.getName(), cost, increase, ratio, duration);
    }
    for (Upgrade upgrade : garden.getUpgrades()) {
        if (!state.isUpgradeBought(upgrade)) {
            float cost = upgrade.getCost().amount();
            float increase = 0;
            for (UpgradeEffect effect : upgrade.getEffects()) {
                Generator generator = effect.getGenerator();
                GeneratorState generatorState = state.getGeneratorState(generator);
                increase += (effect.getEfficiency() - 1) * generator.getBaseProduction().amount()
                        * generatorState.count() * generatorState.efficiency();
            }
            float ratio = increase / cost;
            if (ratio > maxRatio) {
                maxRatio = ratio;
                which = upgrade.getName();
            }
            float time = cost / totalProduction;
            Duration duration = Duration.of(Math.round(time), ChronoUnit.SECONDS);
            System.out.printf("Upgrade %s: cost %.2e, increase %.2e, ratio %.7f, time %s%n",
                    upgrade.getName(), cost, increase, ratio, duration);
        }
    }

    System.out.printf("Best ratio: %s, %.7f%n", which, maxRatio);
}

private static Upgrade createUpgrade(
        Garden garden, GardenState state, Generator generator, String name, float cost, float efficiency) {
    Upgrade upgrade = new Upgrade(name, amount(cost));
    upgrade.addEffect(new UpgradeEffect(generator, efficiency));
    garden.addUpgrade(upgrade);
    state.setUpgradeBought(upgrade);
    return upgrade;
}

private static Upgrade createUpgrade(
        Garden garden, GardenState state, Generator generator, String name, float cost, float efficiency,
        boolean bought) {
    Upgrade upgrade = createUpgrade(garden, state, generator, name, cost, efficiency);
    state.setUpgradeBought(upgrade, bought);
    return upgrade;
}
