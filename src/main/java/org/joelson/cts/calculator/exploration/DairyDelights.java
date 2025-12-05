import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;

static String MILK = "Milk";
static String CHEESE = "Cheese";

private static Amount milk(float amount) {
    return new Amount(MILK, amount);
}

private static Amount cheese(float amount) {
    return new Amount(CHEESE, amount);
}

private static Upgrade createUpgrade(Garden garden, Generator generator, String name, float cost, float efficiency) {
    Upgrade upgrade = new Upgrade(name, cheese(cost), true);
    upgrade.addEffect(new UpgradeEffect(generator, efficiency));
    garden.addUpgrade(upgrade);
    return upgrade;
}

void main() {
    Garden dairyDelights = new Garden("Dairy Delights");
    dairyDelights.addCurrency(MILK);
    dairyDelights.addCurrency(CHEESE);

    Generator milkGenerator = new Generator("Milk", milk(50), 1.4f, milk(1));
    dairyDelights.addGenerator(milkGenerator);
    Upgrade lactose = new Upgrade("Lactose", milk(250), true);
    lactose.addEffect(new UpgradeEffect(milkGenerator, 1.5f));
    dairyDelights.addUpgrade(lactose);
    createUpgrade(dairyDelights, milkGenerator, "Mozzarella", 2_000, 3);
    createUpgrade(dairyDelights, milkGenerator, "Cultures", 10_000, 1.5f);
    createUpgrade(dairyDelights, milkGenerator, "Camenbert", 300_000, 1.5f);
    createUpgrade(dairyDelights, milkGenerator, "Rennet", 8e6f, 3);
    createUpgrade(dairyDelights, milkGenerator, "Muenster", 8e7f, 6);
    createUpgrade(dairyDelights, milkGenerator, "Curds and Whey", 5e9f, 2).setBought(false);

    Generator freshCheese = new Generator("Fresh Cheese", milk(1_000), 1.15f, cheese(1));
    dairyDelights.addGenerator(freshCheese);
    createUpgrade(dairyDelights, freshCheese, "Cottage Cheese", 150, 3);
    createUpgrade(dairyDelights, freshCheese, "Cream Cheese", 500_000, 5);
    createUpgrade(dairyDelights, freshCheese, "Kefir", 2e7f, 6);

    Generator softRipenedCheese = new Generator("Soft-Ripened Cheese", milk(50_000), 1.15f, cheese(100));
    dairyDelights.addGenerator(softRipenedCheese);
    createUpgrade(dairyDelights, softRipenedCheese, "Brie", 150_000, 1.5f);

    Generator semiSoftCheese = new Generator("Semi-Soft Cheese", milk(1.2e6f), 1.25f, cheese(1000));
    dairyDelights.addGenerator(semiSoftCheese);
    createUpgrade(dairyDelights, semiSoftCheese, "Morbier", 7e6f, 1.5f);

    Generator blueCheese = new Generator("Blue Cheese", milk(1.5e7f), 1.15f, cheese(10_000));
    dairyDelights.addGenerator(blueCheese);
    createUpgrade(dairyDelights, blueCheese, "Roquefort", 2e8f, 1.5f).setBought(false);
    createUpgrade(dairyDelights, blueCheese, "Stilton", 7e8f, 2).setBought(false);

    blueCheese.setCount(1);
    semiSoftCheese.setCount(4);
    softRipenedCheese.setCount(12);
    freshCheese.setCount(37);
    milkGenerator.setCount(29);

    produceNext(dairyDelights);
}

private void produceNext(Garden garden) {
    Map<String, Float> totalProduction = new HashMap<>();
    for (String currency : garden.getCurrencies()) {
        totalProduction.put(currency, 0f);
    }
    for (Generator generator : garden.getGenerators()) {
        generator.updateEfficiency(garden);
    }
    for (Generator generator : garden.getGenerators().reversed()) {
        int count = generator.getCount();
        String currencyName = generator.getBaseProduction().currency();
        float baseProduction = generator.getBaseProduction().amount();
        float efficiency = generator.getEfficiency();
        float production = baseProduction * efficiency * count;
        System.out.printf("Generator %s: count %d (next %.2e), base %.2e %s, each %.2e %s, total %.3e %s%n",
                generator.getName(), count, generator.getCost(count).amount(), baseProduction, currencyName,
                baseProduction * efficiency, currencyName, production, currencyName);
        totalProduction.put(currencyName, totalProduction.get(currencyName) + production);
    }
    for (String currency : garden.getCurrencies()) {
        System.out.printf("%s production: %.2e%n", currency, totalProduction.get(currency));
    }
    System.out.println();

    float maxRatio = 0;
    String which = "";
    for (Generator generator : garden.getGenerators().reversed()) {
        float cost = generator.getCost(generator.getCount()).amount();
        float increase = generator.getBaseProduction().amount() * generator.getEfficiency();
        float ratio = increase / cost;
        if (ratio > maxRatio) {
            maxRatio = ratio;
            which = generator.getName();
        }
        float time = cost / totalProduction.get(generator.getBaseCost().currency());
        Duration duration = Duration.of(Math.round(time), ChronoUnit.SECONDS);
        System.out.printf("Generator %s: cost %.2e, increase %.2e, ratio %.7f, time %s%n",
                generator.getName(), cost, increase, ratio, duration);
    }
    for (Upgrade upgrade : garden.getUpgrades()) {
        if (!upgrade.isBought()) {
            float cost = upgrade.getCost().amount();
            float increase = 0;
            for (UpgradeEffect effect : upgrade.getEffects()) {
                increase += (effect.getEfficiency() - 1) * effect.getGenerator().getTotalProduction().amount();
            }
            float ratio = increase / cost;
            if (ratio > maxRatio) {
                maxRatio = ratio;
                which = upgrade.getName();
            }
            float time = cost / totalProduction.get(upgrade.getCost().currency());
            Duration duration = Duration.of(Math.round(time), ChronoUnit.SECONDS);
            System.out.printf("Upgrade %s: cost %.2e, increase %.2e, ratio %.7f, time %s%n",
                    upgrade.getName(), cost, increase, ratio, duration);
        }
    }

    System.out.printf("Best ratio: %s, %.7f%n", which, maxRatio);
}
