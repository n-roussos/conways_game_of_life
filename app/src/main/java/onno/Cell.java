package onno;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private State savedState = State.DEAD;
    private State currentState = State.DEAD;
    private List<Cell> neighbors = new ArrayList<>();


    public void setAlive() {
        this.currentState = State.ALIVE;
        this.savedState = State.ALIVE;
    }


    public boolean isAlive() {
        return this.savedState == State.ALIVE;
    }


    public void addNeighbor(final Cell neighbor) {
        this.neighbors.add(neighbor);
    }


    public void saveCurrentState() {
        this.savedState = this.currentState; // Save previous state, so that neighbors can use that to determine their transition
    }


    public void tick() {
        int neighborsAlive = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.isAlive()) {
                neighborsAlive++;
            }
        }
        if (neighborsAlive == 3 || (neighborsAlive == 2 && this.savedState == State.ALIVE)) {
            this.currentState = State.ALIVE;
        } else {
            this.currentState = State.DEAD;
        }
    }

    enum State {
        ALIVE, DEAD
    }
}