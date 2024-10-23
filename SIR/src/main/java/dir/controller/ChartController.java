package dir.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class ChartController {

    @FXML
    private ScatterChart<Number, Number> scatterChart;

    private XYChart.Series<Number, Number> susceptibleSeries;
    private XYChart.Series<Number, Number> infectedSeries;
    private XYChart.Series<Number, Number> recoveredSeries;
    private int timeStep = 0;

    @FXML
    public void initialize() {
        // Inicializar las series del gráfico
        susceptibleSeries = new XYChart.Series<>();
        susceptibleSeries.setName("Susceptible");

        infectedSeries = new XYChart.Series<>();
        infectedSeries.setName("Infected");

        recoveredSeries = new XYChart.Series<>();
        recoveredSeries.setName("Recovered");

        // Añadir las series al gráfico
        scatterChart.getData().addAll(susceptibleSeries, infectedSeries, recoveredSeries);

        // Aplicar estilos a las series
        applyStylesToSeries();
    }

    private void applyStylesToSeries() {
        // Aplicar estilos a los puntos de datos individuales en las series
        for (XYChart.Data<Number, Number> data : susceptibleSeries.getData()) {
            data.getNode().getStyleClass().add("susceptible-symbol");
        }
        for (XYChart.Data<Number, Number> data : infectedSeries.getData()) {
            data.getNode().getStyleClass().add("infected-symbol");
        }
        for (XYChart.Data<Number, Number> data : recoveredSeries.getData()) {
            data.getNode().getStyleClass().add("recovered-symbol");
        }

        // Aplicar estilos a los nodos de la leyenda
        for (XYChart.Series<Number, Number> series : scatterChart.getData()) {
            for (XYChart.Data<Number, Number> data : series.getData()) {
                if (data.getNode() != null) {
                    data.getNode().getStyleClass().add(series.getName().toLowerCase() + "-symbol");
                }
            }
        }
    }

    public void updateChart(int numSusceptible, int numInfected, int numRecovered) {
        XYChart.Data<Number, Number> susceptibleData = new XYChart.Data<>(timeStep, numSusceptible);
        XYChart.Data<Number, Number> infectedData = new XYChart.Data<>(timeStep, numInfected);
        XYChart.Data<Number, Number> recoveredData = new XYChart.Data<>(timeStep, numRecovered);

        susceptibleSeries.getData().add(susceptibleData);
        infectedSeries.getData().add(infectedData);
        recoveredSeries.getData().add(recoveredData);

        // Aplicar estilos a los puntos
        susceptibleData.getNode().getStyleClass().add("susceptible-symbol");
        infectedData.getNode().getStyleClass().add("infected-symbol");
        recoveredData.getNode().getStyleClass().add("recovered-symbol");

        timeStep++;
    }

    public void clearChart() {
        susceptibleSeries.getData().clear();
        infectedSeries.getData().clear();
        recoveredSeries.getData().clear();
        timeStep = 0;
    }
}