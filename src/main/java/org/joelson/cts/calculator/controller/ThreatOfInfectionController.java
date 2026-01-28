package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.ThreatOfInfection;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ThreatOfInfectionController {

    private static final String GARDEN_GET = "/threatofinfection";
    private static final String GARDEN_GENERATOR_UPDATE = "/threatofinfection-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/threatofinfection-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/threatofinfection-generator-decrement";
    private static final String GARDEN_UPGRADE = "/threatofinfection-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public ThreatOfInfectionController() {
        garden = ThreatOfInfection.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Coronavirus", 0);
        setGeneratorCount("HIV", 0);
        setGeneratorCount("Typhus", 0);
        setGeneratorCount("Influenza", 0);
        setGeneratorCount("Polio", 0);
        setGeneratorCount("Cholera", 0);
        setGeneratorCount("Tuberculosis", 0);
        setGeneratorCount("Yellow Fever", 0);
        setGeneratorCount("Salmonella", 0);
        setGeneratorCount("Malaria", 0);
        setGeneratorCount("Syphilis", 0);
        setGeneratorCount("Plague", 0);
        setGeneratorCount("Smallpox", 0);
        setGeneratorCount("Human Life", 1);

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
    }

    private void setGeneratorCount(String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    @GetMapping(GARDEN_GET)
    public String setInStone(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String setInStoneGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String setInStoneGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String setInStoneGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String setInStoneUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
