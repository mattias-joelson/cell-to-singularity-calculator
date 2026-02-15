package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class AJourneyOfSerenity {

    private static final String LEAVES_CURRENCY = "Tea Leaves";
    private static final String CUPS_CURRENCY = "Tea Cups";

    private static Amount leaves(double amount) {
        return new Amount(LEAVES_CURRENCY, amount);
    }

    private static Amount cups(double amount) {
        return new Amount(CUPS_CURRENCY, amount);
    }

    private void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("A Journey of Serenity");
        garden.addCurrency(LEAVES_CURRENCY);
        garden.addCurrency(CUPS_CURRENCY);

        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Wild Tea Plant", leaves(15), 1.13f, leaves(1))

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
                .addUpgradeRequirement("Anti-inflammatory");

        builder.createGenerator("Domesticated Tea Plant", leaves(75_000), leaves(500))
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
                .addUpgradeRequirement("Anti-inflammatory");

        builder.createGenerator("Tea Plantation", leaves(5e7), leaves(100_000))
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
                .addUpgradeRequirement("Drone Technology")
                .addGeneratorRequirement("Unconventional Tea")

                .addEfficiencyUpgrade("Storing", leaves(3e8), 2.5f)
                .addGeneratorRequirement("Tea Plantation", 1)

                .addEfficiencyUpgrade("Drying", leaves(3e10), 2)
                .addUpgradeRequirement("Storing")

                .addEfficiencyUpgrade("Roasting", leaves(8e11), 2.25f)
                .addUpgradeRequirement("Drying")

                .addEfficiencyUpgrade("Fermentation", leaves(3e14), 3.5f)
                .addUpgradeRequirement("Grinding");

        builder.createGenerator("Tea Evolution", leaves(2e6), 1.13f, cups(3))
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
                .addUpgradeRequirement("Monitoring System");

        builder.createGenerator("Matcha", cups(1e6), cups(200))
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
                .addUpgradeRequirement("Roasting");

        builder.createGenerator("Loose-Leaf Tea", cups(3e8), cups(25_000))
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
                .addUpgradeRequirement("Fermentation");

        builder.createGenerator("Infused Tea", cups(4e13), cups(2e9))
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
                .addUpgradeRequirement("Tea Brick");

        builder.createGenerator("Unconventional Tea", cups(5e18), cups(1e15))
                .addUpgradeRequirement("Masala Chai")

                .addEfficiencyUpgrade("Iced Tea", cups(4e20), 2)
                .addGeneratorRequirement("Unconventional Tea", 1)

                .addEfficiencyUpgrade("Cold Brew", cups(3e21), 2.5f)
                .addUpgradeRequirement("Iced Tea")

                .addEfficiencyUpgrade("Herbal Tea", cups(7e22), 6)
                .addUpgradeRequirement("Iced Tea")

                .addEfficiencyUpgrade("Tea Latte", cups(2e23), 5)
                .addUpgradeRequirement("Herbal Tea")

                .addEfficiencyUpgrade("Tea Cocktail", cups(1.5e24), 21)
                .addUpgradeRequirement("Herbal Tea")

                .addEfficiencyUpgrade("Bubble Tea", cups(3e25), 6)
                .addUpgradeRequirement("Tea Latte")

                .addEfficiencyUpgrade("Tea Bag", leaves(6e18), 2.5f)
                .addGeneratorRequirement("Unconventional Tea", 1)
                .addUpgradeRequirement("Storage Jar")

                .addEfficiencyUpgrade("Vacuum Sealer", leaves(3e19), 2.5f)
                .addUpgradeRequirement("Tea Bag");

        builder.createGenerator("Virtual Tea", cups(5e22), cups(4e18))
                .addUpgradeRequirement("AI Automation")

                .addEfficiencyUpgrade("Tea Simulator", cups(5e23), 4f)
                .addGeneratorRequirement("Virtual Tea", 1)

                .addEfficiencyUpgrade("Online Tea Ceremony", cups(1e25), 6f)
                .addGeneratorRequirement("Virtual Tea", 1)

                .addEfficiencyUpgrade("Shared Serenity", cups(1e26), 2)
                .addUpgradeRequirement("Tea Simulator")
                .addUpgradeRequirement("Online Tea Ceremony");

        builder.resolveRequirements();
        return garden;
    }

    void main() {
        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

        Garden.generateGeneratorUpgradesNames(garden);

        setGeneratorCount(garden, state, "Virtual Tea", 0);
        setGeneratorCount(garden, state, "Unconventional Tea", 0);
        setGeneratorCount(garden, state, "Infused Tea", 0);
        setGeneratorCount(garden, state, "Loose-Leaf Tea", 0);
        setGeneratorCount(garden, state, "Matcha", 0);
        setGeneratorCount(garden, state, "Tea Evolution", 0);
        setGeneratorCount(garden, state, "Tea Plantation", 0);
        setGeneratorCount(garden, state, "Domesticated Tea Plant", 0);
        setGeneratorCount(garden, state, "Wild Tea Plant", 1);

        String[] boughtUpgrades = {
//                "Cultivation",
//                "Health Benefits",
//                "Defense Response",
//                "Tea Meals",
//                "Chagayu",
//                "Herbal Medicine",
//                "Ochazuke",

//                "Origin Myth",
//                "Calm Body and Mind",
//                "Digestion",
//                "Anti-inflammatory",
//                "Weight Management",

//                "Pruning",
//                "Harvesting",
//                "Scissors",
//                "Harvesting Machinery",
//                "Soil Acidity",
//                "Fertilizer",
//                "Pest and Disease Control",
//                "Vertical Farming",
//                "Irrigation System",
//                "Mechanical Plucking",
//                "Drone Technology",
//                "Monitoring System",
//                "Storing",
//                "Drying",
//                "Roasting",
//                "Fermentation",

//                "Tea Contest",
//                "Silk Road Trade",
//                "Arabic Shai",
//                "Moroccan Atai",
//                "AI Automation", // tea leaves

//                "Whisking",
//                "Foam Art",
//                "Chanoyu",
//                "Grinding", // tea leaves

//                "Steeping",
//                "Darye",
//                "Trade to Europe",
//                "Yunnan Pu-erh Tea", // tea leaves
//                "Tea Brick", // tea leaves

//                "British Tea",
//                "Masala Chai",
//                "Boiling",
//                "High Tea",
//                "Tea House",
//                "Assam Tea", // tea leaves
//                "Storage Jar", // tea leaves

//                "Iced Tea",
//                "Cold Brew",
//                "Herbal Tea",
//                "Tea Latte",
//                "Tea Cocktail",
//                "Bubble Tea",
//                "Tea Bag", // tea leaves
//                "Vacuum Sealer", // tea leaves

//                "Tea Simulator",
//                "Online Tea Ceremony",
//                "Shared Serenity",
        };

        for (String upgradeName : boughtUpgrades) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);

//        state.verifyAllUpgradesBought(garden);

        List<String> actions = new ArrayList<>();
        ImprovementCalculator.multiCurrencyApproach(garden, state, actions);
        actions.forEach(System.out::println);
    }
}
