/**
 * @author Blake Gerard
 * @version 2.0
 *
 *          The Driver class houses the main method and two example data
 *          clusters for testing classification.
 */
public class Driver {
	public static void main(String[] args) {
		// Dataset one
		double[] m0 = { 1, 0.1, 0.3 };
		double[] m1 = { 1, 0.2, 0.2 };
		double[] m2 = { 1, 0.3, 0.4 };
		double[] m3 = { 1, 0.6, 0.8 };
		double[] m4 = { 1, 0.8, 0.6 };
		double[] m5 = { 1, 0.9, 0.9 };
		double[][] data1 = { m0, m1, m2, m3, m4, m5 };

		// Dataset two
		double[] d0 = { 1, 0.1, 0.8 };
		double[] d1 = { 1, 0.3, 0.7 };
		double[] d2 = { 1, 0.2, 0.5 };
		double[] d3 = { 1, 0.6, 0.1 };
		double[] d4 = { 1, 0.8, 0.3 };
		double[] d5 = { 1, 0.9, 0.2 };
		double[][] data2 = { d0, d1, d2, d3, d4, d5 };

		// Target values
		double[] targetValues = { 0.0, 0.0, 0.0, 1.0, 1.0, 1.0 };

		// Epochs
		int epochs = 20;

		Perceptron p = new Perceptron(data2, targetValues);
		p.classify(epochs);
	}

}
