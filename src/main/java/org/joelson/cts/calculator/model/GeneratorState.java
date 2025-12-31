package org.joelson.cts.calculator.model;

public record GeneratorState(int count, float efficiency, int speed) {

    public static GeneratorState EMPTY = new GeneratorState(0, 1);
    public static GeneratorState EMPTY_TIMED = new GeneratorState(0, 1, 1);

    public GeneratorState(int count, float efficiency) {
        this(count, efficiency, 0);
    }
}
