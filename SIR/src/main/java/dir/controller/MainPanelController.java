package dir.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * MainPanelController is responsible for initializing and managing the main panel of the application.
 * It loads various FXML components and sets up their controllers.
 */
public class MainPanelController {

    @FXML
    private ScrollPane scrollGrid;

    @FXML
    private Pane rightPanel;

    @FXML
    private Pane downPanel;

    private ChartController chartController;

    /**
     * Initializes the main panel by loading FXML files and setting up their controllers.
     */
    @FXML
    public void initialize() {
        try {
            FXMLLoader gridLoader = new FXMLLoader(getClass().getResource("/dir/gridView.fxml"));
            GridPane gridContent = gridLoader.load();
            GridController gridController = gridLoader.getController();
            scrollGrid.setContent(gridContent);

            FXMLLoader rightPanelLoader = new FXMLLoader(getClass().getResource("/dir/rightPanel.fxml"));
            Pane rightPanelContent = rightPanelLoader.load();
            RightPanelController rightPanelController = rightPanelLoader.getController();
            rightPanelController.setGridController(gridController);
            rightPanel.getChildren().add(rightPanelContent);

            FXMLLoader chartLoader = new FXMLLoader(getClass().getResource("/dir/chart.fxml"));
            Pane chartContent = chartLoader.load();
            chartController = chartLoader.getController();
            downPanel.getChildren().add(chartContent);
            rightPanelController.setChartController(chartController);

        } catch (IOException e) {
            System.out.println("Error loading FXML files: " + e.getMessage());
        }
    }
}
