import org.joelson.cts.calculator.model.Currency;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;

void main() {

    Currency currency = new Currency("Creativity");
    Garden garden = new Garden("The Power of Images");
    garden.addCurrency(currency);

    Generator line = new Generator("Line", 40, 1.15f, 1);
    garden.addGenerator(line);
    createUpgrade(garden, line, "Imagination", 20, 2);
    createUpgrade(garden, line, "Drawing", 100, 3);
    createUpgrade(garden, line, "Body Paint", 400, 2.5f);

    Generator symbol = new Generator("Symbol", 7500, 1.15f, 50);
    garden.addGenerator(symbol);
    createUpgrade(garden, symbol, "Tattoo", 30_000, 2.5f);
    createUpgrade(garden, symbol, "Glyphs", 150_000, 2);
    createUpgrade(garden, symbol, "Brush Strokes", 800_000, 2);

    Generator composition = new Generator("Composition", 2_500_000, 1.15f, 2000);
    garden.addGenerator(composition);
    createUpgrade(garden, composition, "Ancient Art", 3.2e6f, 2);
    createUpgrade(garden, composition, "Folk Art", 1.2e7f, 2);
    createUpgrade(garden, composition, "Greece and Rome", 5e7f, 2);
    createUpgrade(garden, composition, "Church as King", 2e8f, 2);
    createUpgrade(garden, composition, "The First Projection", 8e8f, 2);

    Generator renaissence = new Generator("Renaissence", 625_000_000, 1.15f, 500_000);
    garden.addGenerator(renaissence);
    createUpgrade(garden, renaissence, "Artisans", 2e9f, 2);
    createUpgrade(garden, renaissence, "Patrons", 8e9f, 2);
    createUpgrade(garden, renaissence, "Impressionism", 5e11f, 2);
    createUpgrade(garden, renaissence, "Romanticism", 3.25e10f, 2);
    createUpgrade(garden, renaissence, "Realism", 1.25e11f, 2);
    createUpgrade(garden, renaissence, "Post Impressionism", 2e12f, 5);

    Generator photography = new Generator("Photography", 150_000_000_000f, 1.15f, 50_000_000);
    garden.addGenerator(photography);
    createUpgrade(garden, photography, "Memory as an Object", 1e12f, 11);
    createUpgrade(garden, photography, "Tricking the Eye", 1.5e16f, 1001);

    Generator modernism = new Generator("Modernism", 40_000_000_000_000f, 1.15f, 5_000_000_000f);
    garden.addGenerator(modernism);
    createUpgrade(garden, modernism, "Expressionism", 5e13f, 2);
    createUpgrade(garden, modernism, "Artist Entrepreneur", 2e14f, 2.25f);
    createUpgrade(garden, modernism, "Cubism", 8e14f, 2);
    createUpgrade(garden, modernism, "Museum", 3e15f, 2);
    createUpgrade(garden, modernism, "Abstract Expressionism", 6e17f, 21);

    Generator artCelebrity = new Generator("Art Celebrity", 1e16f, 1.15f, 3e12f);
    garden.addGenerator(artCelebrity);
    createUpgrade(garden, artCelebrity, "Critic", 5e16f, 3.5f);
    createUpgrade(garden, artCelebrity, "Dealer", 8e19f, 151);

    Generator postModernism = new Generator("Post Modernism", 5e17f, 1.15f, 9e13f);
    garden.addGenerator(postModernism);
    createUpgrade(garden, postModernism, "Dadaism", 2e18f, 3.5f);
    createUpgrade(garden, postModernism, "Surrealism", 1.5e19f, 3);
    createUpgrade(garden, postModernism, "Pop Art", 5e20f, 21);
    createUpgrade(garden, postModernism, "Feminist Art", 1.5e23f, 201).setBought(false);

    Generator movingImages = new Generator("Moving Images", 1.5e20f, 1.15f, 8e15f);
    garden.addGenerator(movingImages);
    createUpgrade(garden, movingImages, "Hand-Drawn Animation", 3e21f, 4);
    createUpgrade(garden, movingImages, "Silent to Talkie", 8e21f, 6);
    createUpgrade(garden, movingImages, "Grayscale to Color", 4e22f, 4).setBought(false);
    createUpgrade(garden, movingImages, "Computer Animation", 1e26f, 2.5f).setBought(false);

    movingImages.setCount(18);
    postModernism.setCount(51);
    artCelebrity.setCount(61);
    modernism.setCount(53);
    photography.setCount(84);
    renaissence.setCount(60);
    composition.setCount(48);
    symbol.setCount(120);
    line.setCount(68);

    float totalProduction = 0;
    for (Generator generator : garden.getGenerators()) {
        generator.updateEfficiency(garden);
    }
    for (Generator generator : garden.getGenerators().reversed()) {
        int count = generator.getCount();
        float baseProduction = generator.getBaseProduction();
        float efficiency = generator.getEfficiency();
        float production = baseProduction * efficiency * count;
        System.out.printf("Generator %s:\tcount %d (next %.2e), base %.2e, each %.2e, total %.3e%n",
                generator.getName(), count, generator.getCost(count), baseProduction, baseProduction * efficiency,
                production);
        totalProduction += production;
    }
    System.out.printf("Total production: %.2e%n", totalProduction);
    System.out.println();

    float maxRatio = 0;
    String which = "";
    for (Generator generator : garden.getGenerators().reversed()) {
        float cost = generator.getCost(generator.getCount());
        float increase = generator.getBaseProduction() * generator.getEfficiency();
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
        if (!upgrade.isBought()) {
            float cost = upgrade.getCost();
            float increase = 0;
            for (UpgradeEffect effect : upgrade.getEffects()) {
                increase += (effect.getEfficiency() - 1) * effect.getGenerator().getTotalProduction();
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

private static Upgrade createUpgrade(Garden garden, Generator generator, String name, float cost, float efficiency) {
    Upgrade upgrade = new Upgrade(name, cost, true);
    upgrade.addEffect(new UpgradeEffect(generator, efficiency));
    garden.addUpgrade(upgrade);
    return upgrade;
}
