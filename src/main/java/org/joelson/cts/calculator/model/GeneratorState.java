package org.joelson.cts.calculator.model;

public record GeneratorState(int count, float efficiency) {

    public static GeneratorState EMPTY = new GeneratorState(0, 1);
}
