package base;

public class Cell {
    private boolean isAlive;
    private Point coordinates;

    public Cell(Point coordinates, boolean isAlive){
        this.coordinates = coordinates;
        this.isAlive = isAlive;
    }

    public Cell(Point coordinates){
        this(coordinates, false);
    }

    public int getPositionX(){
        return coordinates.getX();
    }

    public int getPositionY(){
        return coordinates.getY();
    }
}
