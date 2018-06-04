import java.util.Random;
import javax.swing.JFrame;

/**
 * @author Blake Gerard
 * @version 2.0
 * 
 *          The Perceptron class perform simple binary classification. This
 *          class also employs the Visualizer class to display a new
 *          classification graph after each epoch.
 *
 */
public class Perceptron {

	/**
	 * The entire training dataset to be classified
	 */
	private double[][] trainingData;

	/**
	 * The target values corresponding to each point in the training set
	 */
	private double[] targetVector;

	/**
	 * The weight vector
	 */
	private double[] weightVector;

	/**
	 * Visualizer object to display classification graph
	 */
	private Visualizer vis;

	/**
	 * Constructor. Initializes random weights through Guassian distribution.
	 * 
	 * @param trainingData
	 *            The entire training dataset to be classified
	 * @param targetVector
	 *            The target values corresponding to each point in the training set
	 */
	public Perceptron(double[][] trainingData, double[] targetVector) {
		this.trainingData = trainingData;
		this.targetVector = targetVector;
		weightVector = new double[3];

		Random weightGen = new Random();
		for (int i = 0; i < weightVector.length; ++i) {
			weightVector[i] = weightGen.nextGaussian();
		}
	}

	/**
	 * Performs and visualizes binary classification.
	 * 
	 * @param epochs
	 *            specified learning iterations
	 */
	public void classify(int epochs) {
		for (int t = 0; t < epochs; ++t) {
			for (int i = 0; i < trainingData.length; ++i) {
				double output = actualOutput(i);
				weightUpdate(i, output);
			}
			visualize(t);
		}
	}

	/**
	 * Computes the weighted net input signal and sigmoidal activation function
	 * result.
	 * 
	 * @param pointIndex
	 *            Iterates through training dataset for retrieval of individual
	 *            points
	 * @return actual output value
	 */
	private double actualOutput(int pointIndex) {
		double weightedNetInputSignal = 0.0;

		for (int i = 0; i < weightVector.length; ++i) {
			weightedNetInputSignal += weightVector[i] * trainingData[pointIndex][i];
		}

		return 1.0 / (1.0 + (Math.pow(Math.E, weightedNetInputSignal)));
	}

	/**
	 * Performs gradient descent weight update
	 * 
	 * @param pointIndex
	 *            Iterates through the training dataset for retrieval of individual
	 *            points
	 * @param output
	 *            actual output value
	 */
	private void weightUpdate(int pointIndex, double output) {
		double[] nextWeightVector = new double[3];
		double error = output - targetVector[pointIndex];

		for (int i = 0; i < weightVector.length; ++i) {
			nextWeightVector[i] = weightVector[i] + (error * trainingData[pointIndex][i]);
		}
		weightVector = nextWeightVector;
	}

	/**
	 * Visualizes the classification of the current epoch
	 * 
	 * @param t
	 *            the current epoch
	 */
	private void visualize(int t) {
		vis = new Visualizer(trainingData, weightVector, t + 1);
		vis.setSize(700, 700);
		vis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vis.setVisible(true);
	}

}
