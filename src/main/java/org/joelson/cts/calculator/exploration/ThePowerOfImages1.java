import org.joelson.cts.calculator.model.Generator;

void main() {
    //Generator generator = new Generator("Age of Mammals", 8e15f, 1.12f, 1e12f);
    //Generator generator = new Generator("Symbol", 7500, 1.15f, 50);
    //Generator generator = new Generator("Composition", 2.5e6f, 1.15f, 1000);
    Generator generator = new Generator("Photography", 1.5e11f, 1.15f, 5e7f);
    float generatorProduction = generator.getBaseProduction() * 11;
    System.out.println("generatorProduction: " + generatorProduction);

    float lineProduction = (1 * 2 * 3 * 2.5f) * 38;
    System.out.println("lineProduction: " + lineProduction);

    float symbolProduction = (50 * 2.5f * 2 * 2) * 120;
    System.out.println("symbolProduction: " + symbolProduction);

    float compositionProduction = (2000 * 2 * 2 * 2 * 2 * 2) * 14;
    System.out.println("compositionProduction: " + compositionProduction);

    float renaissenceProduction = (5e5f * 2 * 2 * 2 * 2 * 2 * 4 * 5) * 15;
    System.out.println("renaissenceProduction: " + renaissenceProduction);

    float otherProduction = lineProduction + symbolProduction + compositionProduction + renaissenceProduction;


    float improvementCost = 4e13f;//1.5e16f;

    float prevTime = Float.MAX_VALUE;
    int startValue = 19;
    System.out.println("generatorProduction: " + generatorProduction);
    System.out.println("photographyProduction: " + startValue * generatorProduction);
    System.out.println("total production " + (otherProduction + startValue * generatorProduction));
    for (int l = startValue; l <= startValue + 100; l += 1) {
        float time = 0;
        System.out.println("With " + l);
        for (int d = startValue + 1; d <= l; d += 1) {
            //System.err.println("production: " + ((d-1) * generatorProduction + otherProduction));
            //System.err.println("cost: " + generator.getCost(d - 1));
            float generatorTime = generator.getCost(d - 1) / ((d - 1) * generatorProduction + otherProduction);
            time += generatorTime;
            System.out.println(
                    "generator" + ": " + d + ", generatorTime: " + generatorTime / 60 + " (time " + time / 60 + ")");
        }
        float improvementTime = improvementCost / (l * generatorProduction + otherProduction);
        time += improvementTime;
        System.out.println("improvementTime: " + improvementTime / 60 + " (time " + time / 60 + ")");
        System.out.println();
        if (time > prevTime) {
            System.out.println(
                    "time " + time / 60 + " larger than previous time " + prevTime / 60 + " by " + (time - prevTime));
            System.out.println();
            break;
        }
        prevTime = time;
    }
}
