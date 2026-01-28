package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class ThreatOfInfection {

    private static final String IMMUNITY_CURRENCY = "Immunity";
    private static final String CONTAGIONS_CURRENCY = "Contagions";

    private static final String[] CURRENCIES = { IMMUNITY_CURRENCY, CONTAGIONS_CURRENCY };

    private static Amount immunity(double amount) {
        return new Amount(IMMUNITY_CURRENCY, amount);
    }

    private static Amount contagions(double amount) {
        return new Amount(CONTAGIONS_CURRENCY, amount);
    }

    private void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Threat of Infection");
        for (String currency : CURRENCIES) {
            garden.addCurrency(currency);
        }

        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Human Life", immunity(30), 1.11f, immunity(1))

                .addEfficiencyUpgrade("Viruses", contagions(25), 1)
                .addGeneratorRequirement("Human Life")

                .addEfficiencyUpgrade("White Blood Cells", immunity(500), 2.5f)
                .addGeneratorRequirement("Human Life")
                .addUpgradeRequirement("Antoine Plague")

                .addEfficiencyUpgrade("Lymphatic System", immunity(1e9), 6)
                .addGeneratorRequirement("Human Life")
                .addUpgradeRequirement("The Black Death")

                .addEfficiencyUpgrade("Gut Bacteria", immunity(3.2e21), 9)
                .addGeneratorRequirement("Human Life")
                .addUpgradeRequirement("The Blue Death")

                .addEfficiencyUpgrade("Fever", immunity(1e27), 26)
                .addGeneratorRequirement("Human Life")
                .addUpgradeRequirement("Spanish Flu")

                .addEfficiencyUpgrade("Antibodies", immunity(2e33), 6)
                .addGeneratorRequirement("Human Life")
                .addUpgradeRequirement("1494-1928")

                .addEfficiencyUpgrade("Documentation", contagions(10_000), 5)
                .addUpgradeRequirement("Antoine Plague")

                .addEfficiencyUpgrade("Four Humors", contagions(2e6), 3.5f)
                .addUpgradeRequirement("Plague of Justinian")

                .addEfficiencyUpgrade("Fighting the Onryō", contagions(1e8), 6)
                .addUpgradeRequirement("Japanese Smallpox")

                .addEfficiencyUpgrade("Quarantines", contagions(2e10), 11)
                .addUpgradeRequirement("The Black Death")

                .addEfficiencyUpgrade("Quinine", contagions(1e14), 501)
                .addGeneratorRequirement("Malaria")

                .addEfficiencyUpgrade("Herbs and Rituals", contagions(3e17), 51)
                .addUpgradeRequirement("Aztec Epidemic")

                .addEfficiencyUpgrade("Plague Doctors", contagions(2e19), 4)
                .addUpgradeRequirement("The Black Death")
                .addUpgradeRequirement("1520")

                .addEfficiencyUpgrade("Preservation", contagions(3e19), 5)
                .addUpgradeRequirement("Cocoliztli")

                .addEfficiencyUpgrade("Scientific Survey", contagions(6e22), 201)
                .addUpgradeRequirement("Summer in Philadelphia")

                .addEfficiencyUpgrade("Germ Theory", contagions(7e25), 31)
                .addUpgradeRequirement("Urban Epidemic")

                .addEfficiencyUpgrade("Sanitation", contagions(7e28), 16)
                .addUpgradeRequirement("The Blue Death")

                .addEfficiencyUpgrade("Burning it Down", contagions(1e33), 41)
                .addUpgradeRequirement("Third Plague Epidemic")

                .addEfficiencyUpgrade("Vaccines", contagions(1e35), 31)
                .addUpgradeRequirement("The New York Epidemic")

                .addEfficiencyUpgrade("Masking", contagions(1e38), 26)
                .addUpgradeRequirement("Spanish Flu")

                .addEfficiencyUpgrade("Extreme Hygiene", contagions(8e40), 11)
                .addUpgradeRequirement("Russian Outbreak")

                .addEfficiencyUpgrade("Antibiotics", contagions(1e42), 51)
                .addUpgradeRequirement("Questions of Morality")
                .addUpgradeRequirement("1918-1922")

                .addEfficiencyUpgrade("Prevention", contagions(3e45), 71)
                .addUpgradeRequirement("1494-1928")
                .addUpgradeRequirement("Silent Government")

                .addEfficiencyUpgrade("Medical Technology", contagions(4e48), 101)
                .addUpgradeRequirement("Covid-19")

                .addEfficiencyUpgrade("2020-Present", immunity(1e36), 2)
                .addUpgradeRequirement("Covid-19");

        builder.createGenerator("Smallpox", contagions(50), contagions(1))
                .addUpgradeRequirement("Viruses")

                .addEfficiencyUpgrade("Antoine Plague", contagions(300), 4)
                .addGeneratorRequirement("Smallpox")

                .addEfficiencyUpgrade("Japanese Smallpox", contagions(4e7), 51)
                .addGeneratorRequirement("Smallpox")
                .addUpgradeRequirement("Four Humors")

                .addEfficiencyUpgrade("Aztec Epidemic", contagions(4e16), 2e7f)
                .addGeneratorRequirement("Smallpox")
                .addUpgradeRequirement("Quinine")

                .addEfficiencyUpgrade("AD 165-180", immunity(200_000), 9)
                .addUpgradeRequirement("Documentation")

                .addEfficiencyUpgrade("Bacteria", contagions(200_000), 1)
                .addUpgradeRequirement("AD 165-180")

                .addEfficiencyUpgrade("735-737", immunity(3e7), 11)
                .addUpgradeRequirement("Fighting the Onryō")

                .addEfficiencyUpgrade("1520", immunity(2e14), 6)
                .addUpgradeRequirement("Herbs and Rituals");

        builder.createGenerator("Plague", contagions(75_000), contagions(200))
                .addUpgradeRequirement("Bacteria")

                .addEfficiencyUpgrade("Plague of Justinian", contagions(500_000), 3)
                .addGeneratorRequirement("Plague")

                .addEfficiencyUpgrade("The Black Death", contagions(8e9), 41)
                .addGeneratorRequirement("Plague")
                .addUpgradeRequirement("Fighting the Onryō")

                .addEfficiencyUpgrade("Third Plague Epidemic", contagions(5e30), 1e20f)
                .addGeneratorRequirement("Plague")
                .addUpgradeRequirement("1817-1860")

                .addEfficiencyUpgrade("AD 541-549", immunity(2e6), 5)
                .addUpgradeRequirement("Four Humors")

                .addEfficiencyUpgrade("1346-1353", immunity(4e9), 21)
                .addUpgradeRequirement("Quarantines")
                .addUpgradeRequirement("Lymphatic System")

                .addEfficiencyUpgrade("1900", immunity(1.5e24), 21)
                .addUpgradeRequirement("Burning it Down");

        builder.createGenerator("Syphilis", contagions(2e11), contagions(1e8))
                .addUpgradeRequirement("Bacteria")
                .addUpgradeRequirement("Quarantines")

                .addEfficiencyUpgrade("Questions of Morality", contagions(1e12), 11)
                .addGeneratorRequirement("Syphilis")

                .addEfficiencyUpgrade("Parasites and Protists", contagions(1e13), 1)
                .addUpgradeRequirement("Questions of Morality")

                .addEfficiencyUpgrade("1494-1928", immunity(1e31), 2e29f)
                .addUpgradeRequirement("Antibiotics");

        builder.createGenerator("Malaria", contagions(5e12), contagions(1e10))
                .addUpgradeRequirement("Parasites and Protists")

                .addEfficiencyUpgrade("1486 - Present", immunity(1e13), 101)
                .addUpgradeRequirement("Quinine");

        builder.createGenerator("Salmonella", contagions(2e17), contagions(2e13))
                .addUpgradeRequirement("Bacteria")
                .addUpgradeRequirement("Herbs and Rituals")

                .addEfficiencyUpgrade("Cocoliztli", contagions(3e18), 26)
                .addGeneratorRequirement("Salmonella")
                .addUpgradeRequirement("1520")

                .addEfficiencyUpgrade("1546-1576", immunity(3e16), 21)
                .addUpgradeRequirement("Preservation");

        builder.createGenerator("Yellow Fever", contagions(4e20), contagions(2.5e17))
                .addUpgradeRequirement("Viruses")
                .addUpgradeRequirement("Preservation")

                .addEfficiencyUpgrade("Summer in Philadelphia", contagions(1e22), 7)
                .addGeneratorRequirement("Yellow Fever")
                .addUpgradeRequirement("1486 - Present")

                .addEfficiencyUpgrade("1793-1794", immunity(5e18), 21)
                .addUpgradeRequirement("Scientific Survey");

        builder.createGenerator("Tuberculosis", contagions(1e24), contagions(4e20))
                .addUpgradeRequirement("Bacteria")
                .addUpgradeRequirement("Scientific Survey")

                .addEfficiencyUpgrade("Urban Epidemic", contagions(2e25), 3)
                .addGeneratorRequirement("Tuberculosis")

                .addEfficiencyUpgrade("1800-1922", immunity(1.5e20), 26)
                .addUpgradeRequirement("Germ Theory");

        builder.createGenerator("Cholera", contagions(4e27), contagions(1e24))
                .addUpgradeRequirement("Bacteria")
                .addUpgradeRequirement("Germ Theory")

                .addEfficiencyUpgrade("The Blue Death", contagions(1e28), 5)
                .addGeneratorRequirement("Cholera")

                .addEfficiencyUpgrade("1817-1860", immunity(3e22), 21)
                .addUpgradeRequirement("Sanitation")
                .addUpgradeRequirement("Gut Bacteria");

        builder.createGenerator("Polio", contagions(3e33), contagions(2.5e29))
                .addUpgradeRequirement("Viruses")
                .addUpgradeRequirement("Burning it Down")

                .addEfficiencyUpgrade("The New York Epidemic", contagions(8e33), 7)
                .addGeneratorRequirement("Polio")

                .addEfficiencyUpgrade("1916", immunity(6e25), 21)
                .addUpgradeRequirement("Vaccines");

        builder.createGenerator("Influenza", contagions(2e36), contagions(3e32))
                .addUpgradeRequirement("Viruses")
                .addUpgradeRequirement("Vaccines")

                .addEfficiencyUpgrade("Spanish Flu", contagions(1e37), 11)
                .addGeneratorRequirement("Influenza")

                .addEfficiencyUpgrade("1918-1920", immunity(4e28), 16)
                .addUpgradeRequirement("Fever");

        builder.createGenerator("Typhus", contagions(2e39), contagions(4e35))
                .addUpgradeRequirement("Bacteria")
                .addUpgradeRequirement("Fever")

                .addEfficiencyUpgrade("Russian Outbreak", contagions(1e40), 4)
                .addGeneratorRequirement("Typhus")

                .addEfficiencyUpgrade("1918-1922", immunity(4e29), 21)
                .addUpgradeRequirement("Extreme Hygiene");

        builder.createGenerator("HIV", contagions(1e43), contagions(7e38))
                .addUpgradeRequirement("Viruses")
                .addUpgradeRequirement("Antibiotics")

                .addEfficiencyUpgrade("Silent Government", contagions(5e44), 26)
                .addGeneratorRequirement("HIV")

                .addEfficiencyUpgrade("1981-1988", immunity(9e33), 41)
                .addUpgradeRequirement("Antibodies")
                .addUpgradeRequirement("Prevention");

        builder.createGenerator("Coronavirus", contagions(5e46), contagions(5e42))
                .addUpgradeRequirement("Viruses")
                .addUpgradeRequirement("Antibodies")

                .addEfficiencyUpgrade("Covid-19", contagions(2e47), 6)
                .addGeneratorRequirement("Coronavirus")

                .addEfficiencyUpgrade("New-Age Contagions", contagions(1e48), 6)
                .addUpgradeRequirement("Covid-19");


        builder.resolveRequirements();

        return garden;
    }

    void main() {

        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

//        Garden.generateGeneratorUpgradesNames(garden);

        setGeneratorCount(garden, state, "Coronavirus", 0);
        setGeneratorCount(garden, state, "HIV", 0);
        setGeneratorCount(garden, state, "Typhus", 0);
        setGeneratorCount(garden, state, "Influenza", 0);
        setGeneratorCount(garden, state, "Polio", 0);
        setGeneratorCount(garden, state, "Cholera", 0);
        setGeneratorCount(garden, state, "Tuberculosis", 0);
        setGeneratorCount(garden, state, "Yellow Fever", 0);
        setGeneratorCount(garden, state, "Salmonella", 0);
        setGeneratorCount(garden, state, "Malaria", 0);
        setGeneratorCount(garden, state, "Syphilis", 0);
        setGeneratorCount(garden, state, "Plague", 0);
        setGeneratorCount(garden, state, "Smallpox", 0);
        setGeneratorCount(garden, state, "Human Life", 1);

        String[] boughtUpdates = {
//                "Viruses", // contagions
//                "White Blood Cells",
//                "Lymphatic System",
//                "Gut Bacteria",
//                "Fever",
//                "Antibodies",
//                "Documentation", // contagions
//                "Four Humors", // contagions
//                "Fighting the Onryō", // contagions
//                "Quarantines", // contagions
//                "Quinine", // contagions
//                "Herbs and Rituals", // contagions
//                "Plague Doctors", // contagions
//                "Preservation", // contagions
//                "Scientific Survey", // contagions
//                "Germ Theory", // contagions
//                "Sanitation", // contagions
//                "Burning it Down", // contagions
//                "Vaccines", // contagions
//                "Masking", // contagions
//                "Extreme Hygiene", // contagions
//                "Antibiotics", // contagions
//                "Prevention", // contagions
//                "Medical Technology", // contagions
//                "2020-Present",

//                "Antoine Plague",
//                "Japanese Smallpox",
//                "Aztec Epidemic",
//                "AD 165-180", // immunity
//                "Bacteria",
//                "735-737", // immunity
//                "1520", // immunity

//                "Plague of Justinian",
//                "The Black Death",
//                "Third Plague Epidemic",
//                "AD 541-549", // immunity
//                "1346-1353", // immunity
//                "1900", // immunity

//                "Questions of Morality",
//                "Parasites and Protists",
//                "1494-1928", // immunity

//                "1486 - Present", // immunity

//                "Cocoliztli",
//                "1546-1576", // immunity

//                "Summer in Philadelphia",
//                "1793-1794", // immunity

//                "Urban Epidemic",
//                "1800-1922", // immunity

//                "The Blue Death",
//                "1817-1860", // immunity

//                "The New York Epidemic",
//                "1916", // immunity

//                "Spanish Flu",
//                "1918-1920", // immunity

//                "Russian Outbreak",
//                "1918-1922", // immunity

//                "Silent Government",
//                "1981-1988", // immunity

//                "Covid-19",
//                "New-Age Contagions",
        };

        for (String upgradeName : boughtUpdates) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);

//        GardenState.isAllUpgradesBought(garden, state);

        List<String> actions = new ArrayList<>();
        ImprovementCalculator.candidateApproach(garden, state, actions);
        actions.forEach(System.out::println);
    }
}
