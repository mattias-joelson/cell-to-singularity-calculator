package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class GoodVibrations {

    private static final String NOTES_CURRENCY = "Notes";
    private static final String SONGS_CURRENCY = "Songs";

    private static final String[] CURRENCIES = { NOTES_CURRENCY, SONGS_CURRENCY };

    private static Amount notes(double amount) {
        return new Amount(NOTES_CURRENCY, amount);
    }

    private static Amount songs(double amount) {
        return new Amount(SONGS_CURRENCY, amount);
    }

    private void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Good Vibrations");
        for (String currency : CURRENCIES) {
            garden.addCurrency(currency);
        }

        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Notes", notes(25), 1.15f, notes(1))
//                .addUpgradeRequirement("Invisible Force")

                .addEfficiencyUpgrade("Vibrations", notes(100), 2)
                .addGeneratorRequirement("Notes")

                .addEfficiencyUpgrade("Receiving Sound", notes(500), 1.75f)
                .addUpgradeRequirement("Vibrations")

                .addEfficiencyUpgrade("Processing Sound", notes(4000), 2.25f)
                .addUpgradeRequirement("Receiving Sound");

        builder.createGenerator("Sound Waves", notes(15_000), 1.15f, notes(50))
                .addUpgradeRequirement("Processing Sound")

                .addEfficiencyUpgrade("Amplitude", notes(44_000), 2.5f)
                .addGeneratorRequirement("Sound Waves")

                .addEfficiencyUpgrade("Wavelength", notes(600_000), 2.5f)
                .addUpgradeRequirement("Amplitude")

                .addEfficiencyUpgrade("Frequency", notes(2e6), 2.25f)
                .addUpgradeRequirement("Wavelength")

                .addEfficiencyUpgrade("Harmonics", notes(1e7), 2.5f)
                .addUpgradeRequirement("Frequency")

                .addEfficiencyUpgrade("Color of a Note", notes(3e7), 2.25f)
                .addUpgradeRequirement("Harmonics")

                .addEfficiencyUpgrade("Vibrato", notes(5e7), 1.75f)
                .addUpgradeRequirement("Harmonics");

        builder.createGenerator("Theory", notes(5e7), 1.15f, notes(10_000))
                .addUpgradeRequirement("Color of a Note")

                .addEfficiencyUpgrade("Pitch", notes(1.5e8), 2)
                .addGeneratorRequirement("Theory")

                .addEfficiencyUpgrade("Rhythm", notes(4e8), 2)
                .addGeneratorRequirement("Theory")

                .addEfficiencyUpgrade("African Polyrhythm", notes(7.5e8), 2)
                .addUpgradeRequirement("Rhythm")

                .addEfficiencyUpgrade("Notation", notes(3e9), 2.5f)
                .addUpgradeRequirement("Pitch")

                .addEfficiencyUpgrade("Semitones", notes(6e10), 2.5f)
                .addGeneratorRequirement("Early Innovations")
                .addUpgradeRequirement("Notation")

                .addEfficiencyUpgrade("Arabic Maqam", notes(2e11), 2f)
                .addUpgradeRequirement("Pitch")
                .addUpgradeRequirement("African Polyrhythm")

                .addEfficiencyUpgrade("Chinese Shi'er lü", notes(4.5e11), 2.5f)
                .addUpgradeRequirement("Arabic Maqam")

                .addEfficiencyUpgrade("Chords", notes(9e11), 2.5f)
                .addUpgradeRequirement("Semitones")

                .addEfficiencyUpgrade("Octave", notes(8.8e12), 5.4f)
                .addGeneratorRequirement("Instruments")
                .addUpgradeRequirement("Chords")

                .addEfficiencyUpgrade("Pentatonic Scale", notes(5e13), 6)
                .addUpgradeRequirement("Chinese Shi'er lü")

                .addEfficiencyUpgrade("Melody", notes(1e14), 4)
                .addUpgradeRequirement("Octave")
                .addUpgradeRequirement("Pentatonic Scale");

        builder.createGenerator("Early Innovations", notes(2e10), 1.15f, notes(1e6))
                .addUpgradeRequirement("Vibrato")

                .addEfficiencyUpgrade("Sticks and Rocks", notes(3e10), 2.25f)
                .addGeneratorRequirement("Early Innovations")

                .addEfficiencyUpgrade("Bone Flute", notes(8e11), 7)
                .addUpgradeRequirement("Sticks and Rocks")

                .addEfficiencyUpgrade("Write That Down", notes(2.5e12), 4)
                .addUpgradeRequirement("Bone Flute")

                .addEfficiencyUpgrade("Clay Tablets", notes(3e13), 6)
                .addUpgradeRequirement("Write That Down");

        builder.createGenerator("Instruments", notes(1e12), 1.15f, songs(1))
                .addUpgradeRequirement("Chords")
                .addUpgradeRequirement("Bone Flute")

                .addEfficiencyUpgrade("What's Next?", notes(8e14), 2.5f)
                .addUpgradeRequirement("Hurricane Hymn No. 6")

                .addEfficiencyUpgrade("Wind", songs(100), 2)
                .addGeneratorRequirement("Instruments")

                .addEfficiencyUpgrade("Voice", songs(1_500), 2.25f)
                .addGeneratorRequirement("Instruments")

                .addEfficiencyUpgrade("Ocarina", songs(8_000), 2)
                .addUpgradeRequirement("Wind")

                .addEfficiencyUpgrade("Keyboard", songs(150_000), 2.5f)
                .addGeneratorRequirement("Instruments")

                .addEfficiencyUpgrade("Percussion", songs(900_000), 1.75f)
                .addGeneratorRequirement("Instruments")

                .addEfficiencyUpgrade("Autotune", songs(8e6), 2f)
                .addUpgradeRequirement("Voice")

                .addEfficiencyUpgrade("Electrophones", songs(1.5e7), 2)
                .addGeneratorRequirement("Instruments")

                .addEfficiencyUpgrade("The King of Instruments", songs(4e7), 2)
                .addUpgradeRequirement("Keyboard")

                .addEfficiencyUpgrade("Xylophone", songs(9e7), 2)
                .addUpgradeRequirement("Percussion")

                .addEfficiencyUpgrade("String", songs(2.5e8), 3)
                .addGeneratorRequirement("Instruments")

                .addEfficiencyUpgrade("Synthesizer", songs(8e8), 3.5f)
                .addUpgradeRequirement("Electrophones")

                .addEfficiencyUpgrade("Lyre, Lyre", songs(3e9), 3.5f)
                .addUpgradeRequirement("String")

                .addEfficiencyUpgrade("Hurricane Hymn No. 6", songs(7e9), 6)
                .addUpgradeRequirement("Melody")
                .addUpgradeRequirement("Lyre, Lyre")
                .addUpgradeRequirement("Clay Tablets");


        builder.createGenerator("Modern Innovations", songs(1e10), 1.15f, songs(1e6))
                .addUpgradeRequirement("What's Next?")

                .addEfficiencyUpgrade("Phonograph", songs(2e11), 6)
                .addGeneratorRequirement("Modern Innovations")

                .addEfficiencyUpgrade("Mic Check", songs(7e11), 2)
                .addUpgradeRequirement("Phonograph")

                .addEfficiencyUpgrade("Listen Up", songs(2.5e12), 3)
                .addUpgradeRequirement("Mic Check")

                .addEfficiencyUpgrade("Radio", songs(1.5e14), 76)
                .addUpgradeRequirement("Listen Up")

                .addEfficiencyUpgrade("Portable Player", songs(2e15), 4)
                .addUpgradeRequirement("Radio")

                .addEfficiencyUpgrade("Digital Age", songs(8e16), 16)
                .addUpgradeRequirement("Portable Player")

                .addEfficiencyUpgrade("Streaming", songs(4e17), 6)
                .addUpgradeRequirement("Digital Age")

                .addEfficiencyUpgrade("Here, There, and Everywhere", songs(1e18), 2)
                .addUpgradeRequirement("Global Sensations")
                .addUpgradeRequirement("Streaming");

        builder.createGenerator("A Brief History", songs(5e12), 1.15f, songs(3e8))
                .addUpgradeRequirement("What's Next?")

                .addEfficiencyUpgrade("Ancient Times", songs(1.2e13), 4)
                .addGeneratorRequirement("A Brief History")

                .addEfficiencyUpgrade("Middle Ages", songs(7e13), 2)
                .addUpgradeRequirement("Ancient Times")

                .addEfficiencyUpgrade("Classical Clientele", songs(8e14), 3)
                .addUpgradeRequirement("Middle Ages")

                .addEfficiencyUpgrade("A New World", songs(7e15), 6)
                .addUpgradeRequirement("Classical Clientele")

                .addEfficiencyUpgrade("Beyond Borders", songs(3e16), 4)
                .addUpgradeRequirement("A New World")

                .addEfficiencyUpgrade("Global Sensations", songs(1.5e17), 3)
                .addUpgradeRequirement("Beyond Borders");

        builder.resolveRequirements();

        return garden;
    }

    void main() {

        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

//        Garden.generateGeneratorUpgradesNames(garden);

        setGeneratorCount(garden, state, "A Brief History", 0);
        setGeneratorCount(garden, state, "Modern Innovations", 0);
        setGeneratorCount(garden, state, "Instruments", 0);
        setGeneratorCount(garden, state, "Early Innovations", 0);
        setGeneratorCount(garden, state, "Theory", 0);
        setGeneratorCount(garden, state, "Sound Waves", 0);
        setGeneratorCount(garden, state, "Notes", 1);

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

//        state.verifyAllUpgradesBought(garden);

        List<String> actions = new ArrayList<>();
        ImprovementCalculator.multiCurrencyApproach(garden, state, actions);
        actions.forEach(System.out::println);
    }
}
