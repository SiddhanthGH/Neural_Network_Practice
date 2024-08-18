import java.awt.image.SinglePixelPackedSampleModel;

public class HiddenLayer {
    private final Neuron[] neurons;
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
                if (outArr[i][j] < 0) {
                    outArr[i][j] = 0;
                }
            }
        }
        return outArr;
    }

    public double[][] firstForward() {
        double[][] outArr = new double[batchSize][neurons.length];
        for (int i = 0; i < batchSize; i++) {
            for(int j = 0; j < neurons.length; j++) {
                outArr[i][j] = neurons[j].firstOut()[i];
                for(int k = 0; k < neurons.length; k++) {
                    if (outArr[i][k] < 0) {
                        outArr[i][k] = 0;
                    }
                }
            }
        }
        return outArr;
    }

    public void changeWeights(double delta) {
        for (int i = 0; i < neurons.length; i++) {
            neurons[i].changeWeights(delta);
        }
    }

    public void changeBiases(double delta) {
        for (int i = 0; i < neurons.length; i++) {
            neurons[i].changeBiases(delta);
        }
    }

    public void revertChanges() {
        for (int i = 0; i < neurons.length; i++) {
            neurons[i].revert();
        }
    }

    public Neuron[] getNeurons() {
        return neurons;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void changeInputs(double[][] inputs) {
        this.batchSize = inputs.length;
        for (int i = 0; i < neurons.length; i++) {
            neurons[i].changeInputs(inputs);
        }
    }

}
