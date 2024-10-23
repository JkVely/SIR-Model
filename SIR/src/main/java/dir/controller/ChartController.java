package dir.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;


/**
 * The ChartController class is responsible for managing the ScatterChart that displays the SIR model data.
 * It initializes the chart with three data series (susceptible, infected, and recovered), applies styles to the data points,
 * and provides methods to update and clear the chart.
 * 
 * <p>This class uses JavaFX annotations and components to manage the chart and its data.</p>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #initialize()}: Initializes the chart controller by setting up the data series and applying styles.</li>
 *   <li>{@link #applyStylesToSeries()}: Applies styles to the data points and legend nodes of the series.</li>
 *   <li>{@link #updateChart(int, int, int)}: Updates the chart with new data points for susceptible, infected, and recovered series.</li>
 *   <li>{@link #clearChart()}: Clears all data points from the chart and resets the time step.</li>
 * </ul>
 * 
 * <p>Fields:</p>
 * <ul>
 *   <li>{@link #scatterChart}: The ScatterChart component that displays the data.</li>
 *   <li>{@link #susceptibleSeries}: The data series for susceptible individuals.</li>
 *   <li>{@link #infectedSeries}: The data series for infected individuals.</li>
 *   <li>{@link #recoveredSeries}: The data series for recovered individuals.</li>
 *   <li>{@link #timeStep}: The current time step for the data points.</li>
 * </ul>
 */
public class ChartController {

    @FXML
    private ScatterChart<Number, Number> scatterChart;

    private XYChart.Series<Number, Number> susceptibleSeries;
    private XYChart.Series<Number, Number> infectedSeries;
    private XYChart.Series<Number, Number> recoveredSeries;
    private int timeStep = 0;

    /**
     * Initializes the chart controller by setting up the data series and applying styles.
     */
    @FXML
    public void initialize() {
        susceptibleSeries = new XYChart.Series<>();
        susceptibleSeries.setName("Susceptible");

        infectedSeries = new XYChart.Series<>();
        infectedSeries.setName("Infected");

        recoveredSeries = new XYChart.Series<>();
        recoveredSeries.setName("Recovered");

        scatterChart.getData().addAll(susceptibleSeries, infectedSeries, recoveredSeries);

        applyStylesToSeries();
    }

    /**
     * Applies styles to the data points and legend nodes of the series.
     */
    private void applyStylesToSeries() {
        for (XYChart.Data<Number, Number> data : susceptibleSeries.getData()) {
            data.getNode().getStyleClass().add("susceptible-symbol");
        }
        for (XYChart.Data<Number, Number> data : infectedSeries.getData()) {
            data.getNode().getStyleClass().add("infected-symbol");
        }
        for (XYChart.Data<Number, Number> data : recoveredSeries.getData()) {
            data.getNode().getStyleClass().add("recovered-symbol");
        }

        for (XYChart.Series<Number, Number> series : scatterChart.getData()) {
            for (XYChart.Data<Number, Number> data : series.getData()) {
                if (data.getNode() != null) {
                    data.getNode().getStyleClass().add(series.getName().toLowerCase() + "-symbol");
                }
            }
        }
    }

    /**
     * Updates the chart with new data points for susceptible, infected, and recovered series.
     *
     * @param numSusceptible the number of susceptible individuals
     * @param numInfected the number of infected individuals
     * @param numRecovered the number of recovered individuals
     */
    public void updateChart(int numSusceptible, int numInfected, int numRecovered) {
        XYChart.Data<Number, Number> susceptibleData = new XYChart.Data<>(timeStep, numSusceptible);
        XYChart.Data<Number, Number> infectedData = new XYChart.Data<>(timeStep, numInfected);
        XYChart.Data<Number, Number> recoveredData = new XYChart.Data<>(timeStep, numRecovered);

        susceptibleSeries.getData().add(susceptibleData);
        infectedSeries.getData().add(infectedData);
        recoveredSeries.getData().add(recoveredData);

        susceptibleData.getNode().getStyleClass().add("susceptible-symbol");
        infectedData.getNode().getStyleClass().add("infected-symbol");
        recoveredData.getNode().getStyleClass().add("recovered-symbol");

        timeStep++;
    }

    /**
     * Clears all data points from the chart and resets the time step.
     */
    public void clearChart() {
        susceptibleSeries.getData().clear();
        infectedSeries.getData().clear();
        recoveredSeries.getData().clear();
        timeStep = 0;
    }
}