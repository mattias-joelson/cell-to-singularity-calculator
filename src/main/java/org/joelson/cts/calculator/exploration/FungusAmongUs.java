package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class FungusAmongUs {

    private static final String CURRENCY = "Toadstools";

    private static Amount fungus(double amount) {
        return new Amount(CURRENCY, amount);
    }

    private static void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Fungus Among Us");
        garden.addCurrency(CURRENCY);
        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Fungi", fungus(20), 1.15f, fungus(1))

                .addEfficiencyUpgrade("Mycology", fungus(600), 1.5f)
                .addGeneratorRequirement("Fungi")

                .addEfficiencyUpgrade("Mushrooms", fungus(5_000), 11)
                .addGeneratorRequirement("Fungi")

                .addEfficiencyUpgrade("Yeast", fungus(2e15), 2e9f)
                .addGeneratorRequirement("Fungi")
                .addGeneratorRequirement("Tasty Fungi")

                .addEfficiencyUpgrade("Mold", fungus(2e18), 251)
                .addGeneratorRequirement("Fungi")
                .addUpgradeRequirement("Blue Cheese");

        builder.createGenerator("Fungal Living", fungus(700), 1.15f, fungus(5))
                .addUpgradeRequirement("Mycology")

                .addEfficiencyUpgrade("Diet", fungus(1_250), 2)
                .addGeneratorRequirement("Fungal Living")

                .addEfficiencyUpgrade("Reproduction", fungus(2_000), 2)
                .addGeneratorRequirement("Fungal Living")

                .addEfficiencyUpgrade("Hydrolytic Enzymes", fungus(50_000), 2)
                .addUpgradeRequirement("Diet")

                .addEfficiencyUpgrade("Spores", fungus(150_000), 3)
                .addUpgradeRequirement("Reproduction")

                .addEfficiencyUpgrade("Symbiosis", fungus(5e7), 51)
                .addGeneratorRequirement("Fungal Living")

                .addEfficiencyUpgrade("Growth", fungus(1e9), 11)
                .addUpgradeRequirement("Reproduction");

        builder.createGenerator("Fungal Cleaners", fungus(600_000), 1.15f, fungus(400))
                .addUpgradeRequirement("Hydrolytic Enzymes")

                .addEfficiencyUpgrade("Bioremediation", fungus(2e6), 3)
                .addGeneratorRequirement("Fungal Cleaners")

                .addEfficiencyUpgrade("Radiotrophic Fungi", fungus(1.5e7), 4)
                .addUpgradeRequirement("Bioremediation")

                .addEfficiencyUpgrade("Decomposition", fungus(1.5e10), 16)
                .addGeneratorRequirement("Fungal Cleaners")

                .addEfficiencyUpgrade("Fungal Burial", fungus(1.5e11), 26)
                .addUpgradeRequirement("Decomposition");

        builder.createGenerator("Fungi of the Forest", fungus(3e8), 1.15f, fungus(50_000))
                .addUpgradeRequirement("Symbiosis")

                .addEfficiencyUpgrade("Mycorrhiza", fungus(7e8), 2)
                .addGeneratorRequirement("Fungi of the Forest")

                .addEfficiencyUpgrade("Mycelial Network", fungus(9e9), 2)
                .addGeneratorRequirement("Fungi of the Forest")
                .addUpgradeRequirement("Growth")

                .addEfficiencyUpgrade("Ghost Orchid", fungus(4e10), 2)
                .addGeneratorRequirement("Fungi of the Forest")
                .addUpgradeRequirement("Decomposition")

                .addEfficiencyUpgrade("Defense Alert", fungus(8e10), 2)
                .addUpgradeRequirement("Mycelial Network");

        builder.createGenerator("Domesticated Fungi", fungus(1.5e11), 1.15f, fungus(6e6))
                .addUpgradeRequirement("Fungal Burial")

                .addEfficiencyUpgrade("Building Material", fungus(6e11), 4)
                .addGeneratorRequirement("Domesticated Fungi")

                .addEfficiencyUpgrade("Pesticides", fungus(1.5e12), 11)
                .addGeneratorRequirement("Domesticated Fungi")

                .addEfficiencyUpgrade("Medicine", fungus(2e13), 3)
                .addGeneratorRequirement("Domesticated Fungi")

                .addEfficiencyUpgrade("Antibiotics", fungus(4e20), 3_000_001)
                .addUpgradeRequirement("Medicine")
                .addUpgradeRequirement("Mold");

        builder.createGenerator("Tasty Fungi", fungus(3.5e13), 1.15f, fungus(5e9))
                .addUpgradeRequirement("Medicine")

                .addEfficiencyUpgrade("Edible Mushrooms", fungus(1.2e14), 3)
                .addGeneratorRequirement("Tasty Fungi")

                .addEfficiencyUpgrade("Truffles", fungus(8e14), 2.5f)
                .addUpgradeRequirement("Edible Mushrooms")

                .addEfficiencyUpgrade("Bread", fungus(4e16), 5)
                .addUpgradeRequirement("Truffles")

                .addEfficiencyUpgrade("Fermentation", fungus(9e16), 2)
                .addGeneratorRequirement("Tasty Fungi")

                .addEfficiencyUpgrade("Cheese Ripening", fungus(2.5e17), 5)
                .addUpgradeRequirement("Fermentation")

                .addEfficiencyUpgrade("Blue Cheese", fungus(7.5e17), 2.5f)
                .addUpgradeRequirement("Cheese Ripening")

                .addEfficiencyUpgrade("Alcohol", fungus(9e22), 75_001)
                .addUpgradeRequirement("Fermentation");

        builder.createGenerator("Unwelcome Fungi", fungus(1.2e19), 1.15f, fungus(2.5e14))
                .addUpgradeRequirement("Mold")

                .addEfficiencyUpgrade("Moldy Food", fungus(1.5e19), 9)
                .addGeneratorRequirement("Unwelcome Fungi")

                .addEfficiencyUpgrade("Fungal Infections", fungus(6e19), 2.5f)
                .addGeneratorRequirement("Unwelcome Fungi")

                .addEfficiencyUpgrade("Plant Blight", fungus(5e21), 6)
                .addUpgradeRequirement("Fungal Infections")

                .addEfficiencyUpgrade("Black Mold", fungus(1.2e22), 4)
                .addUpgradeRequirement("Moldy Food")

                .addEfficiencyUpgrade("Human Illness", fungus(4e22), 2)
                .addUpgradeRequirement("Black Mold")
                .addUpgradeRequirement("Fungal Infections")

                .addEfficiencyUpgrade("Poisonous Mushrooms", fungus(3e24), 41)
                .addGeneratorRequirement("Unwelcome Fungi")

                .addEfficiencyUpgrade("Amanita", fungus(1e25), 5)
                .addUpgradeRequirement("Poisonous Mushrooms")

                .addEfficiencyUpgrade("Parasitic Fungi", fungus(1e26), 16)
                .addGeneratorRequirement("Unwelcome Fungi");

        builder.createGenerator("Mind-Altering Fungi", fungus(1e23), 1.15f, fungus(5e18))
                .addUpgradeRequirement("Alcohol")

                .addEfficiencyUpgrade("Scent & Taste", fungus(4e23), 7)
                .addGeneratorRequirement("Mind-Altering Fungi")

                .addEfficiencyUpgrade("Psychedelic Mushrooms", fungus(4e25), 16)
                .addGeneratorRequirement("Mind-Altering Fungi")
                .addUpgradeRequirement("Poisonous Mushrooms")

                .addEfficiencyUpgrade("Cordyceps", fungus(1e27), 2)
                .addUpgradeRequirement("Parasitic Fungi");

        builder.resolveRequirements();

        return garden;
    }

    void main() {
        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
        //state.setBoost(4);

//        Garden.generateGeneratorUpgradesNames(garden);

        setGeneratorCount(garden, state, "Mind-Altering Fungi", 0);
        setGeneratorCount(garden, state, "Unwelcome Fungi", 0);
        setGeneratorCount(garden, state, "Tasty Fungi", 0);
        setGeneratorCount(garden, state, "Domesticated Fungi", 0);
        setGeneratorCount(garden, state, "Fungi of the Forest", 0);
        setGeneratorCount(garden, state, "Fungal Cleaners", 0);
        setGeneratorCount(garden, state, "Fungal Living", 0);
        setGeneratorCount(garden, state, "Fungi", 1);

        String[] boughtUpgrades = {
//                "Mycology",
//                "Mushrooms",
//                "Yeast",
//                "Mold",

//                "Diet",
//                "Reproduction",
//                "Hydrolytic Enzymes",
//                "Spores",
//                "Symbiosis",
//                "Growth",

//                "Bioremediation",
//                "Radiotrophic Fungi",
//                "Decomposition",
//                "Fungal Burial",

//                "Mycorrhiza",
//                "Mycelial Network",
//                "Ghost Orchid",
//                "Defense Alert",

//                "Building Material",
//                "Pesticides",
//                "Medicine",
//                "Antibiotics",

//                "Edible Mushrooms",
//                "Truffles",
//                "Bread",
//                "Fermentation",
//                "Cheese Ripening",
//                "Blue Cheese",
//                "Alcohol",

//                "Moldy Food",
//                "Fungal Infections",
//                "Plant Blight",
//                "Black Mold",
//                "Human Illness",
//                "Poisonous Mushrooms",
//                "Amanita",
//                "Parasitic Fungi",

//                "Scent & Taste",
//                "Psychedelic Mushrooms",
//                "Cordyceps",
        };

        for (String upgradeName : boughtUpgrades) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);

//        state.verifyAllUpgradesBought(garden);

        List<String> actions = new ArrayList<>();
        ImprovementCalculator.singleCurrencyApproach(garden, state, actions);
        actions.forEach(System.out::println);
    }
}
