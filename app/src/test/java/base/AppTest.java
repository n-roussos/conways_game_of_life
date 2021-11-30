package base;

import base.user_interface.GraphicalUserInterface;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTest {
    private static Logger LOGGER = LoggerFactory.getLogger(AppTest.class);


    @Test
    public void testGui() {
        GraphicalUserInterface.run();
    }
}
