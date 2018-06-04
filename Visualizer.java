import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.chart.ChartFactory;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author Blake Gerard
 * @version 1.0
 *
 *          The Visualizer class utilizes the JFreeChart library to display the
 *          perceptron's classification after each epoch. This class extends
 *          JFrame in order to display the scatter plot.
 * 
 *          JFreeChart: http://www.jfree.org/jfreechart/
 */
public class Visualizer extends JFrame
{
    /**
     * Default serial version ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * The set of points to be classified. The data is represented as red points
     * on the scatter plot
     */
    private double[][] trainingData;

    /**
     * Weight vector for the current epoch.
     */
    private double[] weightVector;

    /**
     * Constructor. Calls assembleData method to collect the data together,
     * creates a scatter plot, and displays the graph in the JFrame.
     * 
     * @param trainingData
     *            The set of training points to be classified.
     * @param weightVector
     *            The weight vector for the current epoch.
     * @param currentEpoch
     *            The current epoch. Used for graph title.
     */
    public Visualizer(double[][] trainingData, double[] weightVector,
            int currentEpoch)
    {
        super("Classification");
        this.trainingData = trainingData;
        this.weightVector = weightVector;

        XYDataset data = assembleData();

        // Create a JFreeChart scatter plot with the data collection returned by
        // assembleData method
        JFreeChart graph = ChartFactory.createScatterPlot(
                "Classification After " + currentEpoch + " Epochs", "X", "Y",
                data);

        // Display the scatter plot in the JFrame
        ChartPanel panel = new ChartPanel(graph);
        setContentPane(panel);
    }

    /**
     * Assembles the training set and the classification points determined by
     * the weight vector into a JFreeChart collection. Two XYSeries contain the
     * training set and classification points, which are then added to the
     * XYSeriesCollection to be display in the constructor.
     * 
     * @return XYDataset Collection of data points in the training set and the
     *         two classifier points determined by the weight vectors.
     */
    public XYDataset assembleData()
    {
        // Collection of all data points to be added to the graph
        XYSeriesCollection dataCollection = new XYSeriesCollection();

        // Series of data points from the dataset
        XYSeries dataSeries = new XYSeries("Points");

        // Series of points describing the weight vector
        XYSeries classifierSeries = new XYSeries("Weight Vector");

        // Add all of the points from the given dataset into the XYSeries
        for (double[] point : trainingData)
        {
            dataSeries.add(point[1], point[2]);
        }

        // Add points corresponding to the appropriate weight vector values to
        // present
        // the classification line
        classifierSeries.add(0, -(weightVector[0] / weightVector[1])); // x
        classifierSeries.add(-(weightVector[0] / weightVector[2]), 0); // y

        // Add the training set points to the collection
        dataCollection.addSeries(dataSeries);

        // Add the classification points to the collection
        dataCollection.addSeries(classifierSeries);

        // Return the collection
        return dataCollection;
    }

}
