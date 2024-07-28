import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;

public class NeuralNetwork {
    public static void main(String[] args) {
        double[][] inputs = {{1, 2, 3}};
        //System.out.println(inputs.length);
        //System.out.println(inputs[0].length);

        HiddenLayer layer1 = new HiddenLayer(10, inputs);
        HiddenLayer layer2 = new HiddenLayer(10, layer1.forward());

        OutputLayer outputLayer = new OutputLayer(2, layer2.forward());

        System.out.println(Arrays.deepToString(outputLayer.forward()));
    }
}
