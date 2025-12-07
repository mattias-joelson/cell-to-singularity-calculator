import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
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
    createUpgrade(dairyDelights, milkGenerator, "Camembert", 300_000, 1.5f);
    createUpgrade(dairyDelights, milkGenerator, "Rennet", 8e6f, 3);
    createUpgrade(dairyDelights, milkGenerator, "Muenster", 8e7f, 6);
    createUpgrade(dairyDelights, milkGenerator, "Danish", 3e9f, 6);
    createUpgrade(dairyDelights, milkGenerator, "Curds and Whey", 5e9f, 2);
    createUpgrade(dairyDelights, milkGenerator, "Edam", 5e11f, 11);
    createUpgrade(dairyDelights, milkGenerator, "Salt", 6e13f, 6).setBought(false);

    Generator freshCheese = new Generator("Fresh Cheese", milk(1_000), 1.15f, cheese(1));
    dairyDelights.addGenerator(freshCheese);
    createUpgrade(dairyDelights, freshCheese, "Cottage Cheese", 150, 3);
    createUpgrade(dairyDelights, freshCheese, "Cream Cheese", 500_000, 5);
    createUpgrade(dairyDelights, freshCheese, "Kefir", 2e7f, 6);
    createUpgrade(dairyDelights, freshCheese, "Feta", 2e10f, 301);

    Generator softRipenedCheese = new Generator("Soft-Ripened Cheese", milk(50_000), 1.15f, cheese(100));
    dairyDelights.addGenerator(softRipenedCheese);
    createUpgrade(dairyDelights, softRipenedCheese, "Brie", 150_000, 1.5f);
    createUpgrade(dairyDelights, softRipenedCheese, "Chévre", 6e10f, 601);

    Generator semiSoftCheese = new Generator("Semi-Soft Cheese", milk(1.2e6f), 1.25f, cheese(1000));
    dairyDelights.addGenerator(semiSoftCheese);
    createUpgrade(dairyDelights, semiSoftCheese, "Morbier", 7e6f, 1.5f);
    createUpgrade(dairyDelights, semiSoftCheese, "Havarti", 1.5e11f, 201);

    Generator blueCheese = new Generator("Blue Cheese", milk(1.5e7f), 1.15f, cheese(10_000));
    dairyDelights.addGenerator(blueCheese);
    createUpgrade(dairyDelights, blueCheese, "Roquefort", 2e8f, 1.5f);
    createUpgrade(dairyDelights, blueCheese, "Stilton", 7e8f, 2);
    createUpgrade(dairyDelights, blueCheese, "Gorgonzola", 1.25e9f, 1.5f);

    Generator semiFirmCheese = new Generator("Semi-Firm Cheese", milk(3e8f), 1.15f, cheese(1e6f));
    dairyDelights.addGenerator(semiFirmCheese);
    createUpgrade(dairyDelights, semiFirmCheese, "Halloumi", 1.5e10f, 1.5f);
    createUpgrade(dairyDelights, semiFirmCheese, "Emmental", 3e11f, 4);
    createUpgrade(dairyDelights, semiFirmCheese, "Cheddar", 2e12f, 3);
    createUpgrade(dairyDelights, semiFirmCheese, "Gloucester", 5e12f, 1.5f);
    createUpgrade(dairyDelights, semiFirmCheese, "Provolone", 8e12f, 2f).setBought(false);

    Generator notQuiteCheese = new Generator("Not-Quite-Cheese", cheese(3e11f), 1.15f, cheese(1e7f));
    dairyDelights.addGenerator(notQuiteCheese);
    createUpgrade(dairyDelights, notQuiteCheese, "Non-Dairy", 7e11f, 3);
    createUpgrade(dairyDelights, notQuiteCheese, "Processed", 1.5e13f, 1).setBought(false);

    notQuiteCheese.setCount(1);
    semiFirmCheese.setCount(22);
    blueCheese.setCount(13);
    semiSoftCheese.setCount(22);
    softRipenedCheese.setCount(50);
    freshCheese.setCount(69);
    milkGenerator.setCount(52);

    dairyDelights.updateEfficiency();

    ImprovementCalculator.calculateImprovement(dairyDelights);
}
