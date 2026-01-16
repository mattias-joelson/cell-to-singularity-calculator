package org.joelson.cts.calculator.model;

public record GeneratorState(int count, float efficiency, float speed, boolean automated) {

    public static GeneratorState EMPTY = withEfficiency(0, 1);

    public static GeneratorState withEfficiency(int count, float efficiency) {
        return new GeneratorState(count, efficiency, 0, false);
    }
}
