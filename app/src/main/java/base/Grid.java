package base;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<Cell> aliveCells = new ArrayList<Cell>();

    public void setAlivePoints(List<Point> alivePoints) {
        aliveCells.clear();
        for (Point p : alivePoints) {
            Cell aliveCell = new Cell(p, true);
            aliveCells.add(aliveCell);
        }
    }

    public void setCellState(Point coordinates, boolean isAlive) {

    }

    public void updateToNextIteration() {
        final Point[] gridBoundaryPoints = getGridBoundaryPoints();
        final Table<Integer, Integer, Cell> aliveCellsHashTable = HashBasedTable.create();
        aliveCells.forEach(a -> aliveCellsHashTable.put(a.getPositionX(), a.getPositionY(), a));

        final List<Cell> nextAliveCells = new ArrayList<>();
        for (int x = gridBoundaryPoints[0].getX(); x <= gridBoundaryPoints[1].getX(); x++) {
            for (int y = gridBoundaryPoints[0].getY(); y <= gridBoundaryPoints[1].getY(); y++) {
                int numberOfAliveNeighbors = 0;

                final List<Point> neighbors = new ArrayList<>();
                neighbors.add(new Point(x - 1, y + 1));
                neighbors.add(new Point(x - 1, y));
                neighbors.add(new Point(x - 1, y - 1));
                neighbors.add(new Point(x, y + 1));
                neighbors.add(new Point(x, y - 1));
                neighbors.add(new Point(x + 1, y + 1));
                neighbors.add(new Point(x + 1, y));
                neighbors.add(new Point(x + 1, y - 1));

                for (Point neighbor : neighbors) {
                    if (aliveCellsHashTable.contains(neighbor.getX(), neighbor.getY())) {
                        numberOfAliveNeighbors++;
                    }
                }


                Cell cell = aliveCellsHashTable.get(x, y);
                if (numberOfAliveNeighbors == 3 || (numberOfAliveNeighbors == 2 && cell != null)) {
                    if (cell == null) {
                        cell = new Cell(new Point(x, y), true);
                    }
                    nextAliveCells.add(cell);
                }
            }
        }
    }

    public List<Point> getAlivePoints() {
        List<Point> convertedPoints = cellsToPoints(aliveCells);
        return convertedPoints;
    }

    private Point[] getGridBoundaryPoints() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Cell c : aliveCells) {
            int positionX = c.getPositionX();
            int positionY = c.getPositionY();

            minX = Math.min(positionX, minX);
            minY = Math.min(positionY, minY);
            maxX = Math.max(positionX, maxX);
            maxY = Math.max(positionY, maxY);
        }

        int boundaryLeft = minX;
        int boundaryUp = minY;
        int boundaryRight = maxX;
        int boundaryDown = maxY;

        Point[] gridBoundaryPoints = new Point[2];
        gridBoundaryPoints[0] = new Point(boundaryLeft, boundaryUp);
        gridBoundaryPoints[1] = new Point(boundaryRight, boundaryDown);

        return gridBoundaryPoints;
    }

    private List<Point> cellsToPoints(List<Cell> cells) {
        List<Point> points = new ArrayList<>();

        for (Cell cell : cells) {
            Point convertedPoint = new Point(cell.getPositionX(), cell.getPositionY());
            points.add(convertedPoint);
        }

        return points;
    }

}
