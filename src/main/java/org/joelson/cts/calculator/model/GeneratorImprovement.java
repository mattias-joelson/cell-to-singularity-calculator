package org.joelson.cts.calculator.model;

public record GeneratorImprovement(Generator generator, GeneratorState state) implements Improvement {

    @Override
    public String getName() {
        return generator.getName();
    }

    @Override
    public Amount getCost() {
        return generator.getCost(state.count());
    }

    @Override
    public Amount getIncrease() {
        Amount production = generator.getBaseProduction().multiplyBy(state.efficiency());
        if (generator().isTimed()) {
            if (state.automated()) {
                return production.divideBy(generator.getBaseChargeTime() / state.speed());
            } else {
                return production.multiplyBy(0);
            }
        } else {
            return production;
        }
    }

    public static GeneratorImprovement create(Generator generator, GardenState state) {
        return new GeneratorImprovement(generator, state.getGeneratorState(generator));
    }
}
