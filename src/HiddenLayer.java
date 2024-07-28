public class HiddenLayer {
    private Neuron[] neurons;
    private int batchSize;

    public HiddenLayer(int neuronNum, double[][] inputs) {
        neurons = new Neuron[neuronNum];
        this.batchSize = inputs.length;
        for (int i = 0; i < neuronNum; i++) {
            neurons[i] = new Neuron(inputs);
        }
    }

    public double[][] forward() {
        double[][] outArr = new double[batchSize][neurons.length];
        for (int i = 0; i < batchSize; i++) {
            for(int j = 0; j < neurons.length; j++) {
                outArr[i][j] = neurons[j].out()[i];
                for(int k = 0; k < neurons.length; k++) {
                    if (outArr[i][k] <= 0) {
                        outArr[i][k] = 0;
                    }
                }
            }
        }
        return outArr;
    }
}
