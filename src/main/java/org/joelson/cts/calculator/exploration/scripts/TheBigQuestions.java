import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

private static final Garden GARDEN = new Garden("The Big Questions");
private static final GardenState STATE = new GardenState();
private static final String CURRENCY = "Questions";

private static Amount questions(double amount) {
    return new Amount(CURRENCY, amount);
}

private void setGeneratorCount(Generator generator, int count) {
    STATE.setGeneratorCount(generator, count);
}

void main() {

    GARDEN.addCurrency(CURRENCY);
    GardenBuilder builder = new GardenBuilder(GARDEN);

    Generator philosophy = builder.createGenerator("Philosophy", questions(25), 1.15f, questions(1))

            .addEfficiencyUpgrade("What is Right?", questions(150), 2)
            .addGeneratorRequirement("Philosophy")

            .addEfficiencyUpgrade("What is Society?", questions(300_000), 31)
            .addGeneratorRequirement("Philosophy")
            .addGeneratorRequirement("Ethics", 10)

            .addEfficiencyUpgrade("What is True?", questions(2e8), 26)
            .addGeneratorRequirement("Philosophy")
            .addGeneratorRequirement("Political Philosophy", 10)

            .addEfficiencyUpgrade("What is Real?", questions(1.5e12), 2501)
            .addGeneratorRequirement("Philosophy")
            .addGeneratorRequirement("Epistemology", 10)

            .addEfficiencyUpgrade("What am I?", questions(1e18), 500_001)
            .addGeneratorRequirement("Philosophy")
            .addGeneratorRequirement("Metaphysics", 10)

            .addEfficiencyUpgrade("Why?", questions(1.2e25), 5_000_001)
            .addUpgradeRequirement("Code of Law")
            .addUpgradeRequirement("Anarchism")
            .addUpgradeRequirement("Empiricism")
            .addUpgradeRequirement("Skepticism")
            .addUpgradeRequirement("Ontology")
            .addUpgradeRequirement("Solipsism")

            .addEfficiencyUpgrade("The Answer Is...", questions(1.5e30), 25_001)
            .addUpgradeRequirement("Thinking...")
            .generator();

    Generator ethics = builder.createGenerator("Ethics", questions(2_000), 1.15f, questions(12))
            .addUpgradeRequirement("What is Right?")

            .addEfficiencyUpgrade("What is Ethical?", questions(8_000), 2)
            .addGeneratorRequirement("Ethics")

            .addEfficiencyUpgrade("Dharma", questions(15_000), 2)
            .addUpgradeRequirement("What is Ethical?")

            .addEfficiencyUpgrade("What is Moral?", questions(40_000), 2)
            .addGeneratorRequirement("Ethics")
            .addUpgradeRequirement("Dharma")

            .addEfficiencyUpgrade("Moral Skepticism", questions(2e7), 5)
            .addUpgradeRequirement("What is Moral?")

            .addEfficiencyUpgrade("Utilitarianism", questions(3e10), 501)
            .addUpgradeRequirement("Moral Skepticism")

            .addEfficiencyUpgrade("Humanism", questions(1.5e13), 101)
            .addUpgradeRequirement("Moral Skepticism")

            .addEfficiencyUpgrade("Intuitive Ethics", questions(1e16), 501)
            .addUpgradeRequirement("Dharma")

            .addEfficiencyUpgrade("Code of Law", questions(5e17), 51)
            .addUpgradeRequirement("Humanism")

            .addEfficiencyUpgrade("Stoicism", questions(2e28), 1e10f)
            .addUpgradeRequirement("Hedonism")
            .generator();

    Generator politicalPhilosophy = builder.createGenerator("Political Philosophy", questions(150_000), 1.15f,
                    questions(300))
            .addUpgradeRequirement("What is Society?")

            .addEfficiencyUpgrade("Who Should Rule?", questions(1.5e6), 2)
            .addGeneratorRequirement("Political Philosophy")

            .addEfficiencyUpgrade("Autocracy", questions(5e6), 1.5f)
            .addUpgradeRequirement("Who Should Rule?")

            .addEfficiencyUpgrade("What is Power?", questions(5e7), 2)
            .addGeneratorRequirement("Political Philosophy")
            .addUpgradeRequirement("Autocracy")

            .addEfficiencyUpgrade("Divine Right", questions(5e8), 3)
            .addUpgradeRequirement("Autocracy")
            .addUpgradeRequirement("What is Power?")

            .addEfficiencyUpgrade("Machiavellianism", questions(7e10), 251)
            .addUpgradeRequirement("Divine Right")

            .addEfficiencyUpgrade("Human Rights", questions(7e13), 101)
            .addUpgradeRequirement("Machiavellianism")

            .addEfficiencyUpgrade("Individualism", questions(1e15), 9)
            .addUpgradeRequirement("Autocracy")

            .addEfficiencyUpgrade("Right to Revolt", questions(1e17), 151)
            .addUpgradeRequirement("Human Rights")

            .addEfficiencyUpgrade("Collectivism", questions(2.5e18), 51)
            .addUpgradeRequirement("Individualism")
            .addUpgradeRequirement("Right to Revolt")
            .addUpgradeRequirement("Code of Law")
            // more

            .addEfficiencyUpgrade("Anarchism", questions(1e20), 26)
            .addUpgradeRequirement("Collectivism")

            .addEfficiencyUpgrade("Thinking...", questions(5e29), 1e9f)
            .addUpgradeRequirement("What's the Answer?")
            .generator();

    Generator epistemology = builder.createGenerator("Epistemology", questions(3e8), 1.15f, questions(100_000))
            .addUpgradeRequirement("What is True?")

            .addEfficiencyUpgrade("What Do We Know?", questions(8e8), 2)
            .addGeneratorRequirement("Epistemology")

            .addEfficiencyUpgrade("Pramana", questions(2e9), 2)
            .addUpgradeRequirement("What Do We Know?")

            .addEfficiencyUpgrade("How Do We Know?", questions(5e9), 2)
            .addGeneratorRequirement("Epistemology")
            .addUpgradeRequirement("Pramana")

            .addEfficiencyUpgrade("Socratic Method", questions(3e11), 11)
            .addUpgradeRequirement("How Do We Know?")

            .addEfficiencyUpgrade("Rationalism", questions(6e12), 3)
            .addUpgradeRequirement("Pramana")

            .addEfficiencyUpgrade("Empiricism", questions(4e13), 4)
            .addUpgradeRequirement("Rationalism")

            .addEfficiencyUpgrade("Skepticism", questions(2e22), (2.5e8f + 1))
            .addUpgradeRequirement("Socratic Method")

            .addEfficiencyUpgrade("What's the Answer?", questions(2e29), (1.5e6f + 1))
            .addUpgradeRequirement("Transcendence")
            .generator();

    Generator metaphysics = builder.createGenerator("Metaphysics", questions(1e13), 1.15f, questions(8e8))
            .addUpgradeRequirement("What is Real?")

            .addEfficiencyUpgrade("How Did It Start?", questions(1e14), 3)
            .addGeneratorRequirement("Metaphysics")

            .addEfficiencyUpgrade("Cosmology", questions(3e14), 2.5f)
            .addUpgradeRequirement("How Did It Start?")

            .addEfficiencyUpgrade("Why Am I Aware?", questions(2e15), 2.5f)
            .addGeneratorRequirement("Metaphysics")
            .addUpgradeRequirement("Cosmology")

            .addEfficiencyUpgrade("Identity", questions(4e15), 2)
            .addUpgradeRequirement("Why Am I Aware?")

            .addEfficiencyUpgrade("Ship of Theseus", questions(4e16), 7)
            .addUpgradeRequirement("Identity")

            .addEfficiencyUpgrade("Space & Time", questions(2e21), 15_001)
            .addUpgradeRequirement("Cosmology")

            .addEfficiencyUpgrade("Theology", questions(1e23), 51)
            .addUpgradeRequirement("Cosmology")

            .addEfficiencyUpgrade("Mind & Matter", questions(3e23), 2)
            .addUpgradeRequirement("Ship of Theseus")

            .addEfficiencyUpgrade("Ontology", questions(3e24), 11)
            .addUpgradeRequirement("Space & Time")
            .addUpgradeRequirement("Theology")
            .addUpgradeRequirement("Mind & Matter")

            .addEfficiencyUpgrade("Ascetism", questions(3e26), 101)
            .addUpgradeRequirement("What's the Good Life?")

            .addEfficiencyUpgrade("Theism", questions(7e27), 16)
            .addUpgradeRequirement("What's the Point?")
            .generator();

    Generator spirit = builder.createGenerator("Spirit", questions(1.2e18), 1.15f, questions(1e14))
            .addUpgradeRequirement("What am I?")

            .addEfficiencyUpgrade("What Is Spirit?", questions(1.5e19), 2)
            .addGeneratorRequirement("Spirit")

            .addEfficiencyUpgrade("Theory of Forms", questions(3e19), 3)
            .addUpgradeRequirement("What Is Spirit?")

            .addEfficiencyUpgrade("What Is Humanity?", questions(8e19), 2)
            .addGeneratorRequirement("Spirit")
            .addUpgradeRequirement("Theory of Forms")

            .addEfficiencyUpgrade("Sapience", questions(3.5e20), 2)
            .addUpgradeRequirement("What Is Humanity?")

            .addEfficiencyUpgrade("Soul", questions(1e21), 2)
            .addUpgradeRequirement("Theory of Forms")
            .addUpgradeRequirement("Sapience")

            .addEfficiencyUpgrade("State of Nature", questions(5e21), 3.5f)
            .addUpgradeRequirement("Sapience")

            .addEfficiencyUpgrade("Nondualism", questions(1e22), 3)
            .addUpgradeRequirement("Soul")

            .addEfficiencyUpgrade("Fate & Free Will", questions(7e23), 101)
            .addUpgradeRequirement("Soul")
            .addUpgradeRequirement("State of Nature")
            .addUpgradeRequirement("Mind & Matter")

            .addEfficiencyUpgrade("Solipsism", questions(7e24), 2.11f)
            .addUpgradeRequirement("Nondualism")

            .addEfficiencyUpgrade("Transcendence", questions(8e28), 5_001)
            .addUpgradeRequirement("Stoicism")
            .addUpgradeRequirement("Existentialism")
            .generator();

    Generator meaningOfLife = builder.createGenerator("Meaning of Life", questions(2e24), 1.15f, questions(1e20))
            .addUpgradeRequirement("Why?")

            .addEfficiencyUpgrade("What's the Good Life?", questions(3e25), 11)
            .addGeneratorRequirement("Meaning of Life")

            .addEfficiencyUpgrade("What's the Point?", questions(1e26), 2)
            .addGeneratorRequirement("Meaning of Life")

            .addEfficiencyUpgrade("Nihilism", questions(7e26), 1)
            .addUpgradeRequirement("What's the Point?")

            .addEfficiencyUpgrade("Hedonism", questions(1e27), 4)
            .addUpgradeRequirement("Ascetism")

            .addEfficiencyUpgrade("Absurdism", questions(3e27), 2)
            .addUpgradeRequirement("Nihilism")

            .addEfficiencyUpgrade("Existentialism", questions(5e28), 5)
            .addUpgradeRequirement("Absurdism")
            .addUpgradeRequirement("Theism")

            .addEfficiencyUpgrade("42", questions(2.5e30), 1.42f)
            .addUpgradeRequirement("The Answer Is...")

            .addEfficiencyUpgrade("What's the Question?", questions(4e30), 2)
            .addUpgradeRequirement("42")
            .generator();

    builder.resolveRequirements();
    STATE.updateGeneratorStates(GARDEN);
//    STATE.setBoost(4);

    setGeneratorCount(meaningOfLife, 0);
    setGeneratorCount(spirit, 0);
    setGeneratorCount(metaphysics, 0);
    setGeneratorCount(epistemology, 0);
    setGeneratorCount(politicalPhilosophy, 0);
    setGeneratorCount(ethics, 0);
    setGeneratorCount(philosophy, 1);

    String[] boughtUpgrades = {

//            "What is Right?",
//            "What is Society?",
//            "What is True?",
//            "What is Real?",
//            "What am I?",
//            "Why?",
//            "The Answer Is...",

//            "What is Ethical?",
//            "Dharma",
//            "What is Moral?",
//            "Moral Skepticism",
//            "Utilitarianism",
//            "Humanism",
//            "Intuitive Ethics",
//            "Code of Law",
//            "Stoicism",

//            "Who Should Rule?",
//            "Autocracy",
//            "What is Power?",
//            "Divine Right",
//            "Machiavellianism",
//            "Human Rights",
//            "Individualism",
//            "Right to Revolt",
//            "Collectivism",
//            "Anarchism",
//            "Thinking...",

//            "What Do We Know?",
//            "Pramana",
//            "How Do We Know?",
//            "Socratic Method",
//            "Rationalism",
//            "Empiricism",
//            "Skepticism",
//            "What's the Answer?",

//            "How Did It Start?",
//            "Cosmology",
//            "Why Am I Aware?",
//            "Identity",
//            "Ship of Theseus",
//            "Space & Time",
//            "Theology",
//            "Mind & Matter",
//            "Ontology",
//            "Ascetism",
//            "Theism",

//            "What Is Spirit?",
//            "Theory of Forms",
//            "What Is Humanity?",
//            "Sapience",
//            "Soul",
//            "State of Nature",
//            "Nondualism",
//            "Fate & Free Will",
//            "Solipsism",
//            "Transcendence",

//            "What's the Good Life?",
//            "What's the Point?",
//            "Nihilism",
//            "Hedonism",
//            "Absurdism",
//            "Existentialism",
//            "42",
//            "What's the Question?"
    };

    for (String boughtUpgrade : boughtUpgrades) {
        Upgrade upgrade = GARDEN.getUpgrade(boughtUpgrade);
        STATE.setUpgradeBought(upgrade);
    }
    STATE.updateGeneratorStates(GARDEN);

    List<String> actions = new ArrayList<>();
    ImprovementCalculator.singleCurrencyApproach(GARDEN, STATE, actions);
    actions.forEach(System.out::println);
}
