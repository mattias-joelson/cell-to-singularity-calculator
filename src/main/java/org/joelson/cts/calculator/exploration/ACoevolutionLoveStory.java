package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class ACoevolutionLoveStory {

    private static final String CURRENCY = "Pollen";

    private static Amount pollen(double amount) {
        return new Amount(CURRENCY, amount);
    }

    private static void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("A Coevolution Love Story");
        garden.addCurrency(CURRENCY);
        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Flowers", pollen(40), 1.1f, pollen(1))

                .addEfficiencyUpgrade("Naked Seeds", pollen(1_000), 4)
                .addGeneratorRequirement("Flowers", 1)
                .addGeneratorRequirement("Bees", 1)

                .addEfficiencyUpgrade("Bloom Boom", pollen(8e7), 501)
                .addUpgradeRequirement("The Showy Magnolia")

                .addEfficiencyUpgrade("Size and Structure", pollen(1e16), 5_500_001)
                .addGeneratorRequirement("The Art of Attraction", 1)

                .addEfficiencyUpgrade("Pollen Tag", pollen(3e24), 1_000_000_001)
                .addUpgradeRequirement("Orchid Flowers")

                .addEfficiencyUpgrade("Pollination Pinnacle", pollen(1e30), 10_001)
                .addUpgradeRequirement("Floral Oils")

                .addEfficiencyUpgrade("A New Suitor", pollen(8e30), 26)
                .addUpgradeRequirement("Pollination Pinnacle")
                .addUpgradeRequirement("Apex of Evolution");

        builder.createGenerator("Bees", pollen(500), pollen(450), 45)
                .addGeneratorRequirement("Flowers", 1)

                .addEfficiencyUpgrade("Wasteful Wind", pollen(4_000), 3)
                .addUpgradeRequirement("Naked Seeds")

                .addEfficiencyUpgrade("Flight", pollen(6_000), 2)
                .addUpgradeRequirement("Wasteful Wind")

                .addEfficiencyAutomatedUpgrade("The Hungry Beetle", pollen(7e6), 21)
                .addUpgradeRequirement("Flight")

                .addEfficiencyUpgrade("The Vegetarian Wasp", pollen(3e8), 5)
                .addUpgradeRequirement("Flight")

                .addSpeedUpgrade("Solitary Nests", pollen(3e9), 10)
                .addGeneratorRequirement("Primitive Bees", 1)

                .addEfficiencyUpgrade("UV Vision", pollen(8e14), 100_001)
                .addGeneratorRequirement("Primitive Bees", 1)

                .addEfficiencyUpgrade("Happy Ending", pollen(6e24), 10_000_000_001f)
                .addUpgradeRequirement("Pollen Tag")//.addUpgradeRequirement("Orchid Bees")

                .addEfficiencyUpgrade("Apex of Evolution", pollen(7e28), 10_001)
                .addGeneratorRequirement("The Hive Life", 1)
                .addUpgradeRequirement("Waggle Dance")

                .withUpgradeEffectBuilder()
                .addEfficiencyEffect("A New Suitor", 16);

        builder.createGenerator("Primitive Flowers", pollen(10_000), 1.1f, pollen(150))
                .addUpgradeRequirement("Wasteful Wind")

                .addEfficiencyUpgrade("Self-Marriage", pollen(50_000), 2)
                .addGeneratorRequirement("Primitive Flowers", 1)

                .addEfficiencyUpgrade("Self-Control", pollen(400_000), 1.5f)
                .addUpgradeRequirement("Self-Marriage")

                .addEfficiencyUpgrade("Stranger Marriage", pollen(900_000), 2)
                .addUpgradeRequirement("Self-Control")

                .addEfficiencyUpgrade("The Showy Magnolia", pollen(3e7), 2)
                .addUpgradeRequirement("Stranger Marriage");

        builder.createGenerator("Primitive Bees", pollen(4e8), pollen(1.5e8), 500)
                .addUpgradeRequirement("The Vegetarian Wasp")

                .addEfficiencyUpgrade("Johnston's Organ", pollen(5e9), 4)
                .addGeneratorRequirement("Primitive Bees", 1)

                .addAutomatedUpgrade("The Competition", pollen(7e9))
                .addGeneratorRequirement("Primitive Bees", 1)

                .addSpeedUpgrade("Birds Not Bees", pollen(3e10), 2)
                .addUpgradeRequirement("The Competition")

                .addSpeedUpgrade("Bumblebee Exclusive", pollen(5e13), 150)
                .addUpgradeRequirement("Birds Not Bees") // ???
                .addUpgradeRequirement("Snappy Snapdragons"); // ???

        builder.createGenerator("The Art of Attraction", pollen(4e10), 1.1f, pollen(7e6))
                .addGeneratorRequirement("Primitive Flowers", 1)
                .addGeneratorRequirement("Primitive Bees", 1)

                .addEfficiencyUpgrade("Come One or All?", pollen(3e11), 3)
                .addGeneratorRequirement("The Art of Attraction", 1)

                .addEfficiencyUpgrade("Dandelion Welcome Mat", pollen(7e11), 3)
                .addUpgradeRequirement("Come One or All?")

                .addEfficiencyUpgrade("Snappy Snapdragons", pollen(4e12), 6)
                .addUpgradeRequirement("Come One or All?")

                .addEfficiencyUpgrade("Early Willows", pollen(2e14), 5)
                .addUpgradeRequirement("Come One or All?")

                .addEfficiencyUpgrade("Color and Pattern", pollen(6e15), 11)
                .addGeneratorRequirement("The Art of Attraction", 1);

        builder.createGenerator("Food Banking", pollen(1.5e16), pollen(1.8e15), 1_800)
                .addGeneratorRequirement("Primitive Bees", 1)
                .addUpgradeRequirement("UV Vision")

                .addEfficiencyAutomatedUpgrade("Very Hairy Body", pollen(3e16), 3)
                .addGeneratorRequirement("Food Banking", 1)

                .addSpeedUpgrade("Pollen Brushes", pollen(5e16), 2)
                .addUpgradeRequirement("Very Hairy Body")

                .addEfficiencyUpgrade("Bristled Baskets", pollen(2e17), 6)
                .addUpgradeRequirement("Pollen Brushes")

                .addSpeedUpgrade("Buzz Pollination", pollen(7e17), 6)
                .addGeneratorRequirement("Food Banking", 1)

                .addEfficiencyUpgrade("Mechanical Mouthparts", pollen(1.4e21), 11)
                .addGeneratorRequirement("Food Banking", 1) // ???
                .addUpgradeRequirement("Nectar Safeguards") // ???

                .addSpeedUpgrade("Bandit Bees", pollen(2.5e21), 20)
                .addUpgradeRequirement("Mechanical Mouthparts")

                .addSpeedUpgrade("All-Purpose Oils", pollen(2.5e23), 4)
                .addUpgradeRequirement("Floral Oils")

                .addEfficiencyUpgrade("Orchid Bees", pollen(5e23), 9)
                .addUpgradeRequirement("Solitary Nests");

        builder.createGenerator("Bribery and Deception", pollen(3e18), 1.1f,
                        pollen(5e14))
                .addGeneratorRequirement("The Art of Attraction", 1)
                .addGeneratorRequirement("Food Banking", 1)

                .addEfficiencyUpgrade("Nectar Bribes", pollen(3e19), 3)
                .addGeneratorRequirement("Bribery and Deception", 1)

                .addEfficiencyUpgrade("Nectar Safeguards", pollen(2.5e20), 4)
                .addUpgradeRequirement("Nectar Bribes")

                .addEfficiencyUpgrade("Kidnappers!", pollen(1.25e22), 6)
                .addUpgradeRequirement("Nectar Bribes")

                .addEfficiencyUpgrade("Murderers!", pollen(2.5e22), 6)
                .addUpgradeRequirement("Kidnappers!")

                .addEfficiencyUpgrade("Floral Oils", pollen(8e22), 3)
                .addGeneratorRequirement("Bribery and Deception", 1)

                .addEfficiencyUpgrade("Orchid Flowers", pollen(8e23), 5)
                .addUpgradeRequirement("Floral Oils")
                .addUpgradeRequirement("Orchid Bees");

        builder.createGenerator("The Hive Life", pollen(8e25), pollen(3.6e26), 3_600)
                .addGeneratorRequirement("Food Banking", 1)
                .addUpgradeRequirement("Orchid Bees")

                .addSpeedAutomatedUpgrade("Baby Bees", pollen(8e25), 2)
                .addGeneratorRequirement("The Hive Life", 1)

                .addSpeedUpgrade("Old Foragers", pollen(3.5e26), 5)
                .addUpgradeRequirement("Baby Bees")

                .addEfficiencySpeedUpgrade("Waggle Dance", pollen(2e27), 11, 2)
                .addUpgradeRequirement("Old Foragers");

        builder.createGenerator("Human", pollen(2.8e31), pollen(2.88e32), 7_200)
                .addUpgradeRequirement("A New Suitor")

                .addSpeedAutomatedUpgrade("The Crops We Crave", pollen(8e31), 2)
                .addGeneratorRequirement("Human", 1)

                .addSpeedUpgrade("Our Favorite Bee", pollen(4e32), 10)
                .addUpgradeRequirement("The Crops We Crave")

                .addSpeedUpgrade("Africanized Bees", pollen(7e33), 10)
                .addUpgradeRequirement("Our Favorite Bee")

                .addEfficiencyUpgrade("Wild Decline", pollen(1e35), 11)
                .addGeneratorRequirement("Human", 1)

                .addSpeedUpgrade("Habitat Destruction", pollen(3e36), 3)
                .addUpgradeRequirement("Wild Decline")

                .addEfficiencyUpgrade("Varroa Destructor", pollen(4e37), 3)
                .addGeneratorRequirement("Human", 1)

                .addEfficiencyUpgrade("Colony Collapse Disorder", pollen(5e38), 6)
                .addUpgradeRequirement("Varroa Destructor")

                .addEfficiencyUpgrade("Till Death Do Us Part?", pollen(2e39), 6)
                .addUpgradeRequirement("Africanized Bees")
                .addUpgradeRequirement("Habitat Destruction")
                .addUpgradeRequirement("Colony Collapse Disorder");

        builder.resolveRequirements();

        return garden;
    }

    void main() {
        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
        //STATE.setBoost(4);

//        Garden.generateGeneratorUpgradesNames(garden);

        setGeneratorCount(garden, state, "Human", 0);
        setGeneratorCount(garden, state, "The Hive Life", 0);
        setGeneratorCount(garden, state, "Bribery and Deception", 0);
        setGeneratorCount(garden, state, "Food Banking", 0);
        setGeneratorCount(garden, state, "The Art of Attraction", 0);
        setGeneratorCount(garden, state, "Primitive Bees", 0);
        setGeneratorCount(garden, state, "Primitive Flowers", 0);
        setGeneratorCount(garden, state, "Bees", 0);
        setGeneratorCount(garden, state, "Flowers", 1);

        String[] boughtUpgrades = {
//                "Naked Seeds",
//                "Bloom Boom",
//                "Size and Structure",
//                "Pollen Tag",
//                "Pollination Pinnacle",
//                "A New Suitor",

//                "A New Suitor",
//                "Wasteful Wind",
//                "Flight",
//                "The Hungry Beetle",
//                "The Vegetarian Wasp",
//                "Solitary Nests",
//                "UV Vision",
//                "Happy Ending",
//                "Apex of Evolution",

//                "Self-Marriage",
//                "Self-Control",
//                "Stranger Marriage",
//                "The Showy Magnolia",

//                "Johnston's Organ",
//                "The Competition",
//                "Birds Not Bees",
//                "Bumblebee Exclusive",

//                "Come One or All?",
//                "Dandelion Welcome Mat",
//                "Snappy Snapdragons",
//                "Early Willows",
//                "Color and Pattern",

//                "Very Hairy Body",
//                "Pollen Brushes",
//                "Bristled Baskets",
//                "Buzz Pollination",
//                "Mechanical Mouthparts",
//                "Bandit Bees",
//                "All-Purpose Oils",
//                "Orchid Bees",

//                "Nectar Bribes",
//                "Nectar Safeguards",
//                "Kidnappers!",
//                "Murderers!",
//                "Floral Oils",
//                "Orchid Flowers",

//                "Baby Bees",
//                "Old Foragers",
//                "Waggle Dance",

//                "The Crops We Crave",
//                "Our Favorite Bee",
//                "Africanized Bees",
//                "Wild Decline",
//                "Habitat Destruction",
//                "Varroa Destructor",
//                "Colony Collapse Disorder",
//                "Till Death Do Us Part?",
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
