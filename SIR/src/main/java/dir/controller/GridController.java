package dir.controller;

import dir.model.Grid;
import dir.view.CellView;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

/**
 * Controller class for managing the grid view and its interactions.
 */
public class GridController {

    @FXML
    private GridPane gridPane;

    private Grid grid;

    /**
     * Initializes the controller and the grid model.
     */
    @FXML
    public void initialize() {
        grid = null;
        initializeAll();
    }

    /**
     * Initializes the grid view based on the grid model.
     */
    private void initializeAll() {
        if (grid != null) {
            initializeGrid();
        } else {
            gridPane.getChildren().clear();
        }
    }

    /**
     * Populates the grid view with cell views based on the grid model.
     */
    private void initializeGrid() {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                CellView cellView = new CellView(20, 20, grid.getState(row, col));
                gridPane.add(cellView, col, row);
            }
        }
    }

    /**
     * Updates the grid model and refreshes the grid view.
     */
    public void updateGrid() {
        grid.updateGrid();
        updateGridView();
    }

    /**
     * Refreshes the grid view based on the updated grid model.
     */
    private void updateGridView() {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                CellView cellView = (CellView) getNodeFromGridPane(col, row);
                cellView.updateColor(grid.getState(row, col));
            }
        }
    }

    /**
     * Retrieves a node from the grid pane based on its column and row indices.
     *
     * @param col the column index of the node
     * @param row the row index of the node
     * @return the node at the specified column and row, or null if not found
     */
    private javafx.scene.Node getNodeFromGridPane(int col, int row) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    /**
     * Sets the grid model and initializes the grid view.
     *
     * @param grid the grid model to set
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
        initializeAll();
    }

    /**
     * Gets the current grid model.
     *
     * @return the current grid model
     */
    public Grid getGrid() {
        return grid;
    }
}