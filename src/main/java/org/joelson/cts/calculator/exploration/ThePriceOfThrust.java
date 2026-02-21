package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorState;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class ThePriceOfThrust {

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

    private void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("The Price of Thrust");
        for (String currency : CURRENCIES) {
            garden.addCurrency(currency);
        }

        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Commodity Currency", commodity(25), 1.07f, commodity(1))
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
                .addGeneratorRequirement("Paper Money");

        builder.createGenerator("Coins", commodity(1e9), 1.09f, coins(1))
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
                .addGeneratorRequirement("Electronic Money");

        builder.createGenerator("Paper Money", coins(1e9), 1.11f, paper(1))
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
                .addGeneratorRequirement("Future Money");

        builder.createGenerator("Electronic Money", paper(1e9), 1.13f, electronic(1))
// when Future Money bought changes to produce future (cryptocurrency)
//        builder.createGenerator("Electronic Money", paper(1e9), 1.13f, future(1))
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
                .addGeneratorRequirement("Future Money");

        builder.createGenerator("Future Money", electronic(1e9), 1.15f, future(100_000))
// when bought cost changes to future (cryptocurrency)
//        builder.createGenerator("Future Money", future(1e9), 1.15f, future(100_000))

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
                .addUpgradeRequirement("Decentralized Utopia?");

        builder.resolveRequirements();

        return garden;
    }

    public static void alterGarden(Garden garden, GardenState state) {
        if (!garden.getName().equals("The Price of Thrust")) {
            throw new IllegalArgumentException("Invalid garden type: " + garden.getName());
        }
        Generator futureMoney = garden.getGenerator("Future Money");
        GeneratorState generatorState = state.getGeneratorState(futureMoney);
        if (generatorState.count() > 0 && futureMoney.getBaseCost().currency().equals(ELECTRONIC_CURRENCY)) {
            Generator electronicMoney = garden.getGenerator("Electronic Money");
            Generator newElectronicMoney = new Generator(electronicMoney.getName(), electronicMoney.getBaseCost(),
                    electronicMoney.getCompoundingCost(), future(electronicMoney.getBaseProduction().amount()));

            swapGenerators(garden, electronicMoney, newElectronicMoney);
            Generator newFutureMoney = new Generator(futureMoney.getName(), future(futureMoney.getBaseCost().amount()),
                    futureMoney.getCompoundingCost(), futureMoney.getBaseProduction());
            swapGenerators(garden, futureMoney, newFutureMoney);
        }
    }

    private static void swapGenerators(Garden garden, Generator oldGenerator, Generator newGenerator) {
        List<Generator> generators = garden.getGenerators();
        for (int i = 0; i < generators.size(); i += 1) {
            if (generators.get(i).equals(oldGenerator)) {
                generators.set(i, newGenerator);
                for (Upgrade upgrade : garden.getUpgrades()) {
                    List<UpgradeEffect> effects = upgrade.getEffects();
                    for (UpgradeEffect effect : effects) {
                        if (effect.generator().equals(oldGenerator)) {
                            effects.remove(effect);
                            effects.add(new UpgradeEffect(newGenerator, effect.efficiency(), effect.speed(),
                                    effect.automated()));
                            break;
                        }
                    }
                }
                return;
            }
        }
        throw new IllegalStateException("No old generator found: " + oldGenerator.getName());
    }

    void main() {
        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//    state.setBoost(4);

//        Garden.generateGeneratorUpgradesNames(garden);

        setGeneratorCount(garden, state, "Future Money", 0);
        setGeneratorCount(garden, state, "Electronic Money", 0);
        setGeneratorCount(garden, state, "Paper Money", 0);
        setGeneratorCount(garden, state, "Coins", 0);
        setGeneratorCount(garden, state, "Commodity Currency", 1);

        String[] boughtUpgrades = {
//                "Debt",
//                "Tally Sticks",
//                "Bartering",
//                "Wampum",
//                "Recordkeeping",
//                "Intrinsic Value",
//                "Precious Metals",
//                "Metallurgy",
//                "Cowrie Shells",
//                "Bronze Shells",
//                "Government Mints",
//                "Shekels", // coinage
//                "Rai Stones", // coinage
//                "Quipu", // banknotes

//                "Lydian Coinage",
//                "Chinese Coins",
//                "Interest",
//                "Usury",
//                "Roman Coinage",
//                "Carolingian Coinage",
//                "Negotiable Instruments",
//                "Banking",
//                "Iconography",
//                "Deposit Lending",
//                "Counterfeiting",
//                "Credit", // banknotes
//                "Piece of Eight", // banknotes
//                "Taxation", // credit

//                "Flying Money",
//                "Central Bank",
//                "Gold Standard",
//                "Fiat Money",
//                "Stock Market",
//                "Speculative Bubbles",
//                "Inflation",
//                "ATM", // credit
//                "Euro", // cryptocurrency

//                "Information Security",
//                "Payment Cards",
//                "Online Banking",
//                "e-commerce",
//                "Wire Transfer",
//                "Mobile Payment",
//                "ETF",
//                "Bank Run", // cryptocurrency
//                "Algorithmic Trading", // cryptocurrency

//                "Darwinium Cube",
//                "Blockchain",
//                "Cryptocurrency",
//                "Virtual Currencies",
//                "What's next?",
//                "Unregulated",
//                "One world, One Currency?",
//                "Decentralized Utopia?",
//                "Post-scarcity Society?",
        };
        for (String upgradeName : boughtUpgrades) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);

//        state.verifyAllUpgradesBought(garden);

        List<String> actions = new ArrayList<>();
        ImprovementCalculator.multiCurrencyApproach(garden, state, actions);
        actions.forEach(System.out::println);
    }
}