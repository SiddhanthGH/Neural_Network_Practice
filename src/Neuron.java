import java.util.Random;

public class Neuron {
    private int size;
    private double[][] weights;
    private double[][] oldWeights;
    private double[][] inputs;
    private double[] bias;
    private double[] oldBiases;
    private int batchSize;
    private final Random rand = new Random();

    public Neuron(double[][] inputs) {
        this.inputs = inputs;
        this.size = inputs[0].length;
        this.batchSize = inputs.length;
        //System.out.println(batchSize);
        this.weights = new double[batchSize][size];
        this.bias = new double[batchSize];

        for (int i = 0; i < batchSize; i++) {
            this.bias[i] = rand.nextDouble(2 - -2) + -2;
        }

        for(int i = 0; i < batchSize; i++) {
            for(int j = 0; j < size; j++) {
                weights[i][j] = rand.nextDouble(1 - -1) + -1;
            }
        }

        weights = transpose(weights, batchSize, size);

    }

    public double[] out() {
        double[] out = new double[batchSize];
        for (int i = 0; i < batchSize; i++) {
            for(int j = 0; j < size; j++) {
                out[i] += inputs[i][j] * weights[j][i];
            }
            out[i] += bias[i];
        }
        return out;
    }

    public double[][] transpose(double[][] arr, int batchSize, int size) {
        double[][] temp = new double[size][batchSize];
        for (int i = 0; i < batchSize; i++) {
            for (int j = 0; j < size; j++) {
                temp[j][i] = arr[i][j];
            }
        }
        return temp;
    }

    public void changeWeights(double delta) {
        oldWeights = weights;
        for(int i = 0; i < batchSize; i++) {
            for(int j = 0; j < size; j++) {
                weights[i][j] += delta * rand.nextDouble(1 - -1) + -1;
            }
        }
    }

    public void changeBiases(double delta) {
        oldBiases = bias;
        for (int i = 0; i < batchSize; i++) {
            this.bias[i] = delta * rand.nextDouble(2 - -2) + -2;
        }
    }

    public void revert() {
        weights = oldWeights;
        bias = oldBiases;
    }

}
