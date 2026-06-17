package ex2.speedylogis;

import java.util.Random;

public class SpeedyLogis {
    public static void main(String[] args) {
        double[] testDistances = new double[1000000];
        Random rand = new Random();
        for (int i = 0; i < testDistances.length; i++) {
            testDistances[i] = 1 + rand.nextDouble() * 4;
        }

        RouteCostCalculator calculator = new RouteCostCalculator();
        calculator.calculateRouteCostV2(testDistances,300000);
    }
}
