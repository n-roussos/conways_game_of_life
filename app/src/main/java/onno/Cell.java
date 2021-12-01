package onno;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private State previousState = State.DEAD;
    private State currentState = State.DEAD;
    private List<Cell> neighbors = new ArrayList<>();


    public Cell() {
    }


    public void setAlive() {
        this.currentState = State.ALIVE;
        this.previousState = State.ALIVE;
    }


    public void addNeighbor(final Cell neighbor) {
        this.neighbors.add(neighbor);
    }

    public void tick() {
        this.previousState = this.currentState; // Save previous state, so that neighbors can use that to determine their transition
        int neighborsAlive = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.isAlive()) {
                neighborsAlive++;
            }
        }
        if (neighborsAlive == 3 || (neighborsAlive == 2 && this.previousState == State.ALIVE)) {
            this.currentState = State.ALIVE;
        } else {
            this.currentState = State.DEAD;
        }
    }

    public boolean isAlive() {
        return this.previousState.equals(State.ALIVE);
    }
}


enum State {
    ALIVE, DEAD
}