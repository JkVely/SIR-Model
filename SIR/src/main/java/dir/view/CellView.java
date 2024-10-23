package dir.view;

import dir.model.State;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The CellView class represents a visual cell in the SIR model simulation.
 * It extends the Rectangle class and provides functionality to update its color
 * based on the state of the cell (SUSCEPTIBLE, INFECTIOUS, or RECOVERED).
 */
public class CellView extends Rectangle {

    /**
     * Constructs a CellView with the specified width, height, and initial state.
     *
     * @param width  the width of the cell
     * @param height the height of the cell
     * @param state  the initial state of the cell (SUSCEPTIBLE, INFECTIOUS, or RECOVERED)
     */
    public CellView(double width, double height, State state) {
        super(width, height);
        updateColor(state);
    }

    /**
     * Updates the color of the cell based on its state.
     *
     * @param state the current state of the cell (SUSCEPTIBLE, INFECTIOUS, or RECOVERED)
     */
    public final void updateColor(State state) {
        switch (state) {
            case SUSCEPTIBLE -> setFill(SUSCEPTIBLE_COLOR);
            case INFECTIOUS -> setFill(INFECTIOUS_COLOR);
            case RECOVERED -> setFill(RECOVERED_COLOR);
        }
    }
}