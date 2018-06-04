import java.util.Random;
import javax.swing.JFrame;

public class Perceptron
{
    private double[][] trainingData;

    private double[] targetVector;

    private double[] weightVector;

    private Visualizer vis;

    public Perceptron(double[][] trainingData, double[] targetVector)
    {
        this.trainingData = trainingData;
        this.targetVector = targetVector;
        weightVector = new double[3];

        Random weightGen = new Random();
        for (int i = 0; i < weightVector.length; ++i)
        {
            weightVector[i] = weightGen.nextGaussian();
        }
    }

    public void classify(int epochs)
    {
        for (int t = 0; t < epochs; ++t)
        {
            for (int i = 0; i < trainingData.length; ++i)
            {
                double output = actualOutput(i);
                weightUpdate(i, output);
            }
            visualize(t);
        }
    }

    private double actualOutput(int pointIndex)
    {
        double weightedNetInputSignal = 0.0;

        for (int i = 0; i < weightVector.length; ++i)
        {
            weightedNetInputSignal += weightVector[i] * trainingData[pointIndex][i];
        }

        return 1.0 / (1.0 + (Math.pow(Math.E, weightedNetInputSignal)));
    }

    private void weightUpdate(int pointIndex, double output)
    {
        double[] nextWeightVector = new double[3];
        double error = output - targetVector[pointIndex];

        for (int i = 0; i < weightVector.length; ++i)
        {
            nextWeightVector[i] = weightVector[i] + (error * trainingData[pointIndex][i]);
        }
        weightVector = nextWeightVector;
    }

    private void visualize(int t)
    {
        vis = new Visualizer(trainingData, weightVector, t + 1);
        vis.setSize(700, 700);
        vis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vis.setVisible(true);
    }

}
