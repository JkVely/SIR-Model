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

/**
 * Controller class for the right panel of the SIR model application.
 * Manages user inputs and interactions for configuring the simulation parameters.
 */
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

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        transmissionRateLabel.textProperty().bind(transmissionRateSlider.valueProperty().asString("%.0f%%"));
        recoveryRateLabel.textProperty().bind(recoveryRateSlider.valueProperty().asString("%.0f%%"));

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> step()));
        timeline.setCycleCount(Timeline.INDEFINITE);

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

    /**
     * Sets the GridController instance.
     *
     * @param gridController the GridController to set
     */
    public void setGridController(GridController gridController) {
        this.gridController = gridController;
    }

    /**
     * Sets the ChartController instance.
     *
     * @param chartController the ChartController to set
     */
    public void setChartController(ChartController chartController) {
        this.chartController = chartController;
    }

    /**
     * Handles the action of stepping through the simulation manually.
     */
    @FXML
    private void handleStepAction() {
        step();
    }

    /**
     * Handles the action of starting the automatic simulation.
     */
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

    /**
     * Handles the action of stopping the automatic simulation.
     */
    @FXML
    private void handleStopAutoAction() {
        timeline.stop();
    }

    /**
     * Performs a single step in the simulation.
     */
    private void step() {
        if (grid.hasInfected()) {
            gridController.updateGrid();
        } else {
            System.out.println("Simulation has ended.");
            gridController.updateGrid();
            System.out.println("Total number of steps: " + grid.getUpdateCounter());
            timeline.stop();
        }
        updateChart();
    }

    /**
     * Handles the action of initializing the grid with user-defined parameters.
     */
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

        grid = new Grid(rows, cols, transmissionRate, recoveryRate);

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

        gridController.setGrid(grid);
        initializeChart();
    }

    /**
     * Initializes the chart with the initial grid data.
     */
    private void initializeChart() {
        chartController.updateChart(grid.getNumSusceptible(), grid.getNumInfected(), grid.getNumRecovered());
    }

    /**
     * Updates the chart with the current grid data.
     */
    private void updateChart() {
        chartController.updateChart(grid.getNumSusceptible(), grid.getNumInfected(), grid.getNumRecovered());
    }
}
