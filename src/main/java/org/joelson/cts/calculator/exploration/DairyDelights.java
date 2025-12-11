import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
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

private static Upgrade createUpgrade(
        Garden garden, GardenState gardenState, Generator generator, String name, float cost, float efficiency,
        boolean bought) {
    Upgrade upgrade = new Upgrade(name, cheese(cost));
    upgrade.addEffect(new UpgradeEffect(generator, efficiency));
    garden.addUpgrade(upgrade);
    if (bought) {
        gardenState.setUpgradeBought(upgrade);
    }
    return upgrade;
}

void main() {
    Garden dairyDelights = new Garden("Dairy Delights");
    dairyDelights.addCurrency(MILK);
    dairyDelights.addCurrency(CHEESE);

    GardenState dairyDelightsState = new GardenState();

    Generator milkGenerator = new Generator("Milk", milk(50), 1.4f, milk(1));
    dairyDelights.addGenerator(milkGenerator);
    Upgrade lactose = new Upgrade("Lactose", milk(250));
    lactose.addEffect(new UpgradeEffect(milkGenerator, 1.5f));
    dairyDelights.addUpgrade(lactose);
    dairyDelightsState.setUpgradeBought(lactose, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Mozzarella", 2_000, 3, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Cultures", 10_000, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Camembert", 300_000, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Rennet", 8e6f, 3, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Muenster", 8e7f, 6, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Danish", 3e9f, 6, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Curds and Whey", 5e9f, 2, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Edam", 5e11f, 11, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Salt", 6e13f, 6, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Ricotta", 8e13f, 11, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "The Big Cheese", 3.5e16f, 2, false);

    Generator freshCheese = new Generator("Fresh Cheese", milk(1_000), 1.15f, cheese(1));
    dairyDelights.addGenerator(freshCheese);
    createUpgrade(dairyDelights, dairyDelightsState, freshCheese, "Cottage Cheese", 150, 3, true);
    createUpgrade(dairyDelights, dairyDelightsState, freshCheese, "Cream Cheese", 500_000, 5, true);
    createUpgrade(dairyDelights, dairyDelightsState, freshCheese, "Kefir", 2e7f, 6, true);
    createUpgrade(dairyDelights, dairyDelightsState, freshCheese, "Feta", 2e10f, 301, true);

    Generator softRipenedCheese = new Generator("Soft-Ripened Cheese", milk(50_000), 1.15f, cheese(100));
    dairyDelights.addGenerator(softRipenedCheese);
    createUpgrade(dairyDelights, dairyDelightsState, softRipenedCheese, "Brie", 150_000, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, softRipenedCheese, "Chévre", 6e10f, 601, true);
    createUpgrade(dairyDelights, dairyDelightsState, softRipenedCheese, "Paneer", 1.5e15f, 11001, false);

    Generator semiSoftCheese = new Generator("Semi-Soft Cheese", milk(1.2e6f), 1.25f, cheese(1000));
    dairyDelights.addGenerator(semiSoftCheese);
    createUpgrade(dairyDelights, dairyDelightsState, semiSoftCheese, "Morbier", 7e6f, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiSoftCheese, "Havarti", 1.5e11f, 201, true);

    Generator blueCheese = new Generator("Blue Cheese", milk(1.5e7f), 1.15f, cheese(10_000));
    dairyDelights.addGenerator(blueCheese);
    createUpgrade(dairyDelights, dairyDelightsState, blueCheese, "Roquefort", 2e8f, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, blueCheese, "Stilton", 7e8f, 2, true);
    createUpgrade(dairyDelights, dairyDelightsState, blueCheese, "Gorgonzola", 1.25e9f, 1.5f, true);

    Generator semiFirmCheese = new Generator("Semi-Firm Cheese", milk(3e8f), 1.15f, cheese(1e6f));
    dairyDelights.addGenerator(semiFirmCheese);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Halloumi", 1.5e10f, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Emmental", 3e11f, 4, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Cheddar", 2e12f, 3, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Gloucester", 5e12f, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Provolone", 8e12f, 2f, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Limburger", 6e14f, 6f, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Gouda", 9e14f, 4f, true);

    Generator notQuiteCheese = new Generator("Not-Quite-Cheese", cheese(3e11f), 1.15f, cheese(1e7f));
    dairyDelights.addGenerator(notQuiteCheese);
    createUpgrade(dairyDelights, dairyDelightsState, notQuiteCheese, "Non-Dairy", 7e11f, 3, true);
    createUpgrade(dairyDelights, dairyDelightsState, notQuiteCheese, "Processed", 1.5e13f, 11, true);

    Generator hardCheese = new Generator("Hard Cheese", milk(6e11f), 1.15f, cheese(1e9f));
    dairyDelights.addGenerator(hardCheese);
    createUpgrade(dairyDelights, dairyDelightsState, hardCheese, "Pecorino", 1.8e14f, 2, true);
    createUpgrade(dairyDelights, dairyDelightsState, hardCheese, "Manchego", 4e14f, 2, true);
    createUpgrade(dairyDelights, dairyDelightsState, hardCheese, "Cotija", 2e15f, 3, false);

    dairyDelights.updateEfficiency(dairyDelightsState);

    dairyDelightsState.setGeneratorCount(hardCheese, 6);
    dairyDelightsState.setGeneratorCount(notQuiteCheese, 35);
    dairyDelightsState.setGeneratorCount(semiFirmCheese, 53);
    dairyDelightsState.setGeneratorCount(blueCheese, 13);
    dairyDelightsState.setGeneratorCount(semiSoftCheese, 25);
    dairyDelightsState.setGeneratorCount(softRipenedCheese, 53);
    dairyDelightsState.setGeneratorCount(freshCheese, 74);
    dairyDelightsState.setGeneratorCount(milkGenerator, 67);

    ImprovementCalculator.calculateImprovement(dairyDelights, dairyDelightsState);
    System.out.println("==========================================");
    ImprovementCalculator.calculateImprovementNew(dairyDelights, dairyDelightsState);
}
