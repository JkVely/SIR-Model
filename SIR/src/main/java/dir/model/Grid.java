package dir.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

/**
 * Controller class for managing the grid of cells in the SIR model.
 * <p>
 * This class represents a grid of cells in the SIR model, managing their states
 * and interactions. It provides methods to initialize the grid in different ways
 * and to update the grid state in each simulation step.
 * </p>
 * 
 * @see Cell
 * @see SIRCell
 * @see State
 * @since 1.0
 */
public class Grid {

    private final SIRCell[][] cells;
    private final int rows;
    private final int cols;
    private final double virusTransmissionRate;
    private final double recoveryRate;
    private int updateCounter = 0;
    private boolean hasInfected = false;
    private int numInfected = 0;
    private int numRecovered = 0;
    private int numSusceptible = 0;
    private static final Random random = new Random();

    /**
     * Constructs a Grid with the specified number of rows and columns, and the virus transmission rate.
     * 
     * @param rows the number of rows in the grid
     * @param cols the number of columns in the grid
     * @param virusTransmissionRate the transmission rate of the virus
     * @param recoveryRate the recovery rate of the virus
     */
    public Grid(int rows, int cols, double virusTransmissionRate, double recoveryRate) {
        this.rows = rows;
        this.cols = cols;
        this.virusTransmissionRate = virusTransmissionRate;
        this.recoveryRate = recoveryRate;
        this.cells = new SIRCell[rows][cols];
    }

    /**
     * Initializes the grid with a specified number of infected cells at random
     * positions.
     *
     * @param numInfected_ the number of cells to initialize as infected
     */
    public void initialize(int numInfected_) {
        hasInfected = true;
        numInfected = numInfected_;

        // Create a list of all possible coordinates
        List<int[]> coordinates = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                coordinates.add(new int[]{row, col});
            }
        }

        // Shuffle the list of coordinates
        Collections.shuffle(coordinates, random);

        // Select the first numInfected_ coordinates
        for (int i = 0; i < numInfected_; i++) {
            int[] coord = coordinates.get(i);
            int randRow = coord[0];
            int randCol = coord[1];
            cells[randRow][randCol] = new SIRCell(State.INFECTIOUS);
        }

        initializeGrid();
    }

    /**
     * Initializes the grid with a specified percentage of infected cells at
     * random positions.
     *
     * @param percentInfected the percentage of cells to initialize as infected
     */
    public void initialize(double percentInfected) {
        int numInfected_ = (int) (rows * cols * (percentInfected / 100));
        initialize(numInfected_);
    }

    /**
     * Initializes the grid with the center cell as infected.
     */
    public void initialize() {
        hasInfected = true;
        cells[rows / 2][cols / 2] = new SIRCell(State.INFECTIOUS);
        numInfected = 1;
        initializeGrid();
    }

    /**
     * Initializes the grid, setting all cells to susceptible if they are not
     * already set.
     */
    private void initializeGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (cells[row][col] == null) {
                    cells[row][col] = new SIRCell(State.SUSCEPTIBLE);
                    numSusceptible++;
                } else {
                    cells[row][col] = (SIRCell) (cells[row][col]);
                }
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col].setNeighbors(createNeighbors(row, col));
            }
        }
    }

    /**
     * Sets the neighbors for a cell at the specified position, handling edges
     * and corners.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return a list of neighboring cells
     */
    private List<SIRCell> createNeighbors(int row, int col) {
        SIRCell cell = cells[row][col];
        if (cell == null) {
            return null;
        }
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {

                if (row == 0 && col == 0) { // top left corner
                    if (r == 2 && c == 1) {
                        cell.addNeighbor((SIRCell) cells[row + 1][col]);
                    } else if (r == 1 && c == 2) {
                        cell.addNeighbor(cells[row][col + 1]);
                    } else if (r == 2 && c == 2) {
                        cell.addNeighbor(cells[row + 1][col + 1]);
                    }
                } else if (row == 0 && col < cols - 1) { // top row
                    switch (c) {
                        case 0 -> {
                            if (r == 1) {
                                cell.addNeighbor(cells[row][col - 1]);
                            } else if (r == 2) {
                                cell.addNeighbor(cells[row + 1][col - 1]);
                            }
                        }
                        case 1 -> {
                            if (r == 2) {
                                cell.addNeighbor(cells[row + 1][col]);
                            }
                        }
                        case 2 -> {
                            if (r == 1) {
                                cell.addNeighbor(cells[row][col + 1]);
                            } else if (r == 2) {
                                cell.addNeighbor(cells[row + 1][col + 1]);
                            }
                        }
                    }
                } else if (row == 0 && col == cols - 1) { // top right corner
                    if (r == 1 && c == 0) {
                        cell.addNeighbor(cells[row][col - 1]);
                    } else if (r == 2 && c == 0) {
                        cell.addNeighbor(cells[row + 1][col - 1]);
                    } else if (r == 2 && c == 1) {
                        cell.addNeighbor(cells[row + 1][col]);
                    }
                } else if (row > 0 && row < rows - 1 && col == cols - 1) { // right column
                    if (c == 0) {
                        switch (r) {
                            case 0 ->
                                cell.addNeighbor(cells[row - 1][col - 1]);
                            case 1 ->
                                cell.addNeighbor(cells[row][col - 1]);
                            case 2 ->
                                cell.addNeighbor(cells[row + 1][col - 1]);
                        }
                    } else if (c == 1) {
                        if (r == 0) {
                            cell.addNeighbor(cells[row - 1][col]);
                        } else if (r == 2) {
                            cell.addNeighbor(cells[row + 1][col]);
                        }
                    }
                } else if (row < rows - 1 && col == 0) { // left column
                    if (c == 1) {
                        if (r == 0) {
                            cell.addNeighbor(cells[row - 1][col]);
                        } else if (r == 2) {
                            cell.addNeighbor(cells[row + 1][col]);
                        }
                    } else if (c == 2) {
                        switch (r) {
                            case 0 ->
                                cell.addNeighbor(cells[row - 1][col + 1]);
                            case 1 ->
                                cell.addNeighbor(cells[row][col + 1]);
                            case 2 ->
                                cell.addNeighbor(cells[row + 1][col + 1]);
                        }
                    }
                } else if (row == rows - 1 && col == 0) { // bottom left corner
                    if (c == 1 && r == 0) {
                        cell.addNeighbor(cells[row - 1][col]);
                    } else if (c == 2 && r == 0) {
                        cell.addNeighbor(cells[row - 1][col + 1]);
                    } else if (c == 2 && r == 1) {
                        cell.addNeighbor(cells[row][col + 1]);
                    }
                } else if (row == rows - 1 && col < cols - 1) { // bottom row
                    if (c == 0) {
                        switch (r) {
                            case 0 ->
                                cell.addNeighbor(cells[row - 1][col - 1]);
                            case 1 ->
                                cell.addNeighbor(cells[row][col - 1]);
                        }
                    } else if (c == 1 && r == 0) {
                        cell.addNeighbor(cells[row - 1][col]);
                    } else if (c == 2) {
                        switch (r) {
                            case 0 ->
                                cell.addNeighbor(cells[row - 1][col + 1]);
                            case 1 ->
                                cell.addNeighbor(cells[row][col + 1]);
                        }
                    }
                } else if (row == (rows - 1) && col == (cols - 1)) { // bottom right corner
                    if (c == 0) {
                        switch (r) {
                            case 0 ->
                                cell.addNeighbor(cells[row - 1][col - 1]);
                            case 1 ->
                                cell.addNeighbor(cells[row][col - 1]);
                        }
                    } else if (c == 1 && r == 0) {
                        cell.addNeighbor(cells[row - 1][col]);
                    }
                } else { // middle cells
                    switch (r) {
                        case 0 ->
                            cell.addNeighbor(cells[row - 1][col - 1]);
                        case 1 -> {
                            if (c != 1) {
                                cell.addNeighbor(cells[row][col + 1]);
                            }
                        }
                        case 2 ->
                            cell.addNeighbor(cells[row + 1][col - 1]);
                    }
                }
            }
        }
        return (List<SIRCell>) cell.getNeighbors();
    }

    /**
     * Updates the state of the grid by calculating the next state for each cell and then updating their states.
     */
    public void updateGrid() {
        numInfected = 0;
        numRecovered = 0;
        numSusceptible = 0;
        hasInfected = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].updateState();
                switch (cells[i][j].getState()) {
                    case INFECTIOUS -> {
                        numInfected++;
                    }
                    case RECOVERED -> {
                        numRecovered++;
                    }
                    default -> {
                        numSusceptible++;
                    }
                }

                cells[i][j].updateNextState(virusTransmissionRate, recoveryRate);

                // Check if the next state of any cell is INFECTIOUS
                if (cells[i][j].getNextState() == State.INFECTIOUS) {
                    hasInfected = true;
                }
            }
        }
        updateCounter++;
    }

    /**
     * @return the number of rows in the grid
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return the number of columns in the grid
     */
    public int getCols() {
        return cols;
    }

    /**
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return the state of the cell at the specified position on the grid.
     */
    public State getState(int row, int col) {
        return cells[row][col].getState();
    }

    /**
     * @return the two-dimensional array of cells representing the grid
     */
    public Cell[][] getCells() {
        return cells;
    }

    /**
     * @return the number of times the grid has been updated
     */
    public int getUpdateCounter() {
        return updateCounter;
    }

    /**
     * @return true if the grid has infected cells, false otherwise
     */
    public boolean hasInfected() {
        return hasInfected;
    }

    /**
     * @return the number of infected cells in the grid
     */
    public int getNumInfected() {
        return numInfected;
    }

    /**
     * @return the number of recovered cells in the grid
     */
    public int getNumRecovered() {
        return numRecovered;
    }

    /**
     * @return the number of susceptible cells in the grid
     */
    public int getNumSusceptible() {
        return numSusceptible;
    }
}
