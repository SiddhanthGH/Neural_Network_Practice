import java.util.Arrays;

public class NeuralNetwork {
    public static void main(String[] args) {
        InputGenerator ig = new InputGenerator(18, 4);

        double[][] inputs = ig.generateInput();//{{1, 2, 3}, {3, 2, 1}, {3,1,3}, {2,2,1}, {2,1,3}, {3,1,2}, {3,3,1}, {3,1,2}, {1,3,2}};
        int[] classTarget = ig.generateTargets(1);//{0, 2, 1, 2, 1, 1, 2, 1, 0};

        //System.out.println(Arrays.deepToString(inputs));
        //System.out.println();
        //System.out.println(Arrays.toString(classTarget));

        double lowestLoss;

        HiddenLayer layer1 = new HiddenLayer(12, inputs);
        HiddenLayer layer2 = new HiddenLayer(12, layer1.forward());

        OutputLayer outputLayer = new OutputLayer(4, layer2.forward());
        outputLayer.setClassTarget(classTarget);
        outputLayer.forward();

        lowestLoss = outputLayer.calculateLoss();
        double loss;
        double lossSum = 0;
        double avgLoss;
        int iteration = 1;
        double delta = 1;

        while (true) {
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

            if (iteration % 1000 == 0) {
                //layer1.changeInputs(ig.generateInput());
                //layer2.changeInputs(layer1.forward());
                //outputLayer.setClassTarget(ig.generateTargets(1));
                //outputLayer.changeInputs(layer2.forward());
                System.out.println((iteration/100000.0) * 100);
            }

            if (iteration == 100000) {
                System.out.println(Arrays.deepToString(inputs));
                System.out.println(Arrays.deepToString(outputLayer.forward()));
                break;
            } else {
                iteration++;
            }
        }

        System.out.printf("Optimized after %d iterations with an average loss of %.3f!\n", iteration, avgLoss);
        //System.out.println("Changing input to: {{1, 2, 3}, {3, 2, 1}, {3,1,3}, {2,2,1}, {2,1,3}, {3,1,2}, {3,3,1}, {3,1,2}, {1,3,2}}\n");

        double[][] inputs2 = ig.generateInput();;
        layer1.changeInputs(inputs2);
        layer2.changeInputs(layer1.forward());
        int[] classTarget2 = ig.generateTargets(1);
        outputLayer.setClassTarget(classTarget2);
        outputLayer.changeInputs(layer2.forward());
        System.out.println(Arrays.deepToString(inputs2));
        System.out.println(Arrays.deepToString(outputLayer.forward()));
        lowestLoss = outputLayer.calculateLoss();
        System.out.println(lowestLoss);
    }
}
