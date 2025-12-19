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
        return generator.getBaseProduction().multiplyBy(state.efficiency());
    }

    public static GeneratorImprovement create(Generator generator, GardenState state) {
        return new GeneratorImprovement(generator, state.getGeneratorState(generator));
    }
}
