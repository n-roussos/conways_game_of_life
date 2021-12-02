package onno;

import java.util.ArrayList;
import java.util.List;


/**
 * Models a cell: its state, attributes and behaviour.
 */
public class Cell {
    private State state = new State();
    private final List<Cell> neighbors = new ArrayList<>();


    public void setAlive() {
        this.state.current = SubState.ALIVE;
        this.state.saved = SubState.ALIVE;
    }


    public boolean isAlive() {
        return this.state.saved == SubState.ALIVE;
    }


    public void addNeighbor(final Cell neighbor) {
        this.neighbors.add(neighbor);
    }


    public void saveCurrentState() {
        this.state.saved = this.state.current; // Save previous state, so that neighbors can use that to determine their transition
    }


    public void update() {
        int neighborsAlive = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.isAlive()) {
                neighborsAlive++;
            }
        }
        if (neighborsAlive == 3 || (neighborsAlive == 2 && this.state.saved == SubState.ALIVE)) {
            this.state.current = SubState.ALIVE;
        } else {
            this.state.current = SubState.DEAD;
        }
    }



    enum SubState {
        ALIVE, DEAD
    }
    
    private class State {
        SubState saved = SubState.DEAD;
        SubState current = SubState.DEAD;
    }
}