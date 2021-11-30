package base;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<Cell> aliveCells = new ArrayList<Cell>();

    public void setAlivePoints(List<Point> alivePoints){
        aliveCells.clear();
        for(Point p : alivePoints){
            Cell aliveCell = new Cell(p, true);
            aliveCells.add(aliveCell);
        }
    }

    public void setCellState(Point coordinates, boolean isAlive){

    }

    public void updateToNextIteration(){
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

//        for(Cell c : aliveCells){
//
//            if(c.getPositionX() < minX){
//                minX =
//            }
//        }
//    }
//
//    public
}
