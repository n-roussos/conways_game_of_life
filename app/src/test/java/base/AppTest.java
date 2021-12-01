package base;

import onno.GoL;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTest {
    private static Logger LOGGER = LoggerFactory.getLogger(AppTest.class);


    @Test
    public void testBase() {
        final GameOfLife game = new GameOfLife();
        game.start();
    }


    @Test
    public void testOnno() throws InterruptedException {
        new GoL();
    }
}
