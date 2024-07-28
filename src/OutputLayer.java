import java.util.Arrays;

public class OutputLayer {
    private Neuron[] neurons;
    private int batchSize;

    public OutputLayer(int neuronNum, double[][] inputs) {
        //System.out.println(Arrays.deepToString(inputs));
        neurons = new Neuron[neuronNum];
        this.batchSize = inputs.length;
        for (int i = 0; i < neuronNum; i++) {
            neurons[i] = new Neuron(inputs);
        }
    }

    public double[][] forward() {
        double[][] outArr = new double[batchSize][neurons.length];
        double expSum = 0;
        double max;

        for (int i = 0; i < batchSize; i++) {
            for (int j = 0; j < neurons.length; j++) {
                outArr[i][j] = neurons[j].out()[i];
            }
        }

        for (int i = 0; i < batchSize; i++) {
            //Get maximum and subtract
            max = outArr[i][0];
            expSum = 0;

            for (int j = 1; j < neurons.length; j++) {
                if (outArr[i][j] >= max) {
                    max = outArr[i][j];
                }
            }

            for (int j = 0; j < neurons.length; j++) {
                outArr[i][j] = outArr[i][j] - max;
            }

            //Softmax activation
            for (int j = 0; j < neurons.length; j++) {
                outArr[i][j] = Math.exp(outArr[i][j]);
                expSum += outArr[i][j];
            }

            for (int j = 0; j < neurons.length; j++) {
                outArr[i][j] = outArr[i][j] / expSum;
            }
        }

        return outArr;
    }
}
