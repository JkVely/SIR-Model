package dir.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainPanelController {

    @FXML
    private ScrollPane scrollGrid;

    @FXML
    private Pane rightPanel;

    @FXML
    private Pane downPanel;

    private ChartController chartController;

    @FXML
    public void initialize() {
        try {
            // Cargar el archivo FXML de GridView y obtener su controlador
            FXMLLoader gridLoader = new FXMLLoader(getClass().getResource("/dir/gridView.fxml"));
            GridPane gridContent = gridLoader.load();
            GridController gridController = gridLoader.getController();

            // Añadir el contenido de GridView al ScrollPane
            scrollGrid.setContent(gridContent);

            // Cargar el archivo FXML de RightPanel y obtener su controlador
            FXMLLoader rightPanelLoader = new FXMLLoader(getClass().getResource("/dir/rightPanel.fxml"));
            Pane rightPanelContent = rightPanelLoader.load();
            RightPanelController rightPanelController = rightPanelLoader.getController();

            // Establecer el GridController en el RightPanelController
            rightPanelController.setGridController(gridController);

            // Añadir el contenido de RightPanel al Pane
            rightPanel.getChildren().add(rightPanelContent);

            // Cargar el archivo FXML de Chart y obtener su controlador
            FXMLLoader chartLoader = new FXMLLoader(getClass().getResource("/dir/chart.fxml"));
            Pane chartContent = chartLoader.load();
            chartController = chartLoader.getController();

            // Añadir el contenido de Chart al Pane
            downPanel.getChildren().add(chartContent);

            // Establecer el ChartController en el RightPanelController
            rightPanelController.setChartController(chartController);

        } catch (IOException e) {
            System.out.println("Error loading FXML files: " + e.getMessage());
        }
    }
}