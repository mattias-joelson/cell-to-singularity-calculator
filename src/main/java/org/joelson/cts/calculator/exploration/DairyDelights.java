import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;

static String MILK = "Milk";
static String CHEESE = "Cheese";

private static Amount milk(double amount) {
    return new Amount(MILK, amount);
}

private static Amount cheese(double amount) {
    return new Amount(CHEESE, amount);
}

private static Upgrade createUpgrade(
        Garden garden, GardenState gardenState, Generator generator, String name, double cost, float efficiency,
        boolean bought) {
    Upgrade upgrade = new Upgrade(name, cheese(cost));
    upgrade.addEffect(UpgradeEffect.withEfficiency(generator, efficiency));
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
    lactose.addEffect(UpgradeEffect.withEfficiency(milkGenerator, 1.5f));
    dairyDelights.addUpgrade(lactose);
    dairyDelightsState.setUpgradeBought(lactose, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Mozzarella", 2_000, 3, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Cultures", 10_000, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Camembert", 300_000, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Rennet", 8e6, 3, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Muenster", 8e7, 6, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Danish", 3e9, 6, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Curds and Whey", 5e9, 2, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Edam", 5e11, 11, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Salt", 6e13, 6, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "Ricotta", 8e13, 11, true);
    createUpgrade(dairyDelights, dairyDelightsState, milkGenerator, "The Big Cheese", 3.5e16, 2, false);

    Generator freshCheese = new Generator("Fresh Cheese", milk(1_000), 1.15f, cheese(1));
    dairyDelights.addGenerator(freshCheese);
    createUpgrade(dairyDelights, dairyDelightsState, freshCheese, "Cottage Cheese", 150, 3, true);
    createUpgrade(dairyDelights, dairyDelightsState, freshCheese, "Cream Cheese", 500_000, 5, true);
    createUpgrade(dairyDelights, dairyDelightsState, freshCheese, "Kefir", 2e7, 6, true);
    createUpgrade(dairyDelights, dairyDelightsState, freshCheese, "Feta", 2e10, 301, true);

    Generator softRipenedCheese = new Generator("Soft-Ripened Cheese", milk(50_000), 1.15f, cheese(100));
    dairyDelights.addGenerator(softRipenedCheese);
    createUpgrade(dairyDelights, dairyDelightsState, softRipenedCheese, "Brie", 150_000, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, softRipenedCheese, "Chévre", 6e10, 601, true);
    createUpgrade(dairyDelights, dairyDelightsState, softRipenedCheese, "Paneer", 1.5e15, 11001, false);

    Generator semiSoftCheese = new Generator("Semi-Soft Cheese", milk(1.2e6), 1.25f, cheese(1000));
    dairyDelights.addGenerator(semiSoftCheese);
    createUpgrade(dairyDelights, dairyDelightsState, semiSoftCheese, "Morbier", 7e6, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiSoftCheese, "Havarti", 1.5e11, 201, true);

    Generator blueCheese = new Generator("Blue Cheese", milk(1.5e7), 1.15f, cheese(10_000));
    dairyDelights.addGenerator(blueCheese);
    createUpgrade(dairyDelights, dairyDelightsState, blueCheese, "Roquefort", 2e8, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, blueCheese, "Stilton", 7e8, 2, true);
    createUpgrade(dairyDelights, dairyDelightsState, blueCheese, "Gorgonzola", 1.25e9, 1.5f, true);

    Generator semiFirmCheese = new Generator("Semi-Firm Cheese", milk(3e8), 1.15f, cheese(1e6));
    dairyDelights.addGenerator(semiFirmCheese);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Halloumi", 1.5e10, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Emmental", 3e11, 4, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Cheddar", 2e12, 3, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Gloucester", 5e12, 1.5f, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Provolone", 8e12, 2f, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Limburger", 6e14, 6f, true);
    createUpgrade(dairyDelights, dairyDelightsState, semiFirmCheese, "Gouda", 9e14, 4f, true);

    Generator notQuiteCheese = new Generator("Not-Quite-Cheese", cheese(3e11), 1.15f, cheese(1e7));
    dairyDelights.addGenerator(notQuiteCheese);
    createUpgrade(dairyDelights, dairyDelightsState, notQuiteCheese, "Non-Dairy", 7e11, 3, true);
    createUpgrade(dairyDelights, dairyDelightsState, notQuiteCheese, "Processed", 1.5e13, 11, true);

    Generator hardCheese = new Generator("Hard Cheese", milk(6e11), 1.15f, cheese(1e9));
    dairyDelights.addGenerator(hardCheese);
    createUpgrade(dairyDelights, dairyDelightsState, hardCheese, "Pecorino", 1.8e14, 2, true);
    createUpgrade(dairyDelights, dairyDelightsState, hardCheese, "Manchego", 4e14, 2, true);
    createUpgrade(dairyDelights, dairyDelightsState, hardCheese, "Cotija", 2e15, 3, false);

    dairyDelightsState.updateGeneratorStates(dairyDelights);

    dairyDelightsState.setGeneratorCount(hardCheese, 6);
    dairyDelightsState.setGeneratorCount(notQuiteCheese, 35);
    dairyDelightsState.setGeneratorCount(semiFirmCheese, 53);
    dairyDelightsState.setGeneratorCount(blueCheese, 13);
    dairyDelightsState.setGeneratorCount(semiSoftCheese, 25);
    dairyDelightsState.setGeneratorCount(softRipenedCheese, 53);
    dairyDelightsState.setGeneratorCount(freshCheese, 74);
    dairyDelightsState.setGeneratorCount(milkGenerator, 67);

    ImprovementCalculator.calculateImprovement(dairyDelights, dairyDelightsState);
}
