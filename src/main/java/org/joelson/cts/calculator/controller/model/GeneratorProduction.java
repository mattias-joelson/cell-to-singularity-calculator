package org.joelson.cts.calculator.controller.model;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorState;
import org.joelson.cts.calculator.util.DurationToolkit;

public class GeneratorProduction {

    private final Production that;
    private final Generator generator;
    private final GeneratorState state;
    private final Amount productionAmount;

    private GeneratorProduction(Production that, Generator generator, GeneratorState state, double production) {
        this.that = that;
        this.generator = generator;
        this.state = state;
        this.productionAmount = new Amount(generator.getBaseProduction().currency(), production);
    }

    public String getName() {
        return generator.getName();
    }

    public int getCount() {
        return state.count();
    }

    public String getNext() {
        return generator.getCost(state.count()).asString();
    }

    public String getNextDuration() {
        Amount cost = generator.getCost(state.count());
        Amount totProd = that.getTotalProduction(cost.currency());
        double duration = cost.amount() / totProd.amount();
        return DurationToolkit.durationString(duration);
    }

    public String getEach() {
        if (generator.isTimed()) {
            return generator.getBaseProduction().multiplyBy(state.efficiency()).multiplyBy(state.speed())
                    .divideBy(generator.getBaseChargeTime()).asString();
        } else {
            return generator.getBaseProduction().multiplyBy(state.efficiency()).asString();
        }
    }

    public Amount getProductionAmount() {
        return productionAmount;
    }

    public String getTotal() {
        return productionAmount.asString();
    }

    public String getRatio() {
        Amount totProd = that.getTotalProduction(productionAmount.currency());
        return String.format("%.3f %%", 100 * productionAmount.amount() / totProd.amount());
    }

    public String getIncrease() {
        if (generator.isTimed()) {
            float cycleTime = generator.getBaseChargeTime() / state.speed();
            return String.format("%.7f", (generator.getBaseProduction().amount() * state.efficiency())
                    / (cycleTime * generator.getCost(state.count()).amount()));
        } else {
            return String.format("%.7f",
                    (generator.getBaseProduction().amount() * state.efficiency()) / generator.getCost(state.count())
                            .amount());
        }
    }

    public static GeneratorProduction calculate(Production that, Generator generator, GeneratorState state) {
        int count = state.count();
        double baseProduction = generator.getBaseProduction().amount();
        float efficiency = state.efficiency();
        double production = baseProduction * efficiency * count;
        if (generator.isTimed()) {
            float cycleTime = generator.getBaseChargeTime() / (float) state.speed();
            return new GeneratorProduction(that, generator, state, production / cycleTime);
        } else {
            return new GeneratorProduction(that, generator, state, production);
        }
    }
}
