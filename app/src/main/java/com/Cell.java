package com;

import java.util.ArrayList;
import java.util.List;


/**
 * Models a cell: its state, attributes and behaviour.
 */
public class Cell {
    private State state = new State();
    private final List<Cell> neighbors = new ArrayList<>();


    /**
     * Depending on the starting pattern, a {@link Cell} may need to be initialized with the ALIVE state.
     */
    public void setAlive() {
        this.state.current = State.SubState.ALIVE;
        this.state.saved = State.SubState.ALIVE;
    }


    /**
     * @return true, if the cell is alive
     */
    public boolean isAlive() {
        return this.state.saved == State.SubState.ALIVE;
    }


    /**
     * Adds a neighbor {@link Cell} to this {@link Cell}.
     *
     * @param neighbor
     */
    public void addNeighbor(final Cell neighbor) {
        this.neighbors.add(neighbor);
    }


    /**
     * Saves the current state of the {@link Cell}, before it is triggered to transition to its next state.
     */
    public void saveCurrentState() {
        this.state.saved = this.state.current; // Save previous state, so that neighbors can use that to determine their transition
    }


    /**
     * This {@link Cell} calculates and transitions to its next state.
     */
    public void update() {
        int neighborsAlive = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.isAlive()) {
                neighborsAlive++;
            }
        }
        if (neighborsAlive == 3 || (neighborsAlive == 2 && this.state.saved == State.SubState.ALIVE)) {
            this.state.current = State.SubState.ALIVE;
        } else {
            this.state.current = State.SubState.DEAD;
        }
    }


    /**
     * The state of a cell.
     */
    private class State {
        SubState saved = SubState.DEAD;
        SubState current = SubState.DEAD;

        enum SubState {
            ALIVE, DEAD
        }
    }
}