import java.util.Arrays;

public class OutputLayer {
    private Neuron[] neurons;

    public OutputLayer(int neuronNum, double[] inputs) {
        neurons = new Neuron[neuronNum];
        for (int i = 0; i < neuronNum; i++) {
            neurons[i] = new Neuron(inputs);
        }
    }

    public double[] forward() {
        double[] outArr = new double[neurons.length];
        double expSum = 0;
        double max;

        //System.out.println();

        for (int i = 0; i < neurons.length; i++) {
            outArr[i] = neurons[i].out();
            //System.out.println(outArr[i]);
        }

        //Get maximum and subtract
        max = outArr[0];

        for (int i = 1; i < neurons.length; i++) {
            if (outArr[i] >= max) {
                max = outArr[i];
            }
        }

        //System.out.println(max);
        //System.out.println();

        for (int i = 0; i < neurons.length; i++) {
            outArr[i] = outArr[i] - max;
        }

        //Softmax activation
        for (int i = 0; i < neurons.length; i++) {
            expSum += Math.exp(outArr[i]);
        }

        for (int i = 0; i < neurons.length; i++) {
            outArr[i] = Math.exp(outArr[i]) / expSum;
        }

        return outArr;
    }
}
