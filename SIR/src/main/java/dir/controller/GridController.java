package dir.controller;

import dir.model.Grid;
import dir.view.CellView;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class GridController {

    @FXML
    private GridPane gridPane;

    private Grid grid;

    @FXML
    public void initialize() {
        // Crear e inicializar el modelo de la cuadrícula
        grid = null;
        initializeAll();
    }

    private void initializeAll() {
        if (grid != null) {
            // Inicializar la vista de la cuadrícula
            initializeGrid();
        } else {
            gridPane.getChildren().clear();
        }
    }

    private void initializeGrid() {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                CellView cellView = new CellView(20, 20, grid.getState(row, col));
                gridPane.add(cellView, col, row);
            }
        }
    }

    public void updateGrid() {
        // Actualizar el modelo de la cuadrícula
        grid.updateGrid();

        // Actualizar la vista de la cuadrícula
        updateGridView();
    }

    private void updateGridView() {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                CellView cellView = (CellView) getNodeFromGridPane(col, row);
                cellView.updateColor(grid.getState(row, col));
            }
        }
    }

    private javafx.scene.Node getNodeFromGridPane(int col, int row) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
        initializeAll();
    }

    public Grid getGrid() {
        return grid;
    }
}