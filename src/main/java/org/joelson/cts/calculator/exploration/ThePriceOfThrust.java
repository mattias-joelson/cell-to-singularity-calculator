import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

private static final Garden GARDEN = new Garden("The Price of Thrust");
private static final GardenState STATE = new GardenState();

private static final String COMMODITY_CURRENCY = "Labor";
private static final String COINS_CURRENCY = "Coinage";
private static final String PAPER_CURRENCY = "Banknotes";
private static final String ELECTRONIC_CURRENCY = "Credit";
private static final String FUTURE_CURRENCY = "Cryptocurrency";

private static final String[] CURRENCIES =
        { COMMODITY_CURRENCY, COINS_CURRENCY, PAPER_CURRENCY, ELECTRONIC_CURRENCY, FUTURE_CURRENCY };

private static Amount commodity(double amount) {
    return new Amount(COMMODITY_CURRENCY, amount);
}

private static Amount coins(double amount) {
    return new Amount(COINS_CURRENCY, amount);
}

private static Amount paper(double amount) {
    return new Amount(PAPER_CURRENCY, amount);
}

private static Amount electronic(double amount) {
    return new Amount(ELECTRONIC_CURRENCY, amount);
}

private static Amount future(double amount) {
    return new Amount(FUTURE_CURRENCY, amount);
}

private void setGeneratorCount(Generator generator, int count) {
    STATE.setGeneratorCount(generator, count);
}

void main() {

    for (String currency : CURRENCIES) {
        GARDEN.addCurrency(currency);
    }

    GardenBuilder builder = new GardenBuilder(GARDEN);

    Generator commodity = builder.createGenerator("Commodity Currency", commodity(25), 1.07f, commodity(1))
            //.addUpgradeRequirement("Specializing")

            .addEfficiencyUpgrade("Debt", commodity(125), 2.5f)
            .addGeneratorRequirement("Commodity Currency")
            //.addUpgradeRequirement("Gifting")

            .addEfficiencyUpgrade("Tally Sticks", commodity(5000), 2)
            .addUpgradeRequirement("Debt")

            .addEfficiencyUpgrade("Bartering", commodity(20_000), 2)
            //.addUpgradeRequirement("Settling")
            .addUpgradeRequirement("Tally Sticks")

            .addEfficiencyUpgrade("Wampum", commodity(250_000), 2.5f)
            //.addUpgradeRequirement("Gifting")
            .addUpgradeRequirement("Bartering")

            .addEfficiencyUpgrade("Recordkeeping", commodity(800_000), 2.5f)
            .addGeneratorRequirement("Commodity Currency")
            .addUpgradeRequirement("Tally Sticks")

            .addEfficiencyUpgrade("Intrinsic Value", commodity(3e6), 2)
            .addGeneratorRequirement("Commodity Currency")
            .addUpgradeRequirement("Bartering")

            .addEfficiencyUpgrade("Precious Metals", commodity(9e6), 2.5f)
            .addGeneratorRequirement("Commodity Currency")
            .addUpgradeRequirement("Intrinsic Value")

            .addEfficiencyUpgrade("Metallurgy", commodity(4e7), 2.5f)
            .addUpgradeRequirement("Precious Metals")

            .addEfficiencyUpgrade("Cowrie Shells", commodity(2.5e8), 3)
            .addUpgradeRequirement("Intrinsic Value")
            //.addUpgradeRequirement("Metallurgy")

            .addEfficiencyUpgrade("Bronze Shells", commodity(7.5e8), 5)
            .addUpgradeRequirement("Cowrie Shells")

            .addEfficiencyUpgrade("Government Mints", commodity(4e10), 3)
            .addUpgradeRequirement("Metallurgy")

            .addEfficiencyUpgrade("Shekels", coins(600), 4)
            .addUpgradeRequirement("Precious Metals")
            .addGeneratorRequirement("Coins")

            .addEfficiencyUpgrade("Rai Stones", coins(1e7), 101)
            .addUpgradeRequirement("Recordkeeping")
            .addUpgradeRequirement("Government Mints")
            .addUpgradeRequirement("Interest")

            .addEfficiencyUpgrade("Quipu", paper(750), 1_001)
            .addUpgradeRequirement("Recordkeeping")
            .addGeneratorRequirement("Paper Money")
            .generator();

    Generator coins = builder.createGenerator("Coins", commodity(1e9), 1.09f, coins(1))
            .addGeneratorRequirement("Commodity Currency")
            .addUpgradeRequirement("Metallurgy")

            .addEfficiencyUpgrade("Lydian Coinage", coins(5_000), 1.75f)
            .addUpgradeRequirement("Bronze Shells")
            .addGeneratorRequirement("Coins")

            .addEfficiencyUpgrade("Chinese Coins", coins(150_000), 2)
            .addUpgradeRequirement("Lydian Coinage")

            .addEfficiencyUpgrade("Interest", coins(400_000), 1.75f)
            .addUpgradeRequirement("Recordkeeping")
            .addGeneratorRequirement("Coins")

            .addEfficiencyUpgrade("Usury", coins(2e6), 2)
            .addUpgradeRequirement("Interest")

            .addEfficiencyUpgrade("Roman Coinage", coins(5e6), 2)
            .addUpgradeRequirement("Lydian Coinage")
            .addUpgradeRequirement("Chinese Coins")

            .addEfficiencyUpgrade("Carolingian Coinage", coins(1e7), 2)
            .addUpgradeRequirement("Roman Coinage")

            .addEfficiencyUpgrade("Negotiable Instruments", coins(2e7), 2.5f)
            .addGeneratorRequirement("Coins")
            .addUpgradeRequirement("Usury")

            .addEfficiencyUpgrade("Banking", coins(8e7), 3)
            .addUpgradeRequirement("Usury")

            .addEfficiencyUpgrade("Iconography", coins(3e8), 4)
            .addGeneratorRequirement("Coins")
            .addUpgradeRequirement("Negotiable Instruments")

            .addEfficiencyUpgrade("Deposit Lending", coins(1e12), 2.5f)
            .addUpgradeRequirement("Banking")
            //.addGeneratorRequirement("Paper Money")

            .addEfficiencyUpgrade("Counterfeiting", coins(5e12), 8)
            .addUpgradeRequirement("Iconography")

            .addEfficiencyUpgrade("Credit", paper(300_000), 11)
            .addUpgradeRequirement("Interest")
            .addGeneratorRequirement("Paper Money")

            .addEfficiencyUpgrade("Piece of Eight", paper(7e6), 101)
            .addUpgradeRequirement("Carolingian Coinage")
            .addGeneratorRequirement("Paper Money")

            .addEfficiencyUpgrade("Taxation", electronic(900), 5001)
            .addUpgradeRequirement("Government Mints")
            .addGeneratorRequirement("Electronic Money")
            .generator();

    Generator paper = builder.createGenerator("Paper Money", coins(1e9), 1.11f, paper(1))
            .addGeneratorRequirement("Coins")
            .addUpgradeRequirement("Negotiable Instruments")

            .addEfficiencyUpgrade("Flying Money", paper(1_500), 2)
            .addUpgradeRequirement("Negotiable Instruments")
            .addGeneratorRequirement("Paper Money")

            .addEfficiencyUpgrade("Central Bank", paper(20_000), 3)
            .addUpgradeRequirement("Banking")
            .addGeneratorRequirement("Paper Money")

            .addEfficiencyUpgrade("Gold Standard", paper(750_000), 4)
            .addUpgradeRequirement("Central Bank")

            .addEfficiencyUpgrade("Fiat Money", paper(3e6), 4)
            .addUpgradeRequirement("Credit")
            .addUpgradeRequirement("Gold Standard")

            .addEfficiencyUpgrade("Stock Market", paper(1e8), 3)
            .addGeneratorRequirement("Paper Money")
            //.addUpgradeRequirement("Fiat Money")

            .addEfficiencyUpgrade("Speculative Bubbles", paper(4e8), 6)
            .addUpgradeRequirement("Stock Market")

            .addEfficiencyUpgrade("Inflation", paper(3e11), 21)
            .addUpgradeRequirement("Deposit Lending")
            .addUpgradeRequirement("Fiat Money")

            .addEfficiencyUpgrade("ATM", electronic(500_000), 51)
            .addUpgradeRequirement("Payment Cards")

            .addEfficiencyUpgrade("Euro", future(2.5e9), 1_001)
            .addUpgradeRequirement("Fiat Money")
            .addGeneratorRequirement("Future Money")
            .generator();

    Generator electronic = builder.createGenerator("Electronic Money", paper(1e9), 1.13f, electronic(1))
// when Future Money bought changes to produce future (cryptocurrency)
//    Generator electronic = builder.createGenerator("Electronic Money", paper(1e9), 1.13f, future(1))
            .addGeneratorRequirement("Paper Money")
            .addUpgradeRequirement("Fiat Money")

            .addEfficiencyUpgrade("Information Security", electronic(10_000), 2)
            .addGeneratorRequirement("Electronic Money")

            .addEfficiencyUpgrade("Payment Cards", electronic(50_000), 2)
            .addGeneratorRequirement("Electronic Money")

            .addEfficiencyUpgrade("Online Banking", electronic(200_000), 5)
            .addGeneratorRequirement("Electronic Money")

            .addEfficiencyUpgrade("e-commerce", electronic(6e6), 6)
            .addUpgradeRequirement("Online Banking")

            .addEfficiencyUpgrade("Wire Transfer", electronic(5e7), 2.5f)
            .addUpgradeRequirement("Information Security")
            .addUpgradeRequirement("Online Banking")

            .addEfficiencyUpgrade("Mobile Payment", electronic(1.5e8), 4)
            .addUpgradeRequirement("Payment Cards")

            .addEfficiencyUpgrade("ETF", electronic(4e8), 3)
            .addUpgradeRequirement("Stock Market")
            .addUpgradeRequirement("e-commerce")

            .addEfficiencyUpgrade("Bank Run", future(2e10), 26)
            .addUpgradeRequirement("Inflation")
            .addGeneratorRequirement("Future Money")

            .addEfficiencyUpgrade("Algorithmic Trading", future(3e11), 6)
            .addUpgradeRequirement("ETF")
            .addGeneratorRequirement("Future Money")

            .generator();

    Generator future = builder.createGenerator("Future Money", electronic(1e9), 1.15f, future(100_000))
// when bought cost changes to future (cryptocurrency)
//    Generator future = builder.createGenerator("Future Money", future(1e9), 1.15f, future(100_000))

            .addGeneratorRequirement("Electronic Money")
            .addUpgradeRequirement("Mobile Payment")

            .addEfficiencyUpgrade("Darwinium Cube", future(1), 3)
            .addUpgradeRequirement("Virtual Currencies")

            .addEfficiencyUpgrade("Blockchain", future(2e9), 3)
            .addGeneratorRequirement("Future Money")

            .addEfficiencyUpgrade("Cryptocurrency", future(1e11), 3)
            .addUpgradeRequirement("Blockchain")

            .addEfficiencyUpgrade("Virtual Currencies", future(7.5e11), 2.5f)
            .addUpgradeRequirement("Mobile Payment")
            .addUpgradeRequirement("Algorithmic Trading")

            .addEfficiencyUpgrade("What's next?", future(9e11), 3)
            .addUpgradeRequirement("Cryptocurrency")
            .addUpgradeRequirement("Virtual Currencies")

            .addEfficiencyUpgrade("Unregulated", future(2e12), 3.5f)
            .addUpgradeRequirement("Cryptocurrency")

            .addEfficiencyUpgrade("One world, One Currency?", future(1e13), 3)
            .addUpgradeRequirement("What's next?")

            .addEfficiencyUpgrade("Decentralized Utopia?", future(4e13), 3)
            .addUpgradeRequirement("What's next?")
            .addUpgradeRequirement("One world, One Currency?")

            .addEfficiencyUpgrade("Post-scarcity Society?", future(1e14), 2)
            .addUpgradeRequirement("What's next?")
            .addUpgradeRequirement("Decentralized Utopia?")
            .generator();

    builder.resolveRequirements();
    STATE.updateGeneratorStates(GARDEN);
//    STATE.setBoost(4);

    setGeneratorCount(future, 0);
    setGeneratorCount(electronic, 0);
    setGeneratorCount(paper, 0);
    setGeneratorCount(coins, 0);
    setGeneratorCount(commodity, 1);

    String[] boughtUpdates = {
//            "Debt",
//            "Tally Sticks",
//            "Bartering",
//            "Wampum",
//            "Recordkeeping",
//            "Intrinsic Value",
//            "Precious Metals",
//            "Metallurgy",
//            "Cowrie Shells",
//            "Bronze Shells",
//            "Government Mints",
//            "Shekels", // coins
//            "Rai Stones", // coins
//            "Quipu", // paper

//            "Lydian Coinage",
//            "Chinese Coins",
//            "Interest",
//            "Usury",
//            "Roman Coinage",
//            "Carolingian Coinage",
//            "Negotiable Instruments",
//            "Banking",
//            "Iconography",
//            "Deposit Lending",
//            "Counterfeiting",
//            "Credit", // paper
//            "Piece of Eight", // paper
//            "Taxation", // credit

//            "Flying Money",
//            "Central Bank",
//            "Gold Standard",
//            "Fiat Money",
//            "Stock Market",
//            "Speculative Bubbles",
//            "Inflation",
//            "ATM", // credit
//            "Euro", // future

//            "Information Security",
//            "Payment Cards",
//            "Online Banking",
//            "e-commerce",
//            "Wire Transfer",
//            "Mobile Payment",
//            "ETF",
//            "Bank Run", // future
//            "Algorithmic Trading", // future

//            "Darwinium Cube",
//            "Blockchain",
//            "Cryptocurrency",
//            "Virtual Currencies",
//            "What's next?",
//            "Unregulated",
//            "One world, One Currency?",
//            "Decentralized Utopia?",
//            "Post-scarcity Society?",
    };
    for (String upgradeName : boughtUpdates) {
        STATE.setUpgradeBought(GARDEN.getUpgrade(upgradeName));
    }
    STATE.updateGeneratorStates(GARDEN);

    List<String> actions = new ArrayList<>();
    ImprovementCalculator.multiCurrencyApproach(GARDEN, STATE, actions);
    actions.forEach(System.out::println);
}
