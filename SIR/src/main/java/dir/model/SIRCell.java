package dir.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implementation of the Cell interface for the SIR model.
 * <p>
 * This class represents a cell in the SIR model, managing its state and
 * interactions with neighboring cells.
 * </p>
 * 
 * @see Cell
 * @see State
 * @since 1.0
 */
public final class SIRCell implements Cell {
    private static final Random random = new Random();
    
    private State currentState;
    private State nextState;
    private final List<SIRCell> neighbors = new ArrayList<>();
    private double virusTransmissionRate, recoveryRate;
    private int infectiousNeighbors = 0;

    /**
     * Constructs a SIRCell with the specified initial state.
     * 
     * @param state the initial state of the cell
     */
    public SIRCell(State state) {
        this.currentState = state;
        this.nextState = updateNextState(virusTransmissionRate, recoveryRate);
    }

    /**
     * Adds a neighboring cell.
     * 
     * @param c the neighboring cell to add
     */
    @Override
    public void addNeighbor(SIRCell c) {
        if (neighbors.size() <= NEIGHBORHOOD_SIZE) {
            neighbors.add(c);
        }
    }

    /**
     * Sets the neighbors of this cell.
     * 
     * @param newNeighbors the new neighbors to set
     */
    public void setNeighbors(List<SIRCell> newNeighbors) {
        List<SIRCell> copiedNeighbors = new ArrayList<>(newNeighbors);
    
        this.neighbors.clear();
        this.neighbors.addAll(copiedNeighbors);
    }

    /**
     * Gets the neighbors of this cell.
     * 
     * @return the list of neighboring cells
     */
    public List<SIRCell> getNeighbors() {
        return neighbors;
    }

    /**
     * Gets the current state of the cell.
     * 
     * @return the current state of the cell
     */
    @Override
    public State getState() {
        return currentState;
    }

    /**
     * Gets the next state of the cell.
     * 
     * @return the next state of the cell
     */
    @Override
    public State getNextState() {
        return nextState;
    }

    /**
     * Updates the current state to the next state.
     */
    @Override
    public void updateState() {
        currentState = nextState;
        nextState = updateNextState(virusTransmissionRate, recoveryRate);
    }

    /**
     * Calculates the next state based on the current state, neighbors, virus transmission rate, and recovery rate.
     * 
     * @param virusTransmissionRate the transmission rate of the virus
     * @param recoveryRate the recovery rate from the virus
     * @return the next state of the cell
     */
    @Override
    public State updateNextState(double virusTransmissionRate, double recoveryRate) {
        this.virusTransmissionRate = virusTransmissionRate;
        this.recoveryRate = recoveryRate;
        this.infectiousNeighbors = 0;
        for (Cell neighbor : getNeighbors()) {
            if (neighbor.getState() == State.INFECTIOUS) {
                infectiousNeighbors++;
            }
        }
        double transmissionProbability = virusTransmissionRate * infectiousNeighbors / neighbors.size();
        double recoveryProbability = recoveryRate;

        if (currentState == State.SUSCEPTIBLE && random.nextDouble() < transmissionProbability) {
            return State.INFECTIOUS;
        } else if (currentState == State.INFECTIOUS && random.nextDouble() < recoveryProbability) {
            return State.RECOVERED;
        }
        return currentState;
    }
}