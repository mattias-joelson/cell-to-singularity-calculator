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
//                "Viruses", // contagions // check
//                "White Blood Cells", // check
//                "Lymphatic System", // check
//                "Gut Bacteria", // check
//                "Fever", // check
//                "Antibodies", // check
//                "Documentation", // contagions // check
//                "Four Humors", // contagions // check
//                "Fighting the Onryō", // contagions // check
//                "Quarantines", // contagions // check
//                "Quinine", // contagions // check
//                "Herbs and Rituals", // contagions // check
//                "Plague Doctors", // contagions // check
//                "Preservation", // contagions // check
//                "Scientific Survey", // contagions // check
//                "Germ Theory", // contagions // check
//                "Sanitation", // contagions // check
//                "Burning it Down", // contagions // check
//                "Vaccines", // contagions // check
//                "Masking", // contagions // check
//                "Extreme Hygiene", // contagions // check
//                "Antibiotics", // contagions // check
//                "Prevention", // contagions // check
//                "Medical Technology", // contagions // check
//                "2020-Present", // check

//                "Antoine Plague", // check
//                "Japanese Smallpox", // check
//                "Aztec Epidemic", // check
//                "AD 165-180", // immunity // check
//                "Bacteria", // check
//                "735-737", // immunity // check
//                "1520", // immunity // check

//                "Plague of Justinian", // check
//                "The Black Death", // check
//                "Third Plague Epidemic", // check
//                "AD 541-549", // immunity // check
//                "1346-1353", // immunity // check
//                "1900", // immunity // check

//                "Questions of Morality", // check
//                "Parasites and Protists", // check
//                "1494-1928", // immunity // check

//                "1486 - Present", // immunity // check

//                "Cocoliztli", // check
//                "1546-1576", // immunity // check

//                "Summer in Philadelphia", // check
//                "1793-1794", // immunity // check

//                "Urban Epidemic", // check
//                "1800-1922", // immunity // check

//                "The Blue Death", // check
//                "1817-1860", // immunity // check

//                "The New York Epidemic", // check
//                "1916", // immunity // check

//                "Spanish Flu", // check
//                "1918-1920", // immunity // check

//                "Russian Outbreak", // check
//                "1918-1922", // immunity // check

//                "Silent Government", // check
//                "1981-1988", // immunity // check

//                "Covid-19", // check
//                "New-Age Contagions", // check
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
