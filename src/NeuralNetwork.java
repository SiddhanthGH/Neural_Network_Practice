import java.util.Arrays;

public class NeuralNetwork {
    public static void main(String[] args) {
        double[][] inputs = {{1, 2, 3}, {3, 2, 1}};
        int[] classTarget = {0, 1};
        double lowestLoss;

        HiddenLayer layer1 = new HiddenLayer(10, inputs);
        HiddenLayer layer2 = new HiddenLayer(10, layer1.forward());

        OutputLayer outputLayer = new OutputLayer(2, layer2.forward());
        outputLayer.setClassTarget(classTarget);

        System.out.println(Arrays.deepToString(outputLayer.forward()));
        System.out.printf("Loss: %f", outputLayer.calculateLoss());

        lowestLoss = outputLayer.calculateLoss();

        for (int iteration = 0; iteration < 1000; iteration++) {

        }
    }
}
