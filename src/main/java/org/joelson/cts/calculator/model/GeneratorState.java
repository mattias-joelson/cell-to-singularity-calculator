package org.joelson.cts.calculator.model;

public record GeneratorState(int count, float efficiency, float speed, boolean automated) {

    public static GeneratorState EMPTY = new GeneratorState(0, 1);
    public static GeneratorState EMPTY_TIMED = new GeneratorState(0, 1, 1, false);

    public GeneratorState(int count, float efficiency) {
        this(count, efficiency, 0, false);
    }

    public GeneratorState(int count, float efficiency, float speed) {
        this(count, efficiency, speed, false);
    }
}
