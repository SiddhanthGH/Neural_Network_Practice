import java.util.Arrays;

public class NeuralNetwork {
    public static void main(String[] args) {
        double[][] inputs = {{1, 2, 3}, {3, 2, 1}, {3,1,3}, {2,2,1}, {2,1,3}, {3,1,2}, {3,3,1}, {3,1,2}, {1,3,2}};
        int[] classTarget = {0, 2, 1, 2, 1, 1, 2, 1, 0};
        double lowestLoss;

        HiddenLayer layer1 = new HiddenLayer(15, inputs);
        HiddenLayer layer2 = new HiddenLayer(15, layer1.forward());

        OutputLayer outputLayer = new OutputLayer(3, layer2.forward());
        outputLayer.setClassTarget(classTarget);
        outputLayer.forward();
        //System.out.println(Arrays.deepToString(outputLayer.forward()));
        //System.out.printf("Loss: %f", outputLayer.calculateLoss());

        lowestLoss = outputLayer.calculateLoss();
        double loss;
        double lossSum = 0;
        double avgLoss;
        int iteration = 1;

        while (true) {
            double delta = 1;
            layer1.changeWeights(delta);
            layer1.changeBiases(delta);
            layer2.changeWeights(delta);
            layer2.changeBiases(delta);
            outputLayer.changeWeights(delta);
            outputLayer.changeBiases(delta);

            layer1.forward();
            layer2.forward();
            outputLayer.forward();

            loss = outputLayer.calculateLoss();

            if (loss > lowestLoss) {
                layer1.revertChanges();
                layer2.revertChanges();
                outputLayer.revertChanges();
            } else {
                lowestLoss = loss;
            }

            lossSum += loss;
            avgLoss = lossSum / iteration;

            if (avgLoss <= 0.01) {
                break;
            } else {
                iteration++;
                System.out.println(loss);
            }
        }

        System.out.printf("Optimized after %d iterations with an average loss of %.3f!\n", iteration, avgLoss);
        System.out.println("Changing input to: {{1, 2, 3}, {3, 2, 1}, {3,1,3}, {2,2,1}, {2,1,3}, {3,1,2}, {3,3,1}, {3,1,2}, {1,3,2}}\n");

        double[][] inputs2 = {{1, 2, 3}, {3, 2, 1}, {3,1,3}, {2,2,1}, {2,1,3}, {3,1,2}, {3,3,1}, {3,1,2}, {1,3,2}};
        layer1.changeInputs(inputs2);
        layer2.changeInputs(layer1.forward());
        int[] classTarget2 = {0, 2, 1, 2, 1, 1, 2, 1, 0};
        outputLayer.setClassTarget(classTarget2);
        outputLayer.changeInputs(layer2.forward());
        System.out.println(Arrays.deepToString(outputLayer.forward()));
        lowestLoss = outputLayer.calculateLoss();
        System.out.println(lowestLoss);
    }
}
