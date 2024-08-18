import java.util.Random;

public class InputGenerator {
    private int batchSize;
    private int size;
    private double[][] input;

    public InputGenerator(int batchSize, int size) {
        this.batchSize = batchSize;
        this.size = size;
    }

    public double[][] generateInput() {
        double[][] outArr = new double[batchSize][size];
        Random rand = new Random();
        boolean trigger;
        int num;

        for (int i = 0; i < batchSize; i++) {
            trigger = false;
            for (int j = 0; j < size; j++) {
                num = rand.nextInt(3) + 1;
                if (num == 1 && !trigger) {
                    trigger = true;
                } else if (num == 1) {
                    while (num == 1) {
                        num = rand.nextInt(3) + 1;
                    }
                }
                if (j == size - 1 && !trigger) {
                    num = 1;
                }
                outArr[i][j] = num;
            }
        }
        this.input = outArr;
        return outArr;
    }

    public int[] generateTargets(int target) {
        int[] outArr = new int[batchSize];
        boolean trigger;

        for (int i = 0; i < batchSize; i++) {
            trigger = false;
            for (int j = 0; j < size; j++) {
                if (input[i][j] == target) {
                    outArr[i] = j;
                    trigger = true;
                }
            }
            if (!trigger) {
                outArr[i] = 4;
            }
        }
        return outArr;
    }
}
