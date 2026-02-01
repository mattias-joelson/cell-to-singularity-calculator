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

//        builder.createGenerator("Notes", notes(25), notes(1))
//                .addUpgradeRequirement("Invisible Force");
//
//        builder.createGenerator("Sound Waves", notes(15_000), notes(50))
//                .addUpgradeRequirement("Processing Sound");
//
//        builder.createGenerator("Theory", notes(50e6), notes(10_000))
//                .addUpgradeRequirement("Color of A Note");

        builder.createGenerator("Notes", notes(25), 1.15f, notes(1)) // invisibleForce
//                .addUpgradeRequirement("Invisible Force")

                .addEfficiencyUpgrade("Vibrations", notes(100), 2) // notes
                .addGeneratorRequirement("Notes")

                .addEfficiencyUpgrade("Receiving Sound", notes(500), 1.75f) // vibrations
                .addUpgradeRequirement("Vibrations")

                .addEfficiencyUpgrade("Processing Sound", notes(4000), 2.25f) // receiving sound
                .addUpgradeRequirement("Receiving Sound");

        builder.createGenerator("Sound Waves", notes(15_000), 1.15f, notes(50)) // processing sound
                .addUpgradeRequirement("Processing Sound")

                .addEfficiencyUpgrade("Amplitude", notes(44_000), 2.5f) // sound waves
                .addGeneratorRequirement("Sound Waves")

                .addEfficiencyUpgrade("Wavelength", notes(600_000), 2.5f) // amplitude
                .addUpgradeRequirement("Amplitude")

                .addEfficiencyUpgrade("Frequency", notes(2e6), 2.25f) // wavelength
                .addUpgradeRequirement("Wavelength")

                .addEfficiencyUpgrade("Harmonics", notes(1e7), 2.5f) // frequency
                .addUpgradeRequirement("Frequency")

                .addEfficiencyUpgrade("Color of a Note", notes(3e7), 2.25f) // harmonics
                .addUpgradeRequirement("Harmonics")

                .addEfficiencyUpgrade("Vibrato", notes(5e7), 1.75f) // harmonics
                .addUpgradeRequirement("Harmonics");

        builder.createGenerator("Theory", notes(5e7), 1.15f, notes(10_000)) // inc // Color of a note
                .addUpgradeRequirement("Color of a Note")

                .addEfficiencyUpgrade("Pitch", notes(1.5e8), 2) // theory
                .addGeneratorRequirement("Theory")

                .addEfficiencyUpgrade("Rhythm", notes(4e8), 2) // theory
                .addGeneratorRequirement("Theory")

                .addEfficiencyUpgrade("African Polyrhythm", notes(7.5e8), 2) // rhythm
                .addUpgradeRequirement("Rhythm")

                .addEfficiencyUpgrade("Notation", notes(3e9), 2.5f) // pitch
                .addUpgradeRequirement("Pitch")

                .addEfficiencyUpgrade("Semitones", notes(6e10), 2.5f) // notation, early innovation
                .addGeneratorRequirement("Early Innovations")
                .addUpgradeRequirement("Notation")

                .addEfficiencyUpgrade("Arabic Maqam", notes(2e11), 2f) // pitch, african polyrhythm
                .addUpgradeRequirement("Pitch")
                .addUpgradeRequirement("African Polyrhythm")

                .addEfficiencyUpgrade("Chinese Shi'er lü", notes(4.5e11), 2.5f) // Arabic Maqam
                .addUpgradeRequirement("Arabic Maqam")

                .addEfficiencyUpgrade("Chords", notes(9e11), 2.5f) // semitones
                .addUpgradeRequirement("Semitones")

                .addEfficiencyUpgrade("Octave", notes(8.8e12), 5.4f) // chords
                .addGeneratorRequirement("Instruments")
                .addUpgradeRequirement("Chords")

                .addEfficiencyUpgrade("Pentatonic Scale", notes(5e13), 6) // // chinese
                .addUpgradeRequirement("Chinese Shi'er lü")

                .addEfficiencyUpgrade("Melody", notes(1e14), 4) // // octave, pentatonic
                .addUpgradeRequirement("Octave")
                .addUpgradeRequirement("Pentatonic Scale");

        builder.createGenerator("Early Innovations", notes(2e10), 1.15f, notes(1e6))// vibrato
                .addUpgradeRequirement("Vibrato")

                .addEfficiencyUpgrade("Sticks and Rocks", notes(3e10), 2.25f) // early innovations
                .addGeneratorRequirement("Early Innovations")

                .addEfficiencyUpgrade("Bone Flute", notes(8e11), 7) // sticks and rocks
                .addUpgradeRequirement("Sticks and Rocks")

                .addEfficiencyUpgrade("Write That Down", notes(2.5e12), 4) // Bone Flute
                .addUpgradeRequirement("Bone Flute")

                .addEfficiencyUpgrade("Clay Tablets", notes(3e13), 6) // write that down
                .addUpgradeRequirement("Write That Down");

        builder.createGenerator("Instruments", notes(1e12), 1.15f, songs(1))
                .addUpgradeRequirement("Chords")
                .addUpgradeRequirement("Bone Flute")

                .addEfficiencyUpgrade("What's Next?", notes(8e14), 2.5f)
                .addUpgradeRequirement("Hurricane Hymn No. 6")

                .addEfficiencyUpgrade("Wind", songs(100), 2) // instruments
                .addGeneratorRequirement("Instruments")

                .addEfficiencyUpgrade("Voice", songs(1_500), 2.25f) // instruments
                .addGeneratorRequirement("Instruments")

                .addEfficiencyUpgrade("Ocarina", songs(8_000), 2) // instruments
                .addUpgradeRequirement("Wind")

                .addEfficiencyUpgrade("Keyboard", songs(150_000), 2.5f) // instruments
                .addGeneratorRequirement("Instruments")

                .addEfficiencyUpgrade("Percussion", songs(900_000), 1.75f) // instruments
                .addGeneratorRequirement("Instruments")

                .addEfficiencyUpgrade("Autotune", songs(8e6), 2f) // autotune
                .addUpgradeRequirement("Voice")

                .addEfficiencyUpgrade("Electrophones", songs(1.5e7), 2) // instruments
                .addGeneratorRequirement("Instruments")

                .addEfficiencyUpgrade("The King of Instruments", songs(4e7), 2) // keyboard
                .addUpgradeRequirement("Keyboard")

                .addEfficiencyUpgrade("Xylophone", songs(9e7), 2) // percussion
                .addUpgradeRequirement("Percussion")

                .addEfficiencyUpgrade("String", songs(2.5e8), 3) // instruments
                .addGeneratorRequirement("Instruments")

                .addEfficiencyUpgrade("Synthesizer", songs(8e8), 3.5f) // electrophones
                .addUpgradeRequirement("Electrophones")

                .addEfficiencyUpgrade("Lyre, Lyre", songs(3e9), 3.5f) // String
                .addUpgradeRequirement("String")

                .addEfficiencyUpgrade("Hurricane Hymn No. 6", songs(7e9), 6) // melody, lyre, clay tablets
                .addUpgradeRequirement("Melody")
                .addUpgradeRequirement("Lyre, Lyre")
                .addUpgradeRequirement("Clay Tablets");


        builder.createGenerator("Modern Innovations", songs(1e10), 1.15f, songs(1e6)) // what's next
                .addUpgradeRequirement("What's Next?") // ???

                .addEfficiencyUpgrade("Phonograph", songs(2e11), 6) // modern
                .addGeneratorRequirement("Modern Innovations")

                .addEfficiencyUpgrade("Mic Check", songs(7e11), 2) // phonograph
                .addUpgradeRequirement("Phonograph")

                .addEfficiencyUpgrade("Listen Up", songs(2.5e12), 3) // mic
                .addUpgradeRequirement("Mic Check")

                .addEfficiencyUpgrade("Radio", songs(1.5e14), 76) // listen
                .addUpgradeRequirement("Listen Up")

                .addEfficiencyUpgrade("Portable Player", songs(2e15), 4) // listen
                .addUpgradeRequirement("Radio")

                .addEfficiencyUpgrade("Digital Age", songs(8e16), 16) // player
                .addUpgradeRequirement("Portable Player")

                .addEfficiencyUpgrade("Streaming", songs(4e17), 6) // digital age
                .addUpgradeRequirement("Digital Age")

                .addEfficiencyUpgrade("Here, There, and Everywhere", songs(1e18), 2) // streaming
                .addUpgradeRequirement("Global Sensations")
                .addUpgradeRequirement("Streaming");

        builder.createGenerator("A Brief History", songs(5e12), 1.15f, songs(3e8)) // what's next
                .addUpgradeRequirement("What's Next?") // ???

                .addEfficiencyUpgrade("Ancient Times", songs(1.2e13), 4) // brief
                .addGeneratorRequirement("A Brief History")

                .addEfficiencyUpgrade("Middle Ages", songs(7e13), 2) // ancient
                .addUpgradeRequirement("Ancient Times")

                .addEfficiencyUpgrade("Classical Clientele", songs(8e14), 3) // middle
                .addUpgradeRequirement("Middle Ages")

                .addEfficiencyUpgrade("A New World", songs(7e15), 6) // classic
                .addUpgradeRequirement("Classical Clientele")

                .addEfficiencyUpgrade("Beyond Borders", songs(3e16), 4) // new world
                .addUpgradeRequirement("A New World")

                .addEfficiencyUpgrade("Global Sensations", songs(1.5e17), 3) // beyond borders
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
//                "Vibrations", // check
//                "Receiving Sound", // check
//                "Processing Sound", // check

//                "Amplitude", // check
//                "Wavelength", // check
//                "Frequency", // check
//                "Harmonics", // check
//                "Color of a Note", // check
//                "Vibrato", // check

//                "Pitch", // check
//                "Rhythm", // check
//                "African Polyrhythm", // check
//                "Notation", // check
//                "Semitones", // check
//                "Arabic Maqam", // check
//                "Chinese Shi'er lü", // check
//                "Chords", // check
//                "Octave", // check
//                "Pentatonic Scale", // check
//                "Melody", // check

//                "Sticks and Rocks", // check
//                "Bone Flute", // check
//                "Write That Down", // check
//                "Clay Tablets", // check

//                "What's Next?", // notes // check
//                "Wind", // check
//                "Voice", // check
//                "Ocarina", // check
//                "Keyboard", // check
//                "Percussion", // check
//                "Autotune", // check
//                "Electrophones", // check
//                "The King of Instruments", // check
//                "Xylophone", // check
//                "String", // check
//                "Synthesizer", // check
//                "Lyre, Lyre", // check
//                "Hurricane Hymn No. 6", // check

//                "Phonograph", // check
//                "Mic Check", // check
//                "Listen Up", // check
//                "Radio", // check
//                "Portable Player", // check
//                "Digital Age", // check
//                "Streaming", // check
//                "Here, There, and Everywhere", // check

//                "Ancient Times", // check
//                "Middle Ages", // check
//                "Classical Clientele", // check
//                "A New World", // check
//                "Beyond Borders", // check
//                "Global Sensations", // check
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
