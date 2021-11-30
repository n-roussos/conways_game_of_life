package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameOfLifeMain {
    private static Logger LOGGER = LoggerFactory.getLogger(GameOfLifeMain.class);


    public static void main(String[] args) {
        final GameOfLife game = new GameOfLife();
        game.start();
    }
}
