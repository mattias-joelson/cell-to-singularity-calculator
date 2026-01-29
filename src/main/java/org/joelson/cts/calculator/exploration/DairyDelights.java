package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class DairyDelights {

    private static final String MILK_CURRENCY = "Milk";
    private static final String CHEESE_CURRENCY = "Cheese";

    private static final String[] CURRENCIES = { MILK_CURRENCY, CHEESE_CURRENCY };

    private static Amount milk(double amount) {
        return new Amount(MILK_CURRENCY, amount);
    }

    private static Amount cheese(double amount) {
        return new Amount(CHEESE_CURRENCY, amount);
    }

    private void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Dairy Delights");
        for (String currency : CURRENCIES) {
            garden.addCurrency(currency);
        }

        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Milk", milk(50), 1.4f, milk(1))
                //.addUpgradeRequirement("Dawn of Dairy")

                .addEfficiencyUpgrade("Lactose", milk(250), 1.5f)
                .addGeneratorRequirement("Milk")

                .addEfficiencyUpgrade("Mozzarella", cheese(2_000), 3)
                .addGeneratorRequirement("Fresh Cheese")

                .addEfficiencyUpgrade("Cultures", cheese(10_000), 1.5f)
                .addUpgradeRequirement("Lactose")
                .addUpgradeRequirement("Mozzarella")

                .addEfficiencyUpgrade("Camenbert", cheese(300_000), 1.5f)
                .addUpgradeRequirement("Brie")

                .addEfficiencyUpgrade("Rennet", cheese(8e6), 3)
                .addUpgradeRequirement("Cultures")
                .addUpgradeRequirement("Cream Cheese")

                .addEfficiencyUpgrade("Muenster", cheese(8e7), 6)
                .addGeneratorRequirement("Semi-Soft Cheese")
                .addUpgradeRequirement("Kefir")

                .addEfficiencyUpgrade("Danish", cheese(3e9), 6)
                .addGeneratorRequirement("Blue Cheese")
                .addUpgradeRequirement("Stilton")

                .addEfficiencyUpgrade("Curds and Whey", cheese(5e9), 2)
                .addUpgradeRequirement("Rennet")
                .addGeneratorRequirement("Semi-Soft Cheese")

                .addEfficiencyUpgrade("Edam", cheese(5e11), 11)
                .addGeneratorRequirement("Semi-Firm Cheese")
                .addUpgradeRequirement("Havarti")

                .addEfficiencyUpgrade("Salt", cheese(6e13), 6)
                .addUpgradeRequirement("Curds and Whey")
                .addGeneratorRequirement("Semi-Firm Cheese")

                .addEfficiencyUpgrade("Ricotta", cheese(8e13), 11)
                .addGeneratorRequirement("Fresh Cheese")
                .addUpgradeRequirement("Salt")

                .addEfficiencyUpgrade("Parmigiano-Reggiano", cheese(1.75e16), 151)
                .addGeneratorRequirement("Hard Cheese")
                .addUpgradeRequirement("Casu Martzu")

                .addEfficiencyUpgrade("The Big Cheese", cheese(3.5e16), 2)
                .addUpgradeRequirement("Salt")
                .addGeneratorRequirement("Hard Cheese");

        builder.createGenerator("Fresh Cheese", milk(1_000), cheese(1))
                .addGeneratorRequirement("Milk")
                .addUpgradeRequirement("Lactose")

                .addEfficiencyUpgrade("Cottage Cheese", cheese(150), 3)
                .addGeneratorRequirement("Fresh Cheese")

                .addEfficiencyUpgrade("Cream Cheese", cheese(500_000), 5)
                .addGeneratorRequirement("Fresh Cheese")
                .addGeneratorRequirement("Soft-Ripened Cheese")

                .addEfficiencyUpgrade("Kefir", cheese(2e7), 6)
                .addGeneratorRequirement("Fresh Cheese")
                .addGeneratorRequirement("Semi-Soft Cheese")

                .addEfficiencyUpgrade("Feta", cheese(2e10), 301)
                .addGeneratorRequirement("Fresh Cheese")
                .addGeneratorRequirement("Semi-Firm Cheese");

        builder.createGenerator("Soft-Ripened Cheese", milk(50_000), cheese(100))
                .addGeneratorRequirement("Milk")
                .addUpgradeRequirement("Cultures")

                .addEfficiencyUpgrade("Brie", cheese(150_000), 0.5f)
                .addGeneratorRequirement("Soft-Ripened Cheese")

                .addEfficiencyUpgrade("Chèvre", cheese(6e10), 601)
                .addGeneratorRequirement("Soft-Ripened Cheese")
                .addUpgradeRequirement("Feta")

                .addEfficiencyUpgrade("Paneer", cheese(1.5e15), 11_001)
                .addGeneratorRequirement("Soft-Ripened Cheese")
                .addUpgradeRequirement("Limburger");

        builder.createGenerator("Semi-Soft Cheese", milk(1.2e6), 1.25f, cheese(1_000))
                .addGeneratorRequirement("Milk")
                .addUpgradeRequirement("Rennet")

                .addEfficiencyUpgrade("Morbier", cheese(7e6), 1.5f)
                .addGeneratorRequirement("Semi-Soft Cheese")

                .addEfficiencyUpgrade("Havarti", cheese(1.5e11), 201)
                .addGeneratorRequirement("Semi-Soft Cheese")
                .addUpgradeRequirement("Feta");

        builder.createGenerator("Blue Cheese", milk(1.5e7), cheese(10_000))
                .addGeneratorRequirement("Milk")
                .addUpgradeRequirement("Muenster")

                .addEfficiencyUpgrade("Roquefort", cheese(2e8), 1.5f)
                .addGeneratorRequirement("Blue Cheese")

                .addEfficiencyUpgrade("Stilton", cheese(7e8), 2)
                .addGeneratorRequirement("Blue Cheese")

                .addEfficiencyUpgrade("Gorgonzola", cheese(1.25e9), 1.5f)
                .addGeneratorRequirement("Blue Cheese")
                .addUpgradeRequirement("Stilton");

        builder.createGenerator("Semi-Firm Cheese", milk(3e8), cheese(1e6))
                .addGeneratorRequirement("Milk")
                .addUpgradeRequirement("Curds and Whey")

                .addEfficiencyUpgrade("Halloumi", cheese(1.5e10), 1.5f)
                .addGeneratorRequirement("Semi-Firm Cheese")

                .addEfficiencyUpgrade("Emmental", cheese(3e11), 4)
                .addGeneratorRequirement("Semi-Firm Cheese")
                .addUpgradeRequirement("Havarti")

                .addEfficiencyUpgrade("Cheddar", cheese(2e12), 3)
                .addGeneratorRequirement("Semi-Firm Cheese")
                .addGeneratorRequirement("Not-Quite-Cheese")

                .addEfficiencyUpgrade("Gloucester", cheese(5e12), 1.5f)
                .addGeneratorRequirement("Semi-Firm Cheese")
                .addGeneratorRequirement("Not-Quite-Cheese")

                .addEfficiencyUpgrade("Provolone", cheese(8e12), 2)
                .addGeneratorRequirement("Semi-Soft Cheese") // ?
                .addUpgradeRequirement("Gloucester")

                .addEfficiencyUpgrade("Limburger", cheese(6e14), 6)
                .addGeneratorRequirement("Semi-Firm Cheese")
                .addGeneratorRequirement("Hard Cheese")

                .addEfficiencyUpgrade("Gouda", cheese(9e14), 4)
                .addGeneratorRequirement("Semi-Firm Cheese")
                .addUpgradeRequirement("Limburger");

        builder.createGenerator("Not-Quite-Cheese", cheese(3e11), cheese(1e7))
                .addUpgradeRequirement("Edam")

                .addEfficiencyUpgrade("Non-Dairy", cheese(7e11), 3)
                .addGeneratorRequirement("Not-Quite-Cheese")

                .addEfficiencyUpgrade("Processed", cheese(1.5e13), 11)
                .addGeneratorRequirement("Not-Quite-Cheese")
                .addUpgradeRequirement("Gloucester")

                .addEfficiencyUpgrade("American", cheese(3e15), 6)
                .addUpgradeRequirement("Processed")
                .addUpgradeRequirement("Cotija")

                .addEfficiencyUpgrade("Canned Cheese", cheese(5e15), 4)
                .addUpgradeRequirement("American");

        builder.createGenerator("Hard Cheese", milk(6e11), cheese(1e9))
                .addGeneratorRequirement("Milk")
                .addUpgradeRequirement("Salt")

                .addEfficiencyUpgrade("Pecorino", cheese(1.8e14), 2)
                .addGeneratorRequirement("Hard Cheese")

                .addEfficiencyUpgrade("Manchego", cheese(4e14), 2)
                .addGeneratorRequirement("Hard Cheese")

                .addEfficiencyUpgrade("Cotija", cheese(2e15), 3)
                .addGeneratorRequirement("Hard Cheese")
                .addUpgradeRequirement("Limburger")

                .addEfficiencyUpgrade("Casu Martzu", cheese(1e16), 6)
                .addUpgradeRequirement("Pecorino")
                .addUpgradeRequirement("Cotija");

        builder.resolveRequirements();

        return garden;
    }

    void main() {

        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

//        Garden.generateGeneratorUpgradesNames(garden);

        setGeneratorCount(garden, state, "Hard Cheese", 1);
        setGeneratorCount(garden, state, "Not-Quite-Cheese", 1);
        setGeneratorCount(garden, state, "Semi-Firm Cheese", 1);
        setGeneratorCount(garden, state, "Blue Cheese", 1);
        setGeneratorCount(garden, state, "Semi-Soft Cheese", 1);
        setGeneratorCount(garden, state, "Soft-Ripened Cheese", 1);
        setGeneratorCount(garden, state, "Fresh Cheese", 1);
        setGeneratorCount(garden, state, "Milk", 1);

        String[] boughtUpdates = {
//                "Lactose", // check
//                "Mozzarella", // cheese // check
//                "Cultures", // cheese // check
//                "Camenbert", // cheese // check
//                "Rennet", // cheese // check
//                "Muenster", // cheese // check
//                "Danish", // cheese // check
//                "Curds and Whey", // cheese // check
//                "Edam", // cheese // check
//                "Salt", // cheese // check
//                "Ricotta", // cheese // check
//                "Parmigiano-Reggiano", // cheese // check
//                "The Big Cheese", // cheese // check

//                "Cottage Cheese", // check
//                "Cream Cheese", // check
//                "Kefir", // check
//                "Feta", // check

//                "Brie", // check
//                "Chèvre", // check
//                "Paneer", // check

//                "Morbier", // check
//                "Havarti", // check

//                "Roquefort", // check
//                "Stilton", // check
//                "Gorgonzola", // check

//                "Halloumi", // check
//                "Emmental", // check
//                "Cheddar", // check
//                "Gloucester", // check
//                "Provolone", // check
//                "Limburger", // check
//                "Gouda", // check

//                "Non-Dairy", // check
//                "Processed", // check
//                "American", // check
//                "Canned Cheese", // check

//                "Pecorino", // check
//                "Manchego", // check
//                "Cotija", // check
//                "Casu Martzu", // check
        };

        for (String upgradeName : boughtUpdates) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);

//        state.verifyAllUpgradesBought(garden);

        List<String> actions = new ArrayList<>();
        ImprovementCalculator.multiCurrencyApproach(garden, state, actions);
        actions.forEach(System.out::println);
    }
}
