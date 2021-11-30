package base;

import base.user_interface.GraphicalUserInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 *
 */
public class GameOfLife {
    private static Logger LOGGER = LoggerFactory.getLogger(GameOfLife.class);
    private final GraphicalUserInterface graphicalUserInterface;
    private final Grid grid;

    public GameOfLife() {
        this.graphicalUserInterface = new GraphicalUserInterface();
        this.grid = new Grid();
    }


    /**
     *
     */
    public void start() {
        final List<Point> userAlivePoints = this.graphicalUserInterface.getUserSelection();
        this.grid.setAlivePoints(userAlivePoints);

        int counter = 0;
        while (counter < 30) {
            counter++;
            LOGGER.debug("\nIteration: " + counter);
            updateGrid();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        }
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }


    /**
     * Updates grid to next state, gets its new state, and propagates it to the GUI.
     */
    private void updateGrid() {
        this.grid.updateToNextIteration();
        LOGGER.debug("Updated grid");
        final List<Point> alivePoints = this.grid.getAlivePoints();
        LOGGER.debug("Got alive points from grid");
        this.graphicalUserInterface.drawGrid(alivePoints);
        LOGGER.debug("Drew points");
    }
}
