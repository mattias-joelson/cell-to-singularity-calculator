import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.CurrencyMapping;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.Improvement;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.model.UpgradeImprovement;

import static org.joelson.cts.calculator.util.DurationToolkit.durationString;

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
    createUpgrade(dairyDelights, milkGenerator, "Camembert", 300_000, 1.5f);
    createUpgrade(dairyDelights, milkGenerator, "Rennet", 8e6f, 3);
    createUpgrade(dairyDelights, milkGenerator, "Muenster", 8e7f, 6);
    createUpgrade(dairyDelights, milkGenerator, "Danish", 3e9f, 6);
    createUpgrade(dairyDelights, milkGenerator, "Curds and Whey", 5e9f, 2);
    createUpgrade(dairyDelights, milkGenerator, "Salt", 6e13f, 6).setBought(false);

    Generator freshCheese = new Generator("Fresh Cheese", milk(1_000), 1.15f, cheese(1));
    dairyDelights.addGenerator(freshCheese);
    createUpgrade(dairyDelights, freshCheese, "Cottage Cheese", 150, 3);
    createUpgrade(dairyDelights, freshCheese, "Cream Cheese", 500_000, 5);
    createUpgrade(dairyDelights, freshCheese, "Kefir", 2e7f, 6);
    createUpgrade(dairyDelights, freshCheese, "Feta", 2e10f, 301).setBought(false);

    Generator softRipenedCheese = new Generator("Soft-Ripened Cheese", milk(50_000), 1.15f, cheese(100));
    dairyDelights.addGenerator(softRipenedCheese);
    createUpgrade(dairyDelights, softRipenedCheese, "Brie", 150_000, 1.5f);
    createUpgrade(dairyDelights, softRipenedCheese, "Chévre", 6e10f, 1.6f).setBought(false);

    Generator semiSoftCheese = new Generator("Semi-Soft Cheese", milk(1.2e6f), 1.25f, cheese(1000));
    dairyDelights.addGenerator(semiSoftCheese);
    createUpgrade(dairyDelights, semiSoftCheese, "Morbier", 7e6f, 1.5f);

    Generator blueCheese = new Generator("Blue Cheese", milk(1.5e7f), 1.15f, cheese(10_000));
    dairyDelights.addGenerator(blueCheese);
    createUpgrade(dairyDelights, blueCheese, "Roquefort", 2e8f, 1.5f);
    createUpgrade(dairyDelights, blueCheese, "Stilton", 7e8f, 2);
    createUpgrade(dairyDelights, blueCheese, "Gorgonzola", 1.25e9f, 1.5f);

    Generator semiFirmCheese = new Generator("Semi-Firm Cheese", milk(3e8f), 1.15f, cheese(1e6f));
    dairyDelights.addGenerator(semiFirmCheese);
    createUpgrade(dairyDelights, semiFirmCheese, "Halloumi", 1.5e10f, 1.5f).setBought(false);

    semiFirmCheese.setCount(1);
    blueCheese.setCount(13);
    semiSoftCheese.setCount(5);
    softRipenedCheese.setCount(14);
    freshCheese.setCount(38);
    milkGenerator.setCount(42);

    ImprovementCalculator.calculateImprovement(dairyDelights);
}
