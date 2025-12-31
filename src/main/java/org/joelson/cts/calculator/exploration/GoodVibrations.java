import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;

static String NOTES = "Notes";
static String SONGS = "Songs";

private static Amount notes(double amount) {
    return new Amount(NOTES, amount);
}

private static Amount songs(double amount) {
    return new Amount(SONGS, amount);
}

private static Upgrade createNoEffectUpgrade(Garden garden, GardenState state, String name, Amount cost) {
    Upgrade upgrade = new Upgrade(name, cost);
    garden.addUpgrade(upgrade);
    state.setUpgradeBought(upgrade);
    return upgrade;
}

private static Upgrade createUpgrade(
        Garden garden, GardenState state, Generator generator, String name, Amount cost, float efficiency) {
    return createUpgrade(garden, state, generator, name, cost, efficiency, true);
}

private static Upgrade createUpgrade(
        Garden garden, GardenState state, Generator generator, String name, Amount cost, float efficiency,
        boolean bought) {
    Upgrade upgrade = new Upgrade(name, cost);
    upgrade.addEffect(new UpgradeEffect(generator, efficiency));
    garden.addUpgrade(upgrade);
    state.setUpgradeBought(upgrade, bought);
    return upgrade;
}

void main() {

    Garden goodVibrations = new Garden("Good Vibration");
    goodVibrations.addCurrency(NOTES);
    goodVibrations.addCurrency(SONGS);
    GardenState state = new GardenState();

    Upgrade invisibleForce = createNoEffectUpgrade(goodVibrations, state, "Invisible Force", notes(20));

    Generator notesGenerator = new Generator("Notes", notes(25), 1.15f, notes(1)); // invisibleForce
    goodVibrations.addGenerator(notesGenerator);
    createUpgrade(goodVibrations, state, notesGenerator, "Vibrations", notes(100), 2); // notes
    createUpgrade(goodVibrations, state, notesGenerator, "Receiving Sound", notes(500), 1.75f); // vibrations
    createUpgrade(goodVibrations, state, notesGenerator, "Processing Sound", notes(4000), 2.25f); // receiving sound

    Generator soundWaves = new Generator("Sound Waves", notes(15_000), 1.15f, notes(50)); // processing sound
    goodVibrations.addGenerator(soundWaves);
    createUpgrade(goodVibrations, state, soundWaves, "Amplitude", notes(44_000), 2.5f); // sound waves
    createUpgrade(goodVibrations, state, soundWaves, "Wavelength", notes(600_000), 2.5f); // amplitude
    createUpgrade(goodVibrations, state, soundWaves, "Frequency", notes(2e6), 2.25f); // wavelength
    createUpgrade(goodVibrations, state, soundWaves, "Harmonics", notes(1e7), 2.5f); // frequency
    createUpgrade(goodVibrations, state, soundWaves, "Color of a Note", notes(3e7), 2.25f); // harmonics
    createUpgrade(goodVibrations, state, soundWaves, "Vibrato", notes(5e7), 1.75f); // harmonics

    Generator theory = new Generator("Theory", notes(5e7), 1.15f, notes(10_000)); // inc // Color of a note
    goodVibrations.addGenerator(theory);
    createUpgrade(goodVibrations, state, theory, "Pitch", notes(1.5e8), 2); // theory
    createUpgrade(goodVibrations, state, theory, "Rhythm", notes(4e8), 2); // theory
    createUpgrade(goodVibrations, state, theory, "African Polyrhythm", notes(7.5e8), 2); // rhythm
    createUpgrade(goodVibrations, state, theory, "Notation", notes(3e9), 2.5f); // pitch
    createUpgrade(goodVibrations, state, theory, "Semitones", notes(6e10), 2.5f); // notation, early innovation
    createUpgrade(goodVibrations, state, theory, "Arabic Maqam", notes(2e11), 2f); // pitch, african polyrhythm
    createUpgrade(goodVibrations, state, theory, "Chinese Shi'er lü", notes(4.5e11), 2.5f); // Arabic Maqam
    createUpgrade(goodVibrations, state, theory, "Chords", notes(9e11), 2.5f); // semitones
    createUpgrade(goodVibrations, state, theory, "Octave", notes(8.8e12), 5.4f); // chords
    createUpgrade(goodVibrations, state, theory, "Pentatonic Scale", notes(5e13), 6); // // chinese
    createUpgrade(goodVibrations, state, theory, "Melody", notes(1e14), 4); // // octave, pentatonic

    Generator earlyInnovations = new Generator("Early Innovations", notes(2e10), 1.15f, notes(1e6));// vibrato
    goodVibrations.addGenerator(earlyInnovations);
    createUpgrade(goodVibrations, state, earlyInnovations, "Sticks and Rocks", notes(3e10), 2.25f); // early innovations
    createUpgrade(goodVibrations, state, earlyInnovations, "Bone Flute", notes(8e11), 7); // sticks and rocks
    createUpgrade(goodVibrations, state, earlyInnovations, "Write That Down", notes(2.5e12), 4); // Bone Flute
    createUpgrade(goodVibrations, state, earlyInnovations, "Clay Tablets", notes(3e13), 6); // write that down

    Generator instruments = new Generator("Instruments", notes(1e12), 1.15f, songs(1));
    goodVibrations.addGenerator(instruments);
    createUpgrade(goodVibrations, state, instruments, "Wind", songs(100), 2); // instruments
    createUpgrade(goodVibrations, state, instruments, "Voice", songs(1_500), 2.25f); // instruments
    createUpgrade(goodVibrations, state, instruments, "Ocarina", songs(8_000), 2); // instruments
    createUpgrade(goodVibrations, state, instruments, "Keyboard", songs(150_000), 2.5f); // instruments
    createUpgrade(goodVibrations, state, instruments, "Percussion", songs(900_000), 1.75f); // instruments
    createUpgrade(goodVibrations, state, instruments, "Autotune", songs(8e6), 2f); // autotune
    createUpgrade(goodVibrations, state, instruments, "Electrophones", songs(1.5e7), 2); // instruments
    createUpgrade(goodVibrations, state, instruments, "The King of Instruments", songs(4e7), 2); // keyboard
    createUpgrade(goodVibrations, state, instruments, "Xylophone", songs(9e7), 2); // percussion
    createUpgrade(goodVibrations, state, instruments, "String", songs(2.5e8), 3); // instruments
    createUpgrade(goodVibrations, state, instruments, "Synthesizer", songs(8e8), 3.5f); // electrophones
    createUpgrade(goodVibrations, state, instruments, "Lyre, Lyre", songs(3e9), 3.5f); // String
    createUpgrade(goodVibrations, state, instruments, "Hurricane Hymn No. 6", songs(7e9), 6); // melody, lyre, clay tablets
    Upgrade whatsNext = new Upgrade("What's Next?", notes(8e14));
    whatsNext.addEffect(new UpgradeEffect(instruments, 2.5f));
    state.setUpgradeBought(whatsNext);
    goodVibrations.addUpgrade(whatsNext);

    Generator modernInnovations = new Generator("Modern Innovations", songs(1e10), 1.15f, songs(1e6)); // what's next
    goodVibrations.addGenerator(modernInnovations);
    createUpgrade(goodVibrations, state, modernInnovations, "Phonograph", songs(2e11), 6); // modern
    createUpgrade(goodVibrations, state, modernInnovations, "Mic Check", songs(7e11), 2); // phonograph
    createUpgrade(goodVibrations, state, modernInnovations, "Listen Up", songs(2.5e12), 3); // mic
    createUpgrade(goodVibrations, state, modernInnovations, "Radio", songs(1.5e14), 76); // listen
    createUpgrade(goodVibrations, state, modernInnovations, "Portable Player", songs(2e15), 4); // listen
    createUpgrade(goodVibrations, state, modernInnovations, "Digital Age", songs(8e16), 16); // player
    createUpgrade(goodVibrations, state, modernInnovations, "Streaming", songs(4e17), 6); // digital age
    createUpgrade(goodVibrations, state, modernInnovations, "Here, There, and Everywhere", songs(1e18), 2, false); // streaming

    Generator briefHistory = new Generator("A Brief History", songs(5e12), 1.15f, songs(3e8)); // what's next
    goodVibrations.addGenerator(briefHistory);
    createUpgrade(goodVibrations, state, briefHistory, "Ancient Times", songs(1.2e13), 4); // brief
    createUpgrade(goodVibrations, state, briefHistory, "Middle Ages", songs(7e13), 2); // ancient
    createUpgrade(goodVibrations, state, briefHistory, "Classical Clientele", songs(8e14), 3); // middle
    createUpgrade(goodVibrations, state, briefHistory, "A New World", songs(7e15), 6); // classic
    createUpgrade(goodVibrations, state, briefHistory, "Beyond Borders", songs(3e16), 4); // new world
    createUpgrade(goodVibrations, state, briefHistory, "Global Sensations", songs(1.5e17), 3); // beyond borders

    state.updateGeneratorStates(goodVibrations);
    state.setBoost(2);

    state.setGeneratorCount(briefHistory, 53);
    state.setGeneratorCount(modernInnovations, 102);

    state.setGeneratorCount(earlyInnovations, 76);
    state.setGeneratorCount(theory, 125);

    state.setGeneratorCount(instruments, 56);

    state.setGeneratorCount(soundWaves, 100);
    state.setGeneratorCount(notesGenerator, 100);

    ImprovementCalculator.calculateImprovement(goodVibrations, state);
    //System.out.println("==========================================");
    //ImprovementCalculator.calculateImprovementNew(goodVibrations, state);
}
