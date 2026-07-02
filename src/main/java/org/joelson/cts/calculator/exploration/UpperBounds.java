package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class UpperBounds {

    private static final String ELEVATION_CURRENCY = "Elevation";

    private static Amount elevation(double amount) {
        return new Amount(ELEVATION_CURRENCY, amount);
    }

    private static void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Upper Bounds");
        garden.addCurrency(ELEVATION_CURRENCY);
        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Troposhpere", elevation(100), elevation(4))
//                .addUpgradeRequirement("Atmosphere")

                .addEfficiencyUpgrade("Air", elevation(200), 1.01f)
                .addGeneratorRequirement("Troposhpere")

                .addEfficiencyUpgrade("Nitrogen", elevation(420), 1.02f)
                .addUpgradeRequirement("Air")

                .addEfficiencyUpgrade("Oxygen", elevation(900), 1.03f)
                .addUpgradeRequirement("Air")

                .addEfficiencyUpgrade("Water", elevation(2_000), 1.04f)
                .addUpgradeRequirement("Oxygen")

                .addEfficiencyUpgrade("Sunlight", elevation(4_200), 1.05f)
                .addUpgradeRequirement("Water")

                .addEfficiencyUpgrade("Infrared Radiation", elevation(10_000), 1.1f)
                .addUpgradeRequirement("Sunlight")

                .addEfficiencyUpgrade("Vapor", elevation(22_000), 1.2f)
                .addUpgradeRequirement("Infrared Radiation")
                .addUpgradeRequirement("Water")

                .addEfficiencyUpgrade("Clouds", elevation(50_000), 1.3f)
                .addUpgradeRequirement("Vapor")

                .addEfficiencyUpgrade("Cumulus", elevation(120_000), 1.4f)
                .addUpgradeRequirement("Clouds")

                .addEfficiencyUpgrade("Pressure", elevation(300_000), 1.5f)
                .addUpgradeRequirement("Cumulus")
                .addUpgradeRequirement("Infrared Radiation")

                .addEfficiencyUpgrade("Wind", elevation(700_000), 1.7f)
                .addUpgradeRequirement("Pressure")

                .addEfficiencyUpgrade("Cirrus", elevation(1.75e6), 1.9f)
                .addUpgradeRequirement("Cumulus")
                .addUpgradeRequirement("Clouds")

                .addEfficiencyUpgrade("Fronts", elevation(4.5e6), 2.1f)
                .addUpgradeRequirement("Wind")

                .addEfficiencyUpgrade("Storm", elevation(1.2e7), 2.2f)
                .addUpgradeRequirement("Fronts")

                .addEfficiencyUpgrade("Cumlonimbus", elevation(3e7), 2.3f)
                .addUpgradeRequirement("Cirrus")
                .addUpgradeRequirement("Clouds")

                .addEfficiencyUpgrade("Tornado", elevation(7.5e7), 2.4f)
                .addUpgradeRequirement("Storm")
                .addUpgradeRequirement("Wind")

                .addEfficiencyUpgrade("Jet Stream", elevation(2.e8), 2.5f)
                .addUpgradeRequirement("Tornado")
                .addUpgradeRequirement("Wind")

                .addEfficiencyUpgrade("Stratus", elevation(5.6e8), 2.6f)
                .addUpgradeRequirement("Cumlonimbus")
                .addUpgradeRequirement("Clouds")

                .addEfficiencyUpgrade("Rain", elevation(1.5e9), 2.7f)
                .addUpgradeRequirement("Storm")

                .addEfficiencyUpgrade("Lightning", elevation(4.2e9), 2.8f)
                .addUpgradeRequirement("Storm")

                .addEfficiencyUpgrade("Thunder", elevation(1.2e10), 2.9f)
                .addUpgradeRequirement("Lightning")

                .addEfficiencyUpgrade("Nimbostratus", elevation(3.5e10), 2.95f)
                .addUpgradeRequirement("Stratus")
                .addUpgradeRequirement("Clouds")

                .addEfficiencyUpgrade("Visible Light", elevation(1e11), 3)
                .addUpgradeRequirement("Nimbostratus")
                .addUpgradeRequirement("Sunlight")

                .addEfficiencyUpgrade("Rainbow", elevation(3e11), 3.05f)
                .addUpgradeRequirement("Visible Light")
                .addUpgradeRequirement("Vapor")

                .addEfficiencyUpgrade("Mammatus", elevation(9e11), 3.1f)
                .addUpgradeRequirement("Nimbostratus")
                .addUpgradeRequirement("Clouds")

                .addEfficiencyUpgrade("UV Radiation", elevation(2.8e12), 3.15f)
                .addUpgradeRequirement("Mammatus")

                .addEfficiencyUpgrade("Lenticular Clouds", elevation(8.6e12), 3.2f)
                .addUpgradeRequirement("Mammatus")
                .addUpgradeRequirement("Clouds")

                .addEfficiencyUpgrade("Asperitas", elevation(2.7e13), 3.25f)
                .addUpgradeRequirement("Lenticular Clouds")
                .addUpgradeRequirement("Clouds")

                .addEfficiencyUpgrade("Ozone", elevation(8.5e13), 3.3f)
                .addUpgradeRequirement("Mammatus");

        builder.createGenerator("Stratosphere", elevation(1e14), elevation(5.2e11))
                .addUpgradeRequirement("Ozone")

                .addEfficiencyUpgrade("Ozone Layer", elevation(8.8e14), 1.2f)
                .addGeneratorRequirement("Stratosphere")

                .addEfficiencyUpgrade("Blue Jet Lightning", elevation(3e15), 1.4f)
                .addUpgradeRequirement("Ozone Layer")
                .addUpgradeRequirement("Lightning")

                .addEfficiencyUpgrade("Polar Vortes", elevation(1e16), 1.6f)
                .addUpgradeRequirement("Ozone Layer")
                .addUpgradeRequirement("Jet Stream")

                .addEfficiencyUpgrade("Nacreous Clouds", elevation(3e16), 1.8f)
                .addUpgradeRequirement("Asperitas")
                .addUpgradeRequirement("Clouds")

                .addEfficiencyUpgrade("Blue Sky", elevation(1e17), 2)
                .addUpgradeRequirement("Nacreous Clouds")
                .addUpgradeRequirement("Visible Light")

                .addEfficiencyUpgrade("Black Sky", elevation(3.8e17), 2.5f)
                .addUpgradeRequirement("Nacreous Clouds")
                .addUpgradeRequirement("Visible Light");

        builder.createGenerator("Mesosphere", elevation(1e18), elevation(5.2e15))
                .addUpgradeRequirement("Ozone Layer")

                .addEfficiencyUpgrade("Meteoric Smoke Particles", elevation(4.5e18), 1.2f)
                .addUpgradeRequirement("Nacreous Clouds")

                .addEfficiencyUpgrade("Shooting Stars", elevation(1.6e19), 1.4f)
                .addUpgradeRequirement("Meteoric Smoke Particles")

                .addEfficiencyUpgrade("Sprites", elevation(6e19), 1.6f)
                .addUpgradeRequirement("Shooting Stars")

                .addEfficiencyUpgrade("Noctilucent Clouds", elevation(2e20), 1.8f)
                .addUpgradeRequirement("Meteoric Smoke Particles")

                .addEfficiencyUpgrade("ELVES", elevation(8e20), 2)
                .addUpgradeRequirement("Sprites")

                .addEfficiencyUpgrade("Turbopause", elevation(3e21), 2.5f)
                .addUpgradeRequirement("ELVES")
                .addGeneratorRequirement("Mesosphere");

        builder.createGenerator("Thermosphere", elevation(1e22), elevation(5.2e19))
                .addUpgradeRequirement("Turbopause")

                .addEfficiencyUpgrade("Karman Line", elevation(4.2e22), 1.2f)
                .addGeneratorRequirement("Thermosphere")

                .addEfficiencyUpgrade("Ionized Gas", elevation(1.6e23), 1.4f)
                .addUpgradeRequirement("Karman Line")

                .addEfficiencyUpgrade("Electron", elevation(6.6e23), 1.6f)
                .addUpgradeRequirement("Ionized Gas")

                .addEfficiencyUpgrade("Positive Ion", elevation(2.5e24), 1.8f)
                .addUpgradeRequirement("Ionized Gas")

                .addEfficiencyUpgrade("Airglow", elevation(1e25), 2.3f)
                .addUpgradeRequirement("Karman Line")

                .addEfficiencyUpgrade("Cosmic Rays", elevation(4e25), 2.6f)
                .addUpgradeRequirement("Ionized Gas")

                .addEfficiencyUpgrade("Plasma Bubbles", elevation(1.5e26), 2.9f)
                .addUpgradeRequirement("Cosmic Rays")

                .addEfficiencyUpgrade("Solar Winds", elevation(6.5e26), 3.2f)
                .addUpgradeRequirement("Plasma Bubbles")

                .addEfficiencyUpgrade("Aurora Borealis", elevation(2.6e27), 3.5f)
                .addUpgradeRequirement("Solar Winds")

                .addEfficiencyUpgrade("Magnetosphere", elevation(1e28), 3.8f)
                .addUpgradeRequirement("Solar Winds")

                .addEfficiencyUpgrade("Ionosphere", elevation(5e28), 4.1f)
                .addUpgradeRequirement("Solar Winds");

        builder.createGenerator("Exosphere", elevation(1e30), elevation(5.2e27))
                .addUpgradeRequirement("Ionosphere");


        builder.resolveRequirements();

        return garden;
    }

    void main() {
        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

//        Garden.generateGeneratorUpgradesNames(garden);

        setGeneratorCount(garden, state, "Exosphere", 0);
        setGeneratorCount(garden, state, "Thermosphere", 0);
        setGeneratorCount(garden, state, "Mesosphere", 0);
        setGeneratorCount(garden, state, "Stratosphere", 0);
        setGeneratorCount(garden, state, "Troposhpere", 1);

        String[] boughtUpgrades = {
//                "Air", // check
//                "Nitrogen", // check
//                "Oxygen", // check
//                "Water", // check
//                "Sunlight", // check
//                "Infrared Radiation", // check
//                "Vapor", // check
//                "Clouds", // check
//                "Cumulus", // check
//                "Pressure", // check
//                "Wind", // check
//                "Cirrus", // check
//                "Fronts", // check
//                "Storm", // check
//                "Cumlonimbus", // check
//                "Tornado", // check
//                "Jet Stream", // check
//                "Stratus", // check
//                "Rain", // check
//                "Lightning", // check
//                "Thunder", // check
//                "Nimbostratus", // check
//                "Visible Light", // check
//                "Rainbow", // check
//                "Mammatus", // check
//                "UV Radiation", // check
//                "Lenticular Clouds", // check
//                "Asperitas", // check
//                "Ozone", // check

//                "Ozone Layer", // check
//                "Blue Jet Lightning", // check
//                "Polar Vortes", // check
//                "Nacreous Clouds", // check
//                "Blue Sky", // check
//                "Black Sky", // check

//                "Meteoric Smoke Particles", // check
//                "Shooting Stars", // check
//                "Sprites", // check
//                "Noctilucent Clouds", // check
//                "ELVES", // check
//                "Turbopause", // check

//                "Karman Line", // check
//                "Ionized Gas", // check
//                "Electron", // check
//                "Positive Ion", // check
//                "Airglow", // check
//                "Cosmic Rays", // check
//                "Plasma Bubbles", // check
//                "Solar Winds", // check
//                "Aurora Borealis", // check
//                "Magnetosphere", // check
//                "Ionosphere", // check
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
