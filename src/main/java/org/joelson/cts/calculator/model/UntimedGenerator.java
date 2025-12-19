package org.joelson.cts.calculator.model;

public record UntimedGenerator(String name, Amount baseCost, float compoundingCost, Amount baseProduction)
        implements Generator {

}
