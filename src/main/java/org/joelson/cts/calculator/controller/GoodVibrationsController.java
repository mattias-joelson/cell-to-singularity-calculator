package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.GoodVibrations;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GoodVibrationsController {

    private static final String GARDEN_GET = "/goodvibrations";
    private static final String GARDEN_GENERATOR_UPDATE = "/goodvibrations-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/goodvibrations-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/goodvibrations-generator-decrement";
    private static final String GARDEN_UPGRADE = "/goodvibrations-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public GoodVibrationsController() {
        garden = GoodVibrations.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("A Brief History", 0);
        setGeneratorCount("Modern Innovations", 0);
        setGeneratorCount("Instruments", 0);
        setGeneratorCount("Early Innovations", 0);
        setGeneratorCount("Theory", 0);
        setGeneratorCount("Sound Waves", 0);
        setGeneratorCount("Notes", 1);

        String[] boughtUpdates = {
//                "Vibrations",
//                "Receiving Sound",
//                "Processing Sound",

//                "Amplitude",
//                "Wavelength",
//                "Frequency",
//                "Harmonics",
//                "Color of a Note",
//                "Vibrato",

//                "Pitch",
//                "Rhythm",
//                "African Polyrhythm",
//                "Notation",
//                "Semitones",
//                "Arabic Maqam",
//                "Chinese Shi'er lü",
//                "Chords",
//                "Octave",
//                "Pentatonic Scale",
//                "Melody",

//                "Sticks and Rocks",
//                "Bone Flute",
//                "Write That Down",
//                "Clay Tablets",

//                "What's Next?", // notes
//                "Wind",
//                "Voice",
//                "Ocarina",
//                "Keyboard",
//                "Percussion",
//                "Autotune",
//                "Electrophones",
//                "The King of Instruments",
//                "Xylophone",
//                "String",
//                "Synthesizer",
//                "Lyre, Lyre",
//                "Hurricane Hymn No. 6",

//                "Phonograph",
//                "Mic Check",
//                "Listen Up",
//                "Radio",
//                "Portable Player",
//                "Digital Age",
//                "Streaming",
//                "Here, There, and Everywhere",

//                "Ancient Times",
//                "Middle Ages",
//                "Classical Clientele",
//                "A New World",
//                "Beyond Borders",
//                "Global Sensations",
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
    public String goodVibrations(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String goodVibrationsGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String goodVibrationsGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String goodVibrationsGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String goodVibrationsUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}
