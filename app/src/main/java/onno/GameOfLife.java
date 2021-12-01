package onno;

import java.awt.*;

public class GameOfLife {
    private final int GRID_SIZE = 100;
    private Cell[][] grid;

    public GameOfLife(int[][] input) {
        grid = new Cell[GRID_SIZE][GRID_SIZE];

        // Initializing the cells
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                grid[x][y] = new Cell();
            }
        }

        // Making them alive if necessary
        for (int[] outer : input) {
            int x = outer[0];
            int y = outer[1];
            grid[x][y].setAlive();
        }

        // Adding their neighbors
        for (int x = 1; x < GRID_SIZE - 1; x++) {
            for (int y = 1; y < GRID_SIZE - 1; y++) {
                for (int i = -1; i < 1; i++) {
                    for (int j = -1; j < 1; j++) {
                        grid[x][y].addNeighbor(grid[x + i][y + j]);
                    }
                }
            }
        }
    }

    public void tick() {
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                grid[x][y].tick();
            }
        }
    }

    public void stop() {

    }

    public void start() {

    }

    public void draw(Graphics g) {
        g.
    }
}
