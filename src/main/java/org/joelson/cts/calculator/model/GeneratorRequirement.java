package org.joelson.cts.calculator.model;

public record GeneratorRequirement(Generator generator, int count) implements Requirement {

    @Override
    public boolean isFulfilled(GardenState state) {
        return state.getGeneratorState(generator).count() >= count;
    }
}
