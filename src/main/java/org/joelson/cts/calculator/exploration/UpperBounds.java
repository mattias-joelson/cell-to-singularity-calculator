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

        builder.createGenerator("Troposhpere", elevation(100), elevation(1))
//                .addUpgradeRequirement("Earth's Surface")

                .addEfficiencyUpgrade("Air", elevation(400), 1.5f)
                .addGeneratorRequirement("Troposhpere")

                .addEfficiencyUpgrade("Nitrogen", elevation(1_600), 1.75f)
                .addUpgradeRequirement("Air")

                .addEfficiencyUpgrade("Oxygen", elevation(7_000), 2)
                .addUpgradeRequirement("Air")

                .addEfficiencyUpgrade("Water", elevation(30_000), 2.25f)
                .addUpgradeRequirement("Oxygen")

                .addEfficiencyUpgrade("Sunlight", elevation(150_000), 2.5f)
                .addUpgradeRequirement("Water")

                .addEfficiencyUpgrade("Infrared Radiation", elevation(600_000), 3)
                .addUpgradeRequirement("Sunlight")

                .addEfficiencyUpgrade("Vapor", elevation(3e6), 3.5f)
                .addUpgradeRequirement("Infrared Radiation")
                .addUpgradeRequirement("Water")

                .addEfficiencyUpgrade("Pressure", elevation(4.25e9), 100)
                .addUpgradeRequirement("Stratus")

                .addEfficiencyUpgrade("Wind", elevation(2e10), 4)
                .addUpgradeRequirement("Pressure")

                .addEfficiencyUpgrade("Fronts", elevation(9e10), 7)
                .addUpgradeRequirement("Wind")

                .addEfficiencyUpgrade("Jet Stream", elevation(1e11), 10)
                .addUpgradeRequirement("Wind")

                .addEfficiencyUpgrade("Visible Light", elevation(1.9e19), 1e7f)
                .addUpgradeRequirement("Thunder")

                .addEfficiencyUpgrade("Rainbow", elevation(1.3e20), 4)
                .addUpgradeRequirement("Visible Light")
                .addUpgradeRequirement("Vapor")

                .addEfficiencyUpgrade("UV Radiation", elevation(6e20), 5)
                .addUpgradeRequirement("Thunder")

                .addEfficiencyUpgrade("Ozone", elevation(2.75e21), 6)
                .addUpgradeRequirement("UV Radiation");

        builder.createGenerator("Cloud", elevation(1e7), elevation(10_000))
                .addUpgradeRequirement("Vapor")

                .addEfficiencyUpgrade("Cumulus", elevation(4.5e7), 1.3f)
                .addGeneratorRequirement("Cloud")

                .addEfficiencyUpgrade("Cirrus", elevation(2e8), 1.6f)
                .addGeneratorRequirement("Cloud")

                .addEfficiencyUpgrade("Stratus", elevation(9.2e8), 2.2f)
                .addGeneratorRequirement("Cloud")

                .addEfficiencyUpgrade("Cumulonimbus", elevation(3e13), 1_000)
                .addGeneratorRequirement("Storm")

                .addEfficiencyUpgrade("Nimbostratus", elevation(3e16), 6_500)
                .addUpgradeRequirement("Thunder")

                .addEfficiencyUpgrade("Asperitas", elevation(1.5e17), 4)
                .addUpgradeRequirement("Thunder")

                .addEfficiencyUpgrade("Mammatus", elevation(5.6e17), 5)
                .addUpgradeRequirement("Asperitas")

                .addEfficiencyUpgrade("Lenticular", elevation(3e18), 6)
                .addUpgradeRequirement("Asperitas")

                .addEfficiencyUpgrade("Nacreous Clouds", elevation(2e25), 3_000_000)
                .addUpgradeRequirement("Ozone Layer")

                .addEfficiencyUpgrade("Noctilucent Clouds", elevation(5.2e30), 200_000)
                .addUpgradeRequirement("Meteoric Smoke Particles");

        builder.createGenerator("Storm", elevation(1e13), elevation(1e10))
                .addUpgradeRequirement("Fronts")

                .addEfficiencyUpgrade("Precipitation", elevation(1.5e14), 1.2f)
                .addGeneratorRequirement("Storm")

                .addEfficiencyUpgrade("Tornado", elevation(6e14), 1.6f)
                .addUpgradeRequirement("Wind")
                .addGeneratorRequirement("Storm")

                .addEfficiencyUpgrade("Lightning", elevation(1.8e15), 2.3f)
                .addGeneratorRequirement("Storm")

                .addEfficiencyUpgrade("Thunder", elevation(6e15), 3.5f)
                .addUpgradeRequirement("Lightning")

                .addEfficiencyUpgrade("Blue Jet Lightning", elevation(9e23), 1e7f)
                .addUpgradeRequirement("Lightning")
                .addUpgradeRequirement("Ozone Layer")

                .addEfficiencyUpgrade("Sprites", elevation(8.5e29), 1_000_000)
                .addUpgradeRequirement("Lightning")
                .addGeneratorRequirement("Mesosphere");

        builder.createGenerator("Stratosphere", elevation(1e22), elevation(1e19))
                .addUpgradeRequirement("Ozone")

                .addEfficiencyUpgrade("Ozone Layer", elevation(2e23), 1.5f)
                .addGeneratorRequirement("Stratosphere")

                .addEfficiencyUpgrade("Polar Vortes", elevation(1e24), 4)
                .addUpgradeRequirement("Ozone Layer")
                .addUpgradeRequirement("Jet Stream")

                .addEfficiencyUpgrade("Blue Sky", elevation(1.2e26), 4)
                .addUpgradeRequirement("Visible Light")
                .addUpgradeRequirement("Ozone Layer")

                .addEfficiencyUpgrade("Black Sky", elevation(4e26), 5)
                .addUpgradeRequirement("Blue Sky");

        builder.createGenerator("Mesosphere", elevation(1e27), elevation(1e24))
                .addUpgradeRequirement("Ozone Layer")

                .addEfficiencyUpgrade("Meteoric Smoke Particles", elevation(2.4e28), 2)
                .addUpgradeRequirement("Nacreous Clouds")
                .addGeneratorRequirement("Mesosphere")

                .addEfficiencyUpgrade("Shooting Stars", elevation(1.4e29), 2.5f)
                .addUpgradeRequirement("Meteoric Smoke Particles")

                .addEfficiencyUpgrade("ELVES", elevation(3.2e31), 5.5f)
                .addUpgradeRequirement("Sprites")

                .addEfficiencyUpgrade("Turbopause", elevation(2e32), 6)
                .addGeneratorRequirement("Mesosphere");

        builder.createGenerator("Thermosphere", elevation(1e33), elevation(1e30))
                .addUpgradeRequirement("Turbopause")

                .addEfficiencyUpgrade("Karman Line", elevation(2e34), 1.6f)
                .addGeneratorRequirement("Thermosphere")

                .addEfficiencyUpgrade("Electron", elevation(2e35), 2.2f)
                .addUpgradeRequirement("Karman Line")

                .addEfficiencyUpgrade("Positive Ion", elevation(3e35), 2.8f)
                .addUpgradeRequirement("Karman Line")

                .addEfficiencyUpgrade("Plasma", elevation(2.5e36), 3.8f)
                .addUpgradeRequirement("Electron")
                .addUpgradeRequirement("Positive Ion")

                .addEfficiencyUpgrade("Ionosphere", elevation(1.2e37), 4.4f)
                .addUpgradeRequirement("Plasma")

                .addEfficiencyUpgrade("Cosmic Rays", elevation(8e37), 4.6f)
                .addUpgradeRequirement("Ionosphere")

                .addEfficiencyUpgrade("Plasma Bubbles", elevation(5.3e38), 5.4f)
                .addUpgradeRequirement("Cosmic Rays")

                .addEfficiencyUpgrade("Airglow", elevation(3e39), 5.8f)
                .addUpgradeRequirement("Ionosphere")

                .addEfficiencyUpgrade("Solar Winds", elevation(3e40), 8)
                .addUpgradeRequirement("Ionosphere")

                .addEfficiencyUpgrade("Aurora", elevation(2.8e41), 10)
                .addUpgradeRequirement("Solar Winds")

                .addEfficiencyUpgrade("Magnetosphere", elevation(3.6e42), 48)
                .addUpgradeRequirement("Solar Winds");

        builder.resolveRequirements();

        builder.with("Storm")
                .addEfficiencyEffect("Cumulonimbus", 1.1f);

        builder.with("Stratosphere")
                .addEfficiencyEffect("Blue Jet Lightning", 2)
                .addEfficiencyEffect("Nacreous Clouds", 3);

        builder.with("Mesosphere")
                .addEfficiencyEffect("Sprites", 3)
                .addEfficiencyEffect("Noctilucent Clouds", 5);

        return garden;
    }

    void main() {
        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

//        Garden.generateGeneratorUpgradesNames(garden);

        setGeneratorCount(garden, state, "Thermosphere", 0);
        setGeneratorCount(garden, state, "Mesosphere", 0);
        setGeneratorCount(garden, state, "Stratosphere", 0);
        setGeneratorCount(garden, state, "Storm", 0);
        setGeneratorCount(garden, state, "Cloud", 0);
        setGeneratorCount(garden, state, "Troposhpere", 1);

        String[] boughtUpgrades = {
//                "Air",
//                "Nitrogen",
//                "Oxygen",
//                "Water",
//                "Sunlight",
//                "Infrared Radiation",
//                "Vapor",
//                "Pressure",
//                "Wind",
//                "Fronts",
//                "Jet Stream",
//                "Visible Light",
//                "Rainbow",
//                "UV Radiation",
//                "Ozone",

//                "Cumulus",
//                "Cirrus",
//                "Stratus",
//                "Cumulonimbus",
//                "Nimbostratus",
//                "Asperitas",
//                "Mammatus",
//                "Lenticular",
//                "Nacreous Clouds",
//                "Noctilucent Clouds",

//                "Cumulonimbus",
//                "Precipitation",
//                "Tornado",
//                "Lightning",
//                "Thunder",
//                "Blue Jet Lightning",
//                "Sprites",

//                "Nacreous Clouds",
//                "Blue Jet Lightning",
//                "Ozone Layer",
//                "Polar Vortes",
//                "Blue Sky",
//                "Black Sky",

//                "Noctilucent Clouds",
//                "Sprites",
//                "Meteoric Smoke Particles",
//                "Shooting Stars",
//                "ELVES",
//                "Turbopause",

//                "Karman Line",
//                "Electron",
//                "Positive Ion",
//                "Plasma",
//                "Ionosphere",
//                "Cosmic Rays",
//                "Plasma Bubbles",
//                "Airglow",
//                "Solar Winds",
//                "Aurora",
//                "Magnetosphere",
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
