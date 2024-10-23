package dir.view;

import dir.model.State;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellView extends Rectangle {

    private static final Color SUSCEPTIBLE_COLOR = Color.web("#ec4d4d");
    private static final Color INFECTIOUS_COLOR = Color.web("#f39c63");
    private static final Color RECOVERED_COLOR = Color.web("#5fe966");

    public CellView(double width, double height, State state) {
        super(width, height);
        updateColor(state);
    }

    public final void updateColor(State state) {
        switch (state) {
            case SUSCEPTIBLE -> setFill(SUSCEPTIBLE_COLOR);
            case INFECTIOUS -> setFill(INFECTIOUS_COLOR);
            case RECOVERED -> setFill(RECOVERED_COLOR);
        }
    }
}