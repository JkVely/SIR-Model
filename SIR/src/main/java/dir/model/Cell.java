package dir.model;

/**
 * Interface representing a cell in the SIR model.
 * <p>
 * This interface defines the essential methods that a cell in the SIR model
 * must implement. The SIR model is used to simulate the spread of infectious
 * diseases through a population. Each cell can interact with its neighbors
 * and transition between different states.
 * </p>
 * <ul>
 *   <li>{@link #NEIGHBORHOOD_SIZE} - The maximum number of neighbors a cell can have.</li>
 *   <li>{@link #addNeighbor(Cell)} - Adds a neighboring cell.</li>
 *   <li>{@link #getState()} - Gets the current state of the cell.</li>
 *   <li>{@link #getNextState()} - Gets the next state of the cell.</li>
 *   <li>{@link #updateState()} - Updates the current state to the next state.</li>
 *   <li>{@link #updateNextState(double)} - Calculates the next state based on the current state, neighbors, and virus transmission rate, and updates the next state.</li>
 * </ul>
 * 
 * @see State
 * @see SIRCell
 * @since 1.0
 */
public interface Cell {
    /**
     * The maximum number of neighbors a cell can have.
     */
    public final int NEIGHBORHOOD_SIZE = 8;

    /**
     * Adds a neighboring cell.
     * 
     * @param cell the neighboring cell to add
     */
    void addNeighbor(SIRCell cell); 

    /**
     * Gets the current state of the cell.
     * 
     * @return the current state of the cell
     */
    State getState();

    /**
     * Gets the next state of the cell.
     * 
     * @return the next state of the cell
     */
    State getNextState();

    /**
     * Updates the current state to the next state.
     */
    void updateState();

    /**
     * Calculates the next state based on the current state, neighbors, virus transmission rate, and recovery rate, and updates the next state.
     * 
     * @param virusTransmissionRate the transmission rate of the virus
     * @param recoveryRate the recovery rate of the virus
     * @return the next state of the cell
     */
    State updateNextState(double virusTransmissionRate, double recoveryRate);
}