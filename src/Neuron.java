import java.util.Random;

public class Neuron {
    private int size;
    private double[] weights;
    private double[] inputs;
    private double bias;

    public Neuron(double[] inputs) {
        Random rand = new Random();

        this.inputs = inputs;
        this.size = inputs.length;
        this.weights = new double[size];
        this.bias = rand.nextDouble(2 - -2) + -2;

        for(int i = 0; i < weights.length; i++) {
            weights[i] = rand.nextDouble(1 - -1) + -1;
        }
    }

    public double out() {
        double out = 0;
        for (int i = 0; i < weights.length; i++) {
            out += inputs[i] * weights[i];
        }
        out += bias;
        if (out <= 0) {
            out = 0;
        }
        return out;
    }
}
