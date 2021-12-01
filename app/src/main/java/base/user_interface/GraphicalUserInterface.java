package base.user_interface;


import base.Point;
//import base.user_interface.one.Layout;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GraphicalUserInterface {
    private static Logger LOGGER = LoggerFactory.getLogger(GraphicalUserInterface.class);
//    Layout layout = new Layout();

    public void drawGrid(List<Point> alivePoints) {
        // TODO: draw alive points
        alivePoints.forEach(a -> LOGGER.debug("Currently alive point: " + a.getX() + "," + a.getY()));
        final Table<Integer, Integer, Boolean> tableOfCells = HashBasedTable.create();
        alivePoints.forEach(a -> tableOfCells.put(a.getX(), a.getY(), true));

        for (int y = -8; y < 8; y++) {
            StringBuilder str1 = new StringBuilder();
            for (int x = -8; x < 8; x++) {
                String str2 = " ";
                if (tableOfCells.contains(x, y)) {
                    str2 = "X";
                }
                str1.append(str2);
            }
            LOGGER.info(str1.toString());
        }

    }


    public List<Point> getUserSelection() {
        //TODO: Ask user for selection of alive points and return
        final List<Point> userSelection = new ArrayList<>();
        return getDefaultPattern3();
    }


    private List<Point> getDefaultPattern1() {
        final List<Point> userSelection = new ArrayList<>();
        userSelection.add(new Point(1, 1));
        userSelection.add(new Point(2, 1));
        userSelection.add(new Point(3, 1));
        return userSelection;
    }


    private List<Point> getDefaultPattern2() {
        final List<Point> userSelection = new ArrayList<>();
        userSelection.add(new Point(2, 0));
        userSelection.add(new Point(1, 1));
        userSelection.add(new Point(2, 1));
        userSelection.add(new Point(3, 1));
        return userSelection;
    }


    private List<Point> getDefaultPattern3() {
        final List<Point> userSelection = new ArrayList<>();
        userSelection.add(new Point(0, 0));
        userSelection.add(new Point(1, 1));
        userSelection.add(new Point(2, 1));
        userSelection.add(new Point(3, 1));
        return userSelection;
    }
}
