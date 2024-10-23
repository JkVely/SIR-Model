package dir.model;

/**
 * Enumeration representing the possible states of a cell in the SIR model.
 * <p>
 * The SIR model is used to simulate the spread of infectious diseases through
 * a population. Each state represents a different stage in the disease
 * progression.
 * </p>
 * <ul>
 *   <li>{@link #SUSCEPTIBLE} - The cell is susceptible to infection.</li>
 *   <li>{@link #INFECTIOUS} - The cell is infectious and can spread the disease.</li>
 *   <li>{@link #RECOVERED} - The cell has recovered from the disease and is no longer infectious.</li>
 *   <li>{@link #DEAD} - The cell has died from the disease.</li>
 * </ul>
 * <p>
 * This enumeration is used in the {@link Cell} interface and its implementations
 * to manage the state transitions of cells in the simulation.
 * </p>
 * 
 * @see Cell
 * @see SIRCell
 * @see Grid
 * @see SimulationApp
 * @see CellView
 * 
 * @since 1.0
 */
public enum State {
    SUSCEPTIBLE,
    INFECTIOUS,
    RECOVERED
}
