package dir.controller;

import dir.model.Grid;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class RightPanelController {

    @FXML
    private Slider transmissionRateSlider;

    @FXML
    private Slider recoveryRateSlider;

    @FXML
    private Label transmissionRateLabel;

    @FXML
    private Label recoveryRateLabel;

    @FXML
    private TextField rowsField;

    @FXML
    private TextField colsField;

    @FXML
    private RadioButton centerInfectionRadio;

    @FXML
    private RadioButton percentageInfectionRadio;

    @FXML
    private RadioButton numberInfectionRadio;

    @FXML
    private TextField percentageField;

    @FXML
    private TextField numberField;

    private Grid grid;
    private Timeline timeline;
    private GridController gridController;
    private ChartController chartController;

    @FXML
    public void initialize() {
        // Bind the labels to the slider values
        transmissionRateLabel.textProperty().bind(transmissionRateSlider.valueProperty().asString("%.0f%%"));
        recoveryRateLabel.textProperty().bind(recoveryRateSlider.valueProperty().asString("%.0f%%"));

        // Initialize the timeline for automatic animation
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> step()));
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Set up listeners for radio buttons
        centerInfectionRadio.setOnAction(e -> {
            percentageField.setDisable(true);
            numberField.setDisable(true);
        });

        percentageInfectionRadio.setOnAction(e -> {
            percentageField.setDisable(false);
            numberField.setDisable(true);
        });

        numberInfectionRadio.setOnAction(e -> {
            percentageField.setDisable(true);
            numberField.setDisable(false);
        });
    }

    public void setGridController(GridController gridController) {
        this.gridController = gridController;
    }

    public void setChartController(ChartController chartController) {
        this.chartController = chartController;
    }

    @FXML
    private void handleStepAction() {
        step();
    }

    @FXML
    private void handleStartAutoAction() {
        if (grid.hasInfected()) {
            timeline.play();
        } else {
            System.out.println("Simulation has ended.");
            gridController.updateGrid();
            System.out.println("Total number of steps: " + grid.getUpdateCounter());
            timeline.stop();
        }
    }

    @FXML
    private void handleStopAutoAction() {
        timeline.stop();
    }

    private void step() {
        // Update the grid model
        if (grid.hasInfected()) {
            gridController.updateGrid();
        } else {
            System.out.println("Simulation has ended.");
            gridController.updateGrid();
            System.out.println("Total number of steps: " + grid.getUpdateCounter());
            timeline.stop();
        }

        // Update the chart
        updateChart();
    }

    @FXML
    private void handleInitializeGrid() {
        grid = null;
        chartController.clearChart();
        gridController.initialize();

        double transmissionRate = transmissionRateSlider.getValue() / 100.0;
        double recoveryRate = recoveryRateSlider.getValue() / 100.0;
        int rows = 10;
        int cols = 10;
        try {
            rows = Integer.parseInt(rowsField.getText());
            cols = Integer.parseInt(colsField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid grid size. Using default 10x10.");
        }

        // Create and initialize the grid model
        grid = new Grid(rows, cols, transmissionRate, recoveryRate);

        // Initialize the grid based on the selected infection type
        if (centerInfectionRadio.isSelected()) {
            grid.initialize();
        } else if (percentageInfectionRadio.isSelected()) {
            try {
                double percentage = Double.parseDouble(percentageField.getText());
                grid.initialize(percentage);
            } catch (NumberFormatException e) {
                System.out.println("Invalid percentage. Using default center infection.");
                grid.initialize();
            }
        } else if (numberInfectionRadio.isSelected()) {
            try {
                int numInfected = Integer.parseInt(numberField.getText());
                grid.initialize(numInfected);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number of infected cells. Using default center infection.");
                grid.initialize();
            }
        } else {
            System.out.println("Invalid infection type. Using default center infection.");
            grid.initialize();
        }

        // Set the grid in the GridController
        gridController.setGrid(grid);

        // Initialize the chart with initial grid data
        initializeChart();
    }

    private void initializeChart() {
        chartController.updateChart(grid.getNumSusceptible(), grid.getNumInfected(), grid.getNumRecovered());
    }

    private void updateChart() {
        chartController.updateChart(grid.getNumSusceptible(), grid.getNumInfected(), grid.getNumRecovered());
    }
}
