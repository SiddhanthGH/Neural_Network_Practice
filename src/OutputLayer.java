public class OutputLayer extends HiddenLayer {
    private int[] classTarget;
    private double[][] outArr;

    public OutputLayer(int neuronNum, double[][] inputs) {
        super(neuronNum, inputs);
    }

    @Override
    public double[][] forward() {
        double[][] outArr = new double[super.getBatchSize()][super.getNeurons().length];
        double expSum = 0;
        double max;

        for (int i = 0; i < super.getBatchSize(); i++) {
            for (int j = 0; j < super.getNeurons().length; j++) {
                outArr[i][j] = super.getNeurons()[j].out()[i];
            }
        }

        for (int i = 0; i < super.getBatchSize(); i++) {
            //Get maximum and subtract
            max = outArr[i][0];
            expSum = 0;

            for (int j = 1; j < super.getNeurons().length; j++) {
                if (outArr[i][j] >= max) {
                    max = outArr[i][j];
                }
            }

            for (int j = 0; j < super.getNeurons().length; j++) {
                outArr[i][j] = outArr[i][j] - max;
            }

            //Softmax activation
            for (int j = 0; j < super.getNeurons().length; j++) {
                outArr[i][j] = Math.exp(outArr[i][j]);
                expSum += outArr[i][j];
            }

            for (int j = 0; j < super.getNeurons().length; j++) {
                outArr[i][j] = outArr[i][j] / expSum;
            }
        }

        this.outArr = outArr;
        return outArr;
    }

    public void setClassTarget(int[] classTarget) {
        this.classTarget = classTarget;
    }

    public double calculateLoss() {
        double loss = 0.0;
        double[] confidences = new double[super.getBatchSize()];

        for (int i = 0; i < super.getBatchSize(); i++) {
            confidences[i] = outArr[i][classTarget[i]];
            if (confidences[i] <= Math.exp(-7)) {
                confidences[i] = Math.exp(-7);
            } else if (confidences[i] >= 1 - Math.exp(-7)) {
                confidences[i] = 1 - Math.exp(-7);
            }
            confidences[i] = -Math.log(confidences[i]);
        }

        for (int i = 0; i < confidences.length; i++) {
            loss += confidences[i];
        }

        return (loss / confidences.length);
    }

}
