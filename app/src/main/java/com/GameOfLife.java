package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;


/**
 * Produces the instance of a game.
 */
public class GameOfLife {
    private static Logger LOGGER = LoggerFactory.getLogger(GameOfLife.class);
    private final int GRID_SIZE_IN_CELLS = 500;
    private final int CELL_SIZE = 20;
    private Cell[][] grid;
    private GameState state = GameState.INACTIVE;


    /**
     * Initializes a Game of Life, creating all grid's {@link Cell}s and bringing them to life depending on the input pattern.
     *
     * @param input
     */
    public GameOfLife(final int[][] input) {
        grid = new Cell[GRID_SIZE_IN_CELLS][GRID_SIZE_IN_CELLS];

        // Initializing the cells
        for (int x = 0; x < GRID_SIZE_IN_CELLS; x++) {
            for (int y = 0; y < GRID_SIZE_IN_CELLS; y++) {
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
        for (int x = 0; x < GRID_SIZE_IN_CELLS; x++) {
            for (int y = 0; y < GRID_SIZE_IN_CELLS; y++) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if ((i == 0 && j == 0)
                                || y + j < 0
                                || x + i >= GRID_SIZE_IN_CELLS
                                || y + j >= GRID_SIZE_IN_CELLS
                                || x + i < 0) {
                            continue;
                        }
                        grid[x][y].addNeighbor(grid[x + i][y + j]);
                    }
                }
            }
        }
    }


    /**
     * Transition to next state - triggers cells to move to their next state.
     */
    public void tick() {
        if (this.state == GameState.ACTIVE) {
            for (int x = 0; x < GRID_SIZE_IN_CELLS; x++) {
                for (int y = 0; y < GRID_SIZE_IN_CELLS; y++) {
                    grid[x][y].saveCurrentState();
                }
            }
            for (int x = 0; x < GRID_SIZE_IN_CELLS; x++) {
                for (int y = 0; y < GRID_SIZE_IN_CELLS; y++) {
                    grid[x][y].update();
                }
            }
        }
    }


    /**
     * Game is paused.
     */
    public void stop() {
        LOGGER.debug("mouseExited");
        this.state = GameState.INACTIVE;
    }


    /**
     * Game is running.
     */
    public void start() {
        LOGGER.debug("mouseEntered");
        this.state = GameState.ACTIVE;
    }


    /**
     * Grid's {@link Cell}s are drawn on the GUI.
     *
     * @param g
     */
    public void draw(Graphics g) {
        for (int x = 0; x < GRID_SIZE_IN_CELLS; x++) {
            for (int y = 0; y < GRID_SIZE_IN_CELLS; y++) {
                if (grid[x][y].isAlive()) {
                    g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }


    /**
     * The two states of the game (running/paused).
     */
    enum GameState {ACTIVE, INACTIVE}
}