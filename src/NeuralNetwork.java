import java.security.spec.RSAOtherPrimeInfo;

public class NeuralNetwork {
    public static void main(String[] args) {
        double[] inputs = {1, 2, 3};

        HiddenLayer layer1 = new HiddenLayer(200, inputs);
        HiddenLayer layer2 = new HiddenLayer(200, layer1.forward());

        OutputLayer outputLayer = new OutputLayer(2, layer2.forward());

        double[] out = outputLayer.forward();
        double sum = 0;
        for (double v : out) {
            System.out.println(v);
            sum += v;
        }
        System.out.println();
        System.out.println(sum);
    }
}
