package model;

public class Cell {
    int x;
    int y;
    public Ship ship = null;
    public boolean isShoot;

    public Cell(int x, int y, Ship ship, boolean isShoot) {
        this.x = x;
        this.y = y;
        this.ship = ship;
        this.isShoot = isShoot;
    }
}
