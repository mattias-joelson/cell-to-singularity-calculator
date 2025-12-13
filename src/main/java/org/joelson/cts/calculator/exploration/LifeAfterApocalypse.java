import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Generator;

static final String CURRENCY = "currency";

void main() {
    //Generator generator = new Generator("Age of Mammals", 8e15f, 1.12f, 1e12f);
    Generator generator = new Generator("Cockroach World?", new Amount(CURRENCY, 5e18f), 1.12f,
            new Amount(CURRENCY, 1e15f));
    float generatorProduction = generator.baseProduction().amount() * 10001 * 2;
    System.out.println("generatorProduction: " + generatorProduction);

    float lucaProduction = (1 * 1.5f * 1.5f * 2 * 16 * 6 * 11 * 12 * 11 * 16 * 11 * 41 * 13 * 51 * 101) * 900;
    System.out.println("lucaProduction: " + lucaProduction);

    float improvementCost = 7e24f + 1.23e24f;

    float prevTime = Float.MAX_VALUE;
    int startValue = 88;
    for (int l = startValue; l <= startValue + 100; l += 1) {
        float time = 0;
        System.out.println("With " + l);
        for (int d = startValue + 1; d <= l; d += 1) {
            //System.err.println("production: " + ((d-1) * generatorProduction + lucaProduction));
            //System.err.println("cost: " + generator.getCost(d - 1));
            float generatorTime = generator.getCost(d - 1).amount() / ((d - 1) * generatorProduction + lucaProduction);
            time += generatorTime;
            System.out.println(
                    "generator" + ": " + d + ", generatorTime: " + generatorTime / 60 + " (time " + time / 60 + ")");
        }
        float improvementTime = improvementCost / (l * generatorProduction + lucaProduction);
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
