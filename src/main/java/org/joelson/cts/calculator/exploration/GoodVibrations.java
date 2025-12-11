import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;

static String NOTES = "Notes";
static String SONGS = "Songs";

private static Amount notes(float amount) {
    return new Amount(NOTES, amount);
}

private static Amount songs(float amount) {
    return new Amount(SONGS, amount);
}

private static Upgrade createNoEffectUpgrade(Garden garden, String name, Amount cost) {
    Upgrade upgrade = new Upgrade(name, cost, true);
    garden.addUpgrade(upgrade);
    return upgrade;
}

private static Upgrade createUpgrade(Garden garden, Generator generator, String name, Amount cost, float efficiency) {
    Upgrade upgrade = new Upgrade(name, cost, true);
    upgrade.addEffect(new UpgradeEffect(generator, efficiency));
    garden.addUpgrade(upgrade);
    return upgrade;
}

void main() {

    Garden goodVibrations = new Garden("Good Vibration");
    goodVibrations.addCurrency(NOTES);
    goodVibrations.addCurrency(SONGS);

    Upgrade invisibleForce = createNoEffectUpgrade(goodVibrations, "Invisible Force", notes(20));

    Generator notesGenerator = new Generator("Notes", notes(25), 1.15f, notes(1)); // invisibleForce
    goodVibrations.addGenerator(notesGenerator);
    createUpgrade(goodVibrations, notesGenerator, "Vibrations", notes(100), 2); // notes
    createUpgrade(goodVibrations, notesGenerator, "Receiving Sound", notes(500), 1.75f); // vibrations
    createUpgrade(goodVibrations, notesGenerator, "Processing Sound", notes(4000), 2.25f); // receiving sound

    Generator soundWaves = new Generator("Sound Waves", notes(15_000), 1.15f, notes(50)); // processing sound
    goodVibrations.addGenerator(soundWaves);
    createUpgrade(goodVibrations, soundWaves, "Amplitude", notes(44_000), 2.5f); // sound waves
    createUpgrade(goodVibrations, soundWaves, "Wavelength", notes(600_000), 2.5f); // amplitude
    createUpgrade(goodVibrations, soundWaves, "Frequency", notes(2e6f), 2.25f); // wavelength
    createUpgrade(goodVibrations, soundWaves, "Harmonics", notes(1e7f), 2.5f); // frequency
    createUpgrade(goodVibrations, soundWaves, "Color of a Note", notes(3e7f), 2.25f); // harmonics
    createUpgrade(goodVibrations, soundWaves, "Vibrato", notes(5e7f), 1.75f); // harmonics

    Generator theory = new Generator("Theory", notes(5e7f), 1.15f, notes(10_000)); // inc // Color of a note
    goodVibrations.addGenerator(theory);
    createUpgrade(goodVibrations, theory, "Pitch", notes(1.5e8f), 2); // theory
    createUpgrade(goodVibrations, theory, "Rhythm", notes(4e8f), 2); // theory
    createUpgrade(goodVibrations, theory, "African Polyrhythm", notes(7.5e8f), 2); // rhythm
    createUpgrade(goodVibrations, theory, "Notation", notes(3e9f), 2.5f); // pitch
    createUpgrade(goodVibrations, theory, "Semitones", notes(6e10f), 2.5f); // notation, early innovation
    createUpgrade(goodVibrations, theory, "Arabic Maqam", notes(2e11f), 2f); // pitch, african polyrhythm
    createUpgrade(goodVibrations, theory, "Chinese Shi'er lü", notes(4.5e11f), 2.5f); // Arabic Maqam
    createUpgrade(goodVibrations, theory, "Chords", notes(9e11f), 2.5f); // semitones
    createUpgrade(goodVibrations, theory, "Octave", notes(8.8e12f), 5.4f); // chords
    createUpgrade(goodVibrations, theory, "Pentatonic Scale", notes(5e13f), 6); // // chinese
    createUpgrade(goodVibrations, theory, "Melody", notes(1e14f), 4); // // octave, pentatonic

    Generator earlyInnovations = new Generator("Early Innovations", notes(2e10f), 1.15f, notes(1e6f));// vibrato
    goodVibrations.addGenerator(earlyInnovations);
    createUpgrade(goodVibrations, earlyInnovations, "Sticks and Rocks", notes(3e10f), 2.25f); // early innovations
    createUpgrade(goodVibrations, earlyInnovations, "Bone Flute", notes(8e11f), 7); // sticks and rocks
    createUpgrade(goodVibrations, earlyInnovations, "Write That Down", notes(2.5e12f), 4); // Bone Flute
    createUpgrade(goodVibrations, earlyInnovations, "Clay Tablets", notes(3e13f), 6); // write that down

    Generator instruments = new Generator("Instruments", notes(1e12f), 1.15f, songs(1));
    goodVibrations.addGenerator(instruments);
    createUpgrade(goodVibrations, instruments, "Wind", songs(100), 2); // instruments
    createUpgrade(goodVibrations, instruments, "Voice", songs(1_500), 2.25f); // instruments
    createUpgrade(goodVibrations, instruments, "Ocarina", songs(8_000), 2); // instruments
    createUpgrade(goodVibrations, instruments, "Keyboard", songs(150_000), 2.5f); // instruments
    createUpgrade(goodVibrations, instruments, "Percussion", songs(900_000), 1.75f); // instruments
    createUpgrade(goodVibrations, instruments, "Autotune", songs(8e6f), 2f); // autotune
    createUpgrade(goodVibrations, instruments, "Electrophones", songs(1.5e7f), 2); // instruments
    createUpgrade(goodVibrations, instruments, "The King of Instruments", songs(4e7f), 2); // keyboard
    createUpgrade(goodVibrations, instruments, "Xylophone", songs(9e7f), 2); // percussion
    createUpgrade(goodVibrations, instruments, "String", songs(2.5e8f), 3); // instruments
    createUpgrade(goodVibrations, instruments, "Synthesizer", songs(8e8f), 3.5f); // electrophones
    createUpgrade(goodVibrations, instruments, "Lyre, Lyre", songs(3e9f), 3.5f); // String
    createUpgrade(goodVibrations, instruments, "Hurricane Hymn No. 6", songs(7e9f), 6); // melody, lyre, clay tablets
    Upgrade whatsNext = new Upgrade("What's Next?", notes(8e14f));
    whatsNext.addEffect(new UpgradeEffect(instruments, 2.5f));
    whatsNext.setBought(true);
    goodVibrations.addUpgrade(whatsNext);


    Generator modernInnovations = new Generator("Modern Innovations", songs(1e10f), 1.15f, songs(1e6f)); // what's next
    goodVibrations.addGenerator(modernInnovations);
    createUpgrade(goodVibrations, modernInnovations, "Phonograph", songs(2e11f), 6); // modern
    createUpgrade(goodVibrations, modernInnovations, "Mic Check", songs(7e11f), 2); // phonograph
    createUpgrade(goodVibrations, modernInnovations, "Listen Up", songs(2.5e12f), 3); // mic
    createUpgrade(goodVibrations, modernInnovations, "Radio", songs(1.5e14f), 76); // listen
    createUpgrade(goodVibrations, modernInnovations, "Portable Player", songs(2e15f), 4).setBought(false); // listen
    createUpgrade(goodVibrations, modernInnovations, "Digital Age", songs(8e16f), 16).setBought(false); // player

    Generator briefHistory = new Generator("A Brief History", songs(5e12f), 1.15f, songs(3e8f)); // what's next
    goodVibrations.addGenerator(briefHistory);
    createUpgrade(goodVibrations, briefHistory, "Ancient Times", songs(1.2e13f), 4); // brief
    createUpgrade(goodVibrations, briefHistory, "Middle Ages", songs(7e13f), 2); // ancient
    createUpgrade(goodVibrations, briefHistory, "Classical Clientele", songs(8e14f), 3).setBought(false); // middle
    createUpgrade(goodVibrations, briefHistory, "A New World", songs(7e15f), 6).setBought(false); // classic

    briefHistory.setCount(12);
    modernInnovations.setCount(58);

    earlyInnovations.setCount(74);
    theory.setCount(123);

    instruments.setCount(55);

    soundWaves.setCount(100);
    notesGenerator.setCount(100);

    goodVibrations.updateEfficiency();

    ImprovementCalculator.calculateImprovement(goodVibrations);
    //System.out.println("==========================================");
    //ImprovementCalculator.calculateImprovementNew(goodVibrations);
}
