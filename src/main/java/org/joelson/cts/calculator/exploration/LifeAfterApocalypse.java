package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class LifeAfterApocalypse {

    private static final String CURRENCY = "LAA";

    private static Amount amount(double amount) {
        return new Amount(CURRENCY, amount);
    }

    private static void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Life After Apocalypse");
        garden.addCurrency(CURRENCY);
        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("L.U.C.A.", amount(40), 1.05f, amount(1))
//                .addUpgradeRequirement("Fire and Ice")

                .addEfficiencyUpgrade("Cambrian Explosion", amount(50), 1.5f)
                .addGeneratorRequirement("L.U.C.A.")

                .addEfficiencyUpgrade("Trilobites", amount(500), 1.5f)
                .addGeneratorRequirement("L.U.C.A.")
                .addUpgradeRequirement("Cambrian Explosion")

                .addEfficiencyUpgrade("Placoderms", amount(3e6), 16)
                .addGeneratorRequirement("L.U.C.A.")
                .addUpgradeRequirement("Ordovician Extinction")

                .addEfficiencyUpgrade("Tetrapods", amount(6e8), 11)
                .addGeneratorRequirement("L.U.C.A.")
                .addUpgradeRequirement("Devonian Extinction")

                .addEfficiencyUpgrade("Archosaurs", amount(1.5e11), 11)
                .addGeneratorRequirement("L.U.C.A.")
                .addUpgradeRequirement("Permian Extinction")

                .addEfficiencyUpgrade("Tyrannosaurus Rex", amount(3.5e13), 11)
                .addGeneratorRequirement("L.U.C.A.")
                .addUpgradeRequirement("Triassic Extinction")

                .addEfficiencyUpgrade("Homo Sapiens", amount(2e16), 13)
                .addGeneratorRequirement("L.U.C.A.")
                .addUpgradeRequirement("Cretaceous Extinction")

                .addEfficiencyUpgrade("Unknown Species", amount(4e19), 101)
                .addGeneratorRequirement("L.U.C.A.")
                .addUpgradeRequirement("Anthropocene Extinction")

                .addEfficiencyUpgrade("Space Loophole", amount(1e23), 1e5f)
                .addUpgradeRequirement("Solar Apocalypse")

                .addEfficiencyUpgrade("Life Beyond", amount(1e28), 2)
                .addUpgradeRequirement("Space Loophole")

                .addEfficiencyUpgrade("Ordovician Extinction", amount(1e8), 2)
                .addUpgradeRequirement("Anoxic Oceans")

                .addEfficiencyUpgrade("Devonian Extinction", amount(4.5e10), 6)
                .addUpgradeRequirement("Extinction Pulses")

                .addEfficiencyUpgrade("Permian Extinction", amount(1e14), 12)
                .addUpgradeRequirement("Ozone Destroyed")
                .addUpgradeRequirement("Acid Rain")
                .addUpgradeRequirement("Scorching Earth")

                .addEfficiencyUpgrade("Triassic Extinction", amount(3e15), 16)
                .addUpgradeRequirement("Carbon Amok (Again)")

                .addEfficiencyUpgrade("Cretaceous Extinction", amount(1.05e20), 41)
                .addUpgradeRequirement("Deccan Death Traps")
                .addUpgradeRequirement("Day into Night")

                .addEfficiencyUpgrade("Anthropocene Extinction", amount(9e23), 51)
                .addUpgradeRequirement("Climate Tipping Point")
                .addUpgradeRequirement("Dead Oceans?")
                .addUpgradeRequirement("Nuclear Winter?")
                .addUpgradeRequirement("Killer Space Rock?")
                .addUpgradeRequirement("Galactic Hazards?")

                .addEfficiencyUpgrade("Solar Apocalypse", amount(7e24), 126)
                .addUpgradeRequirement("An Existential Question");

        builder.createGenerator("Metazoan Seas", amount(350), 1.12f, amount(2))
                .addUpgradeRequirement("Cambrian Explosion")

                .addEfficiencyUpgrade("Trilobite World", amount(10_000), 2.5f)
                .addGeneratorRequirement("Metazoan Seas")

                .addEfficiencyUpgrade("Asteroid Bombardment", amount(70_000), 2)
                .addUpgradeRequirement("Trilobite World")

                .addEfficiencyUpgrade("Continental Collision", amount(140_000), 7)
                .addUpgradeRequirement("Trilobite World")

                .addEfficiencyUpgrade("Carbon Tipping Point", amount(2e6), 2.25f)
                .addUpgradeRequirement("Continental Collision")

                .addEfficiencyUpgrade("Ice Age!", amount(1e7), 2)
                .addUpgradeRequirement("Carbon Tipping Point")

                .addEfficiencyUpgrade("Anoxic Oceans", amount(3e7), 9)
                .addUpgradeRequirement("Ice Age!")

                .withUpgradeEffectBuilder()
                .addEfficiencyEffect("Ordovician Extinction", 0);

        builder.createGenerator("Age of Fishes", amount(200_000), 1.12f, amount(1_000))
                .addGeneratorRequirement("L.U.C.A.")
                .addUpgradeRequirement("Ordovician Extinction")

                .addEfficiencyUpgrade("Land Grab", amount(4.5e7), 8)
                .addGeneratorRequirement("Age of Fishes")

                .addEfficiencyUpgrade("Killer Trees", amount(6e8), 5)
                .addUpgradeRequirement("Land Grab")

                .addEfficiencyUpgrade("Killer Plankton", amount(8e9), 3)
                .addUpgradeRequirement("Killer Trees")

                .addEfficiencyUpgrade("Extinction Pulses", amount(3.25e10), 2)
                .addUpgradeRequirement("Killer Plankton")

                .withUpgradeEffectBuilder()
                .addEfficiencyEffect("Devonian Extinction", 0);

        builder.createGenerator("Pangean Life", amount(6e7), 1.12f, amount(80_000))
                .addGeneratorRequirement("L.U.C.A.")
                .addUpgradeRequirement("Devonian Extinction")

                .addEfficiencyUpgrade("Vertebrate World", amount(7e9), 6)
                .addGeneratorRequirement("Pangean Life")

                .addEfficiencyUpgrade("Mega-Volcanoes", amount(4.5e10), 3)
                .addUpgradeRequirement("Vertebrate World")

                .addEfficiencyUpgrade("Toxic Ash", amount(2.75e11), 3)
                .addUpgradeRequirement("Mega-Volcanoes")

                .addEfficiencyUpgrade("Ozone Destroyed", amount(6e11), 4)
                .addUpgradeRequirement("Toxic Ash")

                .addEfficiencyUpgrade("Acid Rain", amount(6e12), 2.25f)
                .addUpgradeRequirement("Toxic Ash")

                .addEfficiencyUpgrade("Carbon Amok", amount(1.5e13), 2.25f)
                .addUpgradeRequirement("Toxic Ash")

                .addEfficiencyUpgrade("Scorching Earth", amount(4.5e13), 3)
                .addUpgradeRequirement("Carbon Amok")

                .withUpgradeEffectBuilder()
                .addEfficiencyEffect("Permian Extinction", 0);

        builder.createGenerator("Mesozoic Dawn", amount(3e10), 1.12f, amount(2e7))
                .addGeneratorRequirement("L.U.C.A.")
                .addUpgradeRequirement("Permian Extinction")

                .addEfficiencyUpgrade("Crocodile World", amount(1.75e12), 6)
                .addGeneratorRequirement("Mesozoic Dawn")

                .addEfficiencyUpgrade("Pangean Rift", amount(9e12), 11)
                .addUpgradeRequirement("Crocodile World")

                .addEfficiencyUpgrade("Carbon Amok (Again)", amount(3.5e14), 6)
                .addUpgradeRequirement("Pangean Rift")

                .withUpgradeEffectBuilder()
                .addEfficiencyEffect("Triassic Extinction", 0);

        builder.createGenerator("Reign of Dinosaurs", amount(8e12), 1.12f, amount(5e9))
                .addGeneratorRequirement("L.U.C.A.")
                .addUpgradeRequirement("Triassic Extinction")

                .addEfficiencyUpgrade("Deccan Death Traps", amount(3.75e14), 6)
                .addGeneratorRequirement("Reign of Dinosaurs")

                .addEfficiencyUpgrade("Killer Space Rock!", amount(4.5e15), 4)
                .addGeneratorRequirement("Reign of Dinosaurs")

                .addEfficiencyUpgrade("Shook and Boom", amount(3.75e16), 6)
                .addUpgradeRequirement("Killer Space Rock!")

                .addEfficiencyUpgrade("Quake and Slide", amount(3.25e17), 4.5f)
                .addUpgradeRequirement("Killer Space Rock!")

                .addEfficiencyUpgrade("Meteorite Bombs", amount(1.75e18), 2.25f)
                .addUpgradeRequirement("Killer Space Rock!")

                .addEfficiencyUpgrade("Broiled Earth", amount(4.25e18), 3)
                .addUpgradeRequirement("Killer Space Rock!")

                .addEfficiencyUpgrade("Day into Night", amount(1.3e19), 6)
                .addUpgradeRequirement("Killer Space Rock!")

                .withUpgradeEffectBuilder()
                .addEfficiencyEffect("Cretaceous Extinction", 0);

        builder.createGenerator("Age of Mammals", amount(8e15), 1.12f, amount(1e12))
                .addGeneratorRequirement("L.U.C.A.")
                .addUpgradeRequirement("Cretaceous Extinction")

                .addEfficiencyUpgrade("Thermal Maximum", amount(2e17), 6)
                .addGeneratorRequirement("Age of Mammals")

                .addEfficiencyUpgrade("Ice Age (Again)", amount(7.25e17), 3)
                .addUpgradeRequirement("Thermal Maximum")

                .addEfficiencyUpgrade("Anthropocene", amount(3e18), 11)
                .addUpgradeRequirement("Ice Age (Again)")

                .addEfficiencyUpgrade("Human Impact", amount(9e19), 16)
                .addUpgradeRequirement("Anthropocene")

                .addEfficiencyUpgrade("Extinction Threats", amount(2e21), 6)
                .addUpgradeRequirement("Human Impact")

                .addEfficiencyUpgrade("Climate Tipping Point", amount(1.25e22), 2)
                .addUpgradeRequirement("Extinction Threats")

                .addEfficiencyUpgrade("Dead Oceans?", amount(3e22), 2)
                .addUpgradeRequirement("Extinction Threats")
                .addUpgradeRequirement("Climate Tipping Point")

                .addEfficiencyUpgrade("Nuclear Winter?", amount(6.5e22), 2)
                .addUpgradeRequirement("Extinction Threats")
                .addUpgradeRequirement("Dead Oceans?")

                .addEfficiencyUpgrade("Killer Space Rock?", amount(1.5e23), 2)
                .addUpgradeRequirement("Extinction Threats")
                .addUpgradeRequirement("Nuclear Winter?")

                .addEfficiencyUpgrade("Galactic Hazards?", amount(3e23), 2)
                .addUpgradeRequirement("Extinction Threats")
                .addUpgradeRequirement("Killer Space Rock?")

                .withUpgradeEffectBuilder()
                .addEfficiencyEffect("Anthropocene Extinction", 0);

        builder.createGenerator("Cockroach World?", amount(5e18), 1.12f, amount(1e15))
                .addGeneratorRequirement("L.U.C.A.")
                .addUpgradeRequirement("Anthropocene Extinction")

                .addEfficiencyUpgrade("An Existential Question", amount(7e20), 1e4f)
                .addGeneratorRequirement("Cockroach World?")

                .withUpgradeEffectBuilder()
                .addEfficiencyEffect("Solar Apocalypse", 0);

        builder.resolveRequirements();

        return garden;
    }

    void main() {
        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

//        Garden.generateGeneratorUpgradesNames(garden);

        setGeneratorCount(garden, state, "Cockroach World?", 0);
        setGeneratorCount(garden, state, "Age of Mammals", 0);
        setGeneratorCount(garden, state, "Reign of Dinosaurs", 0);
        setGeneratorCount(garden, state, "Mesozoic Dawn", 0);
        setGeneratorCount(garden, state, "Pangean Life", 0);
        setGeneratorCount(garden, state, "Age of Fishes", 0);
        setGeneratorCount(garden, state, "Metazoan Seas", 0);
        setGeneratorCount(garden, state, "L.U.C.A.", 1);

        String[] boughtUpgrades = {
//                "Cambrian Explosion", // check
//                "Trilobites", // check
//                "Placoderms", // check
//                "Tetrapods", // check
//                "Archosaurs", // check
//                "Tyrannosaurus Rex", // check
//                "Homo Sapiens", // check
//                "Unknown Species", // check
//                "Space Loophole", // check
//                "Life Beyond", // check
//                "Ordovician Extinction", // check
//                "Devonian Extinction", // check
//                "Permian Extinction", // check
//                "Triassic Extinction", // check
//                "Cretaceous Extinction", // check
//                "Anthropocene Extinction", // check
//                "Solar Apocalypse", // check

//                "Ordovician Extinction", // check
//                "Trilobite World", // check
//                "Asteroid Bombardment", // check
//                "Continental Collision", // check
//                "Carbon Tipping Point", // check
//                "Ice Age!", // check
//                "Anoxic Oceans", // check

//                "Devonian Extinction", // check
//                "Land Grab", // check
//                "Killer Trees", // check
//                "Killer Plankton", // check
//                "Extinction Pulses", // check

//                "Permian Extinction", // check
//                "Vertebrate World", // check
//                "Mega-Volcanoes", // check
//                "Toxic Ash", // check
//                "Ozone Destroyed", // check
//                "Acid Rain", // check
//                "Carbon Amok", // check
//                "Scorching Earth", // check

//                "Triassic Extinction", // check
//                "Crocodile World", // check
//                "Pangean Rift", // check
//                "Carbon Amok (Again)", // check

//                "Cretaceous Extinction", // check
//                "Deccan Death Traps", // check
//                "Killer Space Rock!", // check
//                "Shook and Boom", // check
//                "Quake and Slide", // check
//                "Meteorite Bombs", // check
//                "Broiled Earth", // check
//                "Day into Night", // check

//                "Anthropocene Extinction", // check
//                "Thermal Maximum", // check
//                "Ice Age (Again)", // check
//                "Anthropocene", // check
//                "Human Impact", // check
//                "Extinction Threats", // check
//                "Climate Tipping Point", // check
//                "Dead Oceans?", // check
//                "Nuclear Winter?", // check
//                "Killer Space Rock?", // check
//                "Galactic Hazards?", // check

//                "Solar Apocalypse", // check
//                "An Existential Question", // check
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
