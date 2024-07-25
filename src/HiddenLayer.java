public class HiddenLayer {
    private Neuron[] neurons;

    public HiddenLayer(int neuronNum, double[] inputs) {
        neurons = new Neuron[neuronNum];
        for (int i = 0; i < neuronNum; i++) {
            neurons[i] = new Neuron(inputs);
        }
    }

    public double[] forward() {
        double[] outArr = new double[neurons.length];
        for (int i = 0; i < neurons.length; i++) {
            outArr[i] = neurons[i].out();
            if (outArr[i] <= 0) {
                outArr[i] = 0;
            }
        }
        return outArr;
    }
}
